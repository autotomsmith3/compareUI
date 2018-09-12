package etl;

//package org.autodatacorp.cpp.etl.color.bolt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.autodatacorp.cpp.etl.color.config.VehicleSetConfigurations;
import org.autodatacorp.cpp.etl.color.config.VehicleSetConfigurations.VehicleSetConfig;
import org.autodatacorp.cpp.etl.color.cps.ColorData;
import org.autodatacorp.cpp.etl.color.cps.CpsWebServiceClient;
import org.autodatacorp.cpp.etl.color.cps.CpsWebServiceClient.CpsWebServiceClientException;
import org.autodatacorp.cpp.etl.color.cps.OptionData;
import org.autodatacorp.cpp.etl.color.mapper.Mapping;
import org.autodatacorp.cpp.etl.color.model.ColorVO;
import org.autodatacorp.cpp.etl.color.model.PaintTables;
import org.autodatacorp.cpp.etl.color.model.PaintTablesVO;
import org.autodatacorp.cpp.etl.color.service.ColorService;
import org.autodatacorp.cpp.etl.color.service.ColorServiceException;
import org.autodatacorp.cpp.etl.config.Configurations;
import org.autodatacorp.cpp.etl.cps.Languages;
import org.autodatacorp.cpp.etl.data.CppSqlSessionProvider;
import org.autodatacorp.cpp.etl.data.SqlSessionProvider;
import org.autodatacorp.cpp.etl.color.config.CountryLanguages;
import org.autodatacorp.cpp.etl.storm.bolt.CppBolt;
import org.autodatacorp.cpp.etl.storm.bolt.CppBoltException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.cds.cps.model.option.Option;
import net.cds.cps.model.paint.PaintTable;
 
/**
 * ColorBolt
 * @author yashbhan.singh
 *
 */
public class ColorBolt extends CppBolt {
 
    private static final long serialVersionUID = 6638758856533660572L;
    private transient OutputCollector collector;
    private transient CpsWebServiceClient cpsWebServiceClient;
    private transient ColorService colorService;
    private transient Map<String, VehicleSetConfig> vehicleSetConfigMap;
    private transient Map<String, List<String>> countryLanguageMap;
    private transient ColorBoltHelper colorBoltHelper;
    private static final Logger logger = LoggerFactory.getLogger(ColorBolt.class);
    private static Set<String> nonConfiguredVehicleSets = new HashSet<>();
 
    @Override
    public void executeTuple(Tuple tuple) {
 
        String gvuid = tuple.getStringByField(ColorTuples.GVUID);
        String transaction = tuple.getStringByField(ColorTuples.TRANSACTION);
        Mapping mapping = (Mapping) tuple.getValueByField(ColorTuples.MAPPIING);
        
        VehicleSetConfig vehicleSetConfig = vehicleSetConfigMap.get(mapping.getVehicleSet());
        
        // The vehicle set id is not configured and should NOT be processed
        if (vehicleSetConfig == null) {
            boolean added = nonConfiguredVehicleSets.add(mapping.getVehicleSet());
            if (added) {
                logger.warn("The VehicleSet {} is not configured", mapping.getVehicleSet());
                reportErrorAndAck(tuple, "The VehicleSet " + mapping.getVehicleSet() + " is not configured");
            }
            collector.ack(tuple);
            return;
        }
 
        String countryCode = mapping.getWarehouseKey().substring(0, 2);
        List<String> lngCodes = countryLanguageMap.get(countryCode);
        List<ColorVO> colorsFromCPS = null;
        List<ColorVO> colorsFromGV;
        String errMsg;
        try{
            colorsFromCPS = resolveColors(mapping.getVehicleSetId(), vehicleSetConfig, lngCodes);
        }catch (CpsWebServiceClientException e) {
            logger.error(e.getMessage(), e);
            errMsg = "Error in color CPS for the GVUID[" + gvuid + "]." + e.getMessage();
            reportErrorAndAck(tuple, errMsg);
            return;
        }
        try{
            colorsFromGV = readColors(gvuid);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            errMsg = "Error while fetching colors data from DB for the GVUID[" + gvuid + "]." + e.getMessage();
            reportErrorAndAck(tuple, errMsg);
            return;
        }
        
        if (colorsFromCPS == null || colorsFromCPS.isEmpty()) {
            /**
             * Case when Colors in CPS Service result are 0 and there are colors
             * in DB. Colors data is being deleted from the DB in this case.
             */
            if (colorsFromGV != null && !colorsFromGV.isEmpty()) {
                for (ColorVO colorVO : colorsFromGV) {
                    deleteColors(gvuid, colorVO, tuple);
                }
                // Records updated in DB. Emit the values to Gvuid
                // Kafka Bolt.
                Values values = buildValues(gvuid, transaction);
                collector.emit(tuple, values);
            }
        } else {
            performCheckForUpdates(colorsFromCPS, colorsFromGV, gvuid, lngCodes, tuple, transaction);
        }
        collector.ack(tuple);
        return;
 
    }
    
    private List<ColorVO> readColors(String gvuid) throws ColorServiceException{
        return colorService.readColors(gvuid);
        
    }
    
    private void deleteColors(String gvuid, ColorVO colorVO, Tuple tuple){
        try {
            colorService.deleteColors(gvuid, colorVO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String errMsg = "Error while deleting color data from DB for the GVUID[" + gvuid + "]." + e.getMessage();
            reportErrorAndAck(tuple, errMsg);
        }
    }
    
    private void performCheckForUpdates(List<ColorVO> colorsFromCPS, List<ColorVO> colorsFromGV, String gvuid,
            List<String> lngCodes, Tuple tuple, String transaction) {
        // Compare the colors and store the result in a boolean var
        // to check if an emit is required.
        try {
            boolean result = performCheck(colorsFromCPS, colorsFromGV, gvuid, lngCodes);
            // Emit the values to Gvuid Kafka Bolt in case result is true.
            if (result) {
                Values values = buildValues(gvuid, transaction);
                collector.emit(tuple, values);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String errMsg = "Error while performing updates on data in DB for the GVUID[" + gvuid + "]." + e.getMessage();
            reportErrorAndAck(tuple, errMsg);
        }
    }
    
    private void reportErrorAndAck(Tuple tuple, String msg) {
        collector.reportError(new CppBoltException(msg, tuple));
        collector.ack(tuple);
 
    }
 
    protected Values buildValues(String gvuid, String transaction) {
        Values values = new Values();
        values.add(gvuid);
        values.add(transaction);
        return values;
 
    }
 
    @Override
    public void prepareBolt(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        Properties properties = Configurations.resolveProperties(stormConf, true);
        cpsWebServiceClient = new CpsWebServiceClient(properties);
        SqlSessionProvider sqlSessionProvider = new CppSqlSessionProvider(properties, "sqlSession.color");
        colorService = new ColorService(sqlSessionProvider);
        vehicleSetConfigMap = VehicleSetConfigurations.configureVehicleSetConfigMap(properties);
        countryLanguageMap = CountryLanguages.buildCountryLanguageListMap(properties);
        colorBoltHelper = new ColorBoltHelper();
    }
 
    protected List<ColorVO> resolveColors(String vehcielId, VehicleSetConfig vehicleSetConfig, List<String> lngCodes) throws CpsWebServiceClientException {
 
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsForEcc = new ArrayList<>();
        List<PaintTablesVO> paintTablesVOList = new ArrayList<>();
        byte[] cpsResponseData;
 
 
        if (vehicleSetConfig.getCpsCustomCode() != null) {
            cpsResponseData = cpsWebServiceClient.readCustomColorData(vehicleSetConfig.getCpsOutput(),
                        vehicleSetConfig.getCpsSet(), vehicleSetConfig.getCpsCustomCode(), vehcielId);
            
        } else {
            cpsResponseData = cpsWebServiceClient.readColorData(vehicleSetConfig.getCpsOutput(),
                        vehicleSetConfig.getCpsSet(), vehcielId);
        }
 
        addCPSResponseToColorVO(cpsResponseData, paintTablesVOList);
        // Parsing response for Options from CPS response
        OptionData optionsFromCPS = OptionData.parse(cpsResponseData);
        if (optionsFromCPS.getOptionData() != null) {
            optionsForEcc = optionsFromCPS.getOptionData().getOptions();
        }
        for (String lngCode : lngCodes) {
            for (PaintTablesVO paintTable : paintTablesVOList) {
                colorVOList = colorBoltHelper.generateColorVOList(colorVOList, optionsForEcc, paintTable,
                        lngCode);
            }
        }
        return colorVOList;
    }
 
    private void addCPSResponseToColorVO(byte[] cpsResponseData, List<PaintTablesVO> paintTablesVOList) {
 
        ColorData colorsFromCPS = ColorData.parse(cpsResponseData);
        PaintTables paintTables = colorsFromCPS.getColorData();
        if (paintTables != null) {
            List<PaintTable> colors = paintTables.getPaintTables();
            if (colors != null) {
                for (PaintTable color : colors) {
                    PaintTablesVO paintTablesVO = new PaintTablesVO();
                    paintTablesVO.setInteriors(color.getInteriors());
                    paintTablesVO.setRows(color.getRows());
                    paintTablesVOList.add(paintTablesVO);
                }
            }
        }
    }
 
    private boolean performCheck(List<ColorVO> colorsFromCPS, List<ColorVO> colorsFromGV, String gvuid,
            List<String> lngCodes) throws ColorServiceException {
 
        boolean result = false;
 
        /**
         * Case when colors in DB are 0 and there are colors in CPS service
         * result. Colors data is being inserted in to DB in this case.
         */
        if (colorsFromGV == null || colorsFromGV.isEmpty()) {
 
            for (ColorVO colorCPS : colorsFromCPS) {
                createColor(colorCPS, gvuid);
            }
            result = true;
        } else {
 
            /**
             * Do the comparisons and perform the updates accordingly
             */
            boolean comparisonResult;
            for (String lngCode : lngCodes) {
                comparisonResult = compareColorsForLngCode(colorsFromCPS, colorsFromGV, gvuid,
                        lngCode);
                result = result || comparisonResult;
            }
        }
 
        return result;
 
    }
 
    private boolean compareColorsForLngCode(List<ColorVO> colorsFromCPS, List<ColorVO> colorsFromGV, String gvuid,
            String lngCode) throws ColorServiceException {
 
        boolean comparisonResult = false;
        List<ColorVO> colorsListCPS = new ArrayList<>();
        List<ColorVO> colorsListGV = new ArrayList<>();
 
        for (ColorVO colorVO : colorsFromCPS) {
            if (lngCode.trim().equalsIgnoreCase(colorVO.getIsoLngCode())) {
                colorsListCPS.add(colorVO);
            }
        }
 
        for (ColorVO colorVO : colorsFromGV) {
            if (lngCode.trim().equalsIgnoreCase(colorVO.getIsoLngCode())) {
                colorsListGV.add(colorVO);
            }
        }
 
        Map<String, ColorVO> colorsGVMap = colorsListGV.stream()
                .collect(Collectors.toMap(ColorVO::getColorId, Function.identity()));
 
        Map<String, ColorVO> colorsCPSMap = colorsListCPS.stream()
                .collect(Collectors.toMap(ColorVO::getColorId, Function.identity()));
 
        if (!colorsGVMap.equals(colorsCPSMap)) {
 
            /**
             * Case when Colors in CPS Service do exist in DB but are different.
             * Colors data is being updated in the DB in this case.
             */
 
            updateColor(colorsListCPS, colorsGVMap, gvuid);
 
            /**
             * Case when Colors in DB exist and these do not exist in the CPS
             * result. Here, we are deleting those records from the DB.
             */
 
            deleteColor(colorsListGV, colorsCPSMap, gvuid);
            comparisonResult = true;
        }
        return comparisonResult;
 
    }
 
    private void createColor(ColorVO color, String gvuid) throws ColorServiceException {
        color.setGvuid(gvuid);
        colorService.createColor(color);
 
    }
 
    private void updateColor(List<ColorVO> colorsFromCPS, Map<String, ColorVO> colorsGVMap, String gvuid)
            throws ColorServiceException {
 
        for (ColorVO cpsColor : colorsFromCPS) {
            ColorVO gvColor = colorsGVMap.get(cpsColor.getColorId());
            if (gvColor == null) {
                // Insert CPS color into DB
                createColor(cpsColor, gvuid);
            } else {
                boolean equal = compare(cpsColor, gvColor);
                if (!equal) {
                    cpsColor.setGvuid(gvuid);
                    // Delete from ColorDesc using cpsColor
                    colorService.updateColors(cpsColor, gvColor.getColorDescUID());
                }
            }
 
        }
 
    }
 
    private void deleteColor(List<ColorVO> colorsFromGV, Map<String, ColorVO> colorsGVMap, String gvuid)
            throws ColorServiceException {
        for (ColorVO color : colorsFromGV) {
            ColorVO cpsColor = colorsGVMap.get(color.getColorId());
            if (cpsColor == null) {
 
                colorService.deleteColors(gvuid, color);
            }
 
        }
    }
 
    private boolean compare(ColorVO cpsColor, ColorVO gvColor) {
        boolean result = false;
        if (cpsColor.toString().equals(gvColor.toString())) {
            result = true;
        }
        return result;
    }
 
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(ColorTuples.COLOR_DECLARED_FIELDS));
    }
}