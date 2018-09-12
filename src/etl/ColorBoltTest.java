package etl;

//package org.autodatacorp.cpp.etl.color.bolt;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.autodatacorp.cpp.etl.color.config.VehicleSetConfigurations.VehicleSetConfig;
import org.autodatacorp.cpp.etl.color.cps.CpsWebServiceClient;
import org.autodatacorp.cpp.etl.color.mapper.Mapping;
import org.autodatacorp.cpp.etl.color.model.ColorVO;
import org.autodatacorp.cpp.etl.color.repository.ColorRepository;
import org.autodatacorp.cpp.etl.color.service.ColorService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
 
public class ColorBoltTest {
 
    @Mock
    Map conf = Mockito.mock(Map.class);
 
    @Mock
    Map<String, VehicleSetConfig> vehicleSetConfigMap = Mockito.mock(Map.class);
 
    @Mock
    Map<String, List<String>> countryLanguageMap = Mockito.mock(Map.class);
 
    @Mock
    Tuple tuple = Mockito.mock(Tuple.class);
 
    @Mock
    OutputCollector collector = Mockito.mock(OutputCollector.class);
 
    @Mock
    CpsWebServiceClient cpsWebServiceClient = Mockito.mock(CpsWebServiceClient.class);
 
    @Mock
    ColorBoltHelper colorBoltHelper = Mockito.mock(ColorBoltHelper.class);
 
    @Mock
    ColorService colorService = Mockito.mock(ColorService.class);
 
    @Mock
    private ColorRepository colorRepository;
 
    /**
     * testExecuteTupleWhenResultsAreDifferentCPSAndGV executes the ColorBolt
     * and check if the results are DIFFERENT. Verifies that the collector
     * should emit values as results are DIFFERENT.
     * @throws Throwable
     */
    @Test
    public void testExecuteTupleWhenResultsAreDifferentCPSAndGV() throws Throwable {
 
        ColorBolt colorBolt = new ColorBolt();
 
        Field cpsWebServiceClientField = ColorBolt.class.getDeclaredField("cpsWebServiceClient");
        cpsWebServiceClientField.setAccessible(true);
        cpsWebServiceClientField.set(colorBolt, cpsWebServiceClient);
 
        Field colorBoltHelperField = ColorBolt.class.getDeclaredField("colorBoltHelper");
        colorBoltHelperField.setAccessible(true);
        colorBoltHelperField.set(colorBolt, colorBoltHelper);
 
        Field colorServiceField = ColorBolt.class.getDeclaredField("colorService");
        colorServiceField.setAccessible(true);
        colorServiceField.set(colorBolt, colorService);
 
        Field collectorField = ColorBolt.class.getDeclaredField("collector");
        collectorField.setAccessible(true);
        collectorField.set(colorBolt, collector);
 
        Field vehicleSetConfigMapField = ColorBolt.class.getDeclaredField("vehicleSetConfigMap");
        vehicleSetConfigMapField.setAccessible(true);
        vehicleSetConfigMapField.set(colorBolt, vehicleSetConfigMap);
 
        Field countryLanguageMapField = ColorBolt.class.getDeclaredField("countryLanguageMap");
        countryLanguageMapField.setAccessible(true);
        countryLanguageMapField.set(colorBolt, countryLanguageMap);
 
        String colorList = "<Options>"
                + "<Option optCode='KCSW' optLevel='01' realOrder='10010' optCluster='1000' paintCluster='1000' optTrim='*' ecc='0083'>"
                + "<optDescription><en>Black</en><translation lngCode='SP'>Negro</translation></optDescription>"
                + "<fullDescription><en>Black w/Sensatec Leatherette Upholstery</en><translation lngCode='SP'>Negro c/Tapicería en imitación cuero Sensatec</translation></fullDescription>"
                + "</Option>" + "</Options>" + "<PaintTables>"
                + "<Table tableCode='PTUSC60BMS19004' clusterCode='1000' isClrCombo='false' isNotToBeUsedWithCombo='false' chartNum='0'>"
                + "<InteriorColors>"
                + "<Color interiorID='1' rowCode='Row1' colorCode='KCSW' colorVar='01' genericColorCode='2'>"
                + "<shortDescription><en>Black</en></shortDescription>"
                + "<longDescription><en>Black w/Sensatec Leatherette Upholstery</en></longDescription>"
                + "<admsDescription><en>Black</en></admsDescription>" + "<rgb red='6' green='6' blue='6' />"
                + "</Color>" + "</InteriorColors>" + "</Table>" + "</PaintTables>";
 
        Mapping mapping = createMapping();
        VehicleSetConfig vehicleSetConfig = new VehicleSetConfig("USC60BMS192A0", "CPP_NoConfig", "BaseAcode", null);
 
        List<ColorVO> colorVOList = generateTestData("testExecuteTupleWhenResultsAreDifferentCPSAndGV");
        List<ColorVO> colorVOListFromCPS = generateTestDataForCPS();
 
        Mockito.when(tuple.getStringByField(ColorTuples.GVUID)).thenReturn("1da2e95e-3752-488b-a3c2-3ead130d8dfd");
        Mockito.when(tuple.getStringByField(ColorTuples.TRANSACTION)).thenReturn("transactionId");
        Mockito.when(tuple.getValueByField(ColorTuples.MAPPIING)).thenReturn(mapping);
        Mockito.when(vehicleSetConfigMap.get(mapping.getVehicleSet())).thenReturn(vehicleSetConfig);
        List<String> langList = new ArrayList<>();
        langList.add("en");
        langList.add("es");
        Mockito.when(countryLanguageMap.get(Mockito.anyString())).thenReturn(langList);
        Mockito.when(cpsWebServiceClient.readColorData(Mockito.any(), Mockito.any(),
            Mockito.any())).thenReturn(colorList.getBytes());
        Mockito.when(colorService.readColors(Mockito.anyString())).thenReturn(colorVOList);
        Mockito.when(colorBoltHelper.generateColorVOList(Mockito.anyList(), Mockito.anyList(), Mockito.anyObject(),
                Mockito.anyString())).thenReturn(colorVOListFromCPS);
        colorBolt.executeTuple(tuple);
        Mockito.verify(collector).emit(Mockito.any(Tuple.class), Mockito.any(Values.class));
    }
 
    /**
     * testExecuteTupleWhenResultsAreSameCPSAndGV executes the ColorBolt and
     * check if the results are SAME. Verifies that the collector should not
     * emit anything as results are SAME.
     * @throws Throwable
     */
    @Test
    public void testExecuteTupleWhenResultsAreSameCPSAndGV() throws Throwable {
 
        ColorBolt colorBolt = new ColorBolt();
 
        Field cpsWebServiceClientField = ColorBolt.class.getDeclaredField("cpsWebServiceClient");
        cpsWebServiceClientField.setAccessible(true);
        cpsWebServiceClientField.set(colorBolt, cpsWebServiceClient);
 
        Field colorBoltHelperField = ColorBolt.class.getDeclaredField("colorBoltHelper");
        colorBoltHelperField.setAccessible(true);
        colorBoltHelperField.set(colorBolt, colorBoltHelper);
 
        Field colorServiceField = ColorBolt.class.getDeclaredField("colorService");
        colorServiceField.setAccessible(true);
        colorServiceField.set(colorBolt, colorService);
 
        Field collectorField = ColorBolt.class.getDeclaredField("collector");
        collectorField.setAccessible(true);
        collectorField.set(colorBolt, collector);
 
        Field vehicleSetConfigMapField = ColorBolt.class.getDeclaredField("vehicleSetConfigMap");
        vehicleSetConfigMapField.setAccessible(true);
        vehicleSetConfigMapField.set(colorBolt, vehicleSetConfigMap);
 
        Field countryLanguageMapField = ColorBolt.class.getDeclaredField("countryLanguageMap");
        countryLanguageMapField.setAccessible(true);
        countryLanguageMapField.set(colorBolt, countryLanguageMap);
 
        String colorList = "<Options>"
                + "<Option optCode='KCSW' optLevel='01' realOrder='10010' optCluster='1000' paintCluster='1000' optTrim='*' ecc='0083'>"
                + "<optDescription><en>Black</en><translation lngCode='SP'>Negro</translation></optDescription>"
                + "<fullDescription><en>Black w/Sensatec Leatherette Upholstery</en><translation lngCode='SP'>Negro c/Tapicería en imitación cuero Sensatec</translation></fullDescription>"
                + "</Option>" + "</Options>" + "<PaintTables>"
                + "<Table tableCode='PTUSC60BMS19004' clusterCode='1000' isClrCombo='false' isNotToBeUsedWithCombo='false' chartNum='0'>"
                + "<InteriorColors>"
                + "<Color interiorID='1' rowCode='Row1' colorCode='KCSW' colorVar='01' genericColorCode='2'>"
                + "<shortDescription><en>Black</en></shortDescription>"
                + "<longDescription><en>Black w/Sensatec Leatherette Upholstery</en></longDescription>"
                + "<admsDescription><en>Black</en></admsDescription>" + "<rgb red='6' green='6' blue='6' />"
                + "</Color>" + "</InteriorColors>" + "</Table>" + "</PaintTables>";
 
        Mapping mapping = createMapping();
        VehicleSetConfig vehicleSetConfig = new VehicleSetConfig("USC70AUS011A0", "CPP_NoConfig", "BaseAcode", null);
 
        List<ColorVO> colorVOList = generateTestData("testExecuteTupleWhenResultsAreSameCPSAndGV");
        List<ColorVO> colorVOListFromCPS = generateTestDataForCPS();
 
        Mockito.when(tuple.getStringByField(ColorTuples.GVUID)).thenReturn("1da2e95e-3752-488b-a3c2-3ead130d8dfd");
        Mockito.when(tuple.getStringByField(ColorTuples.TRANSACTION)).thenReturn("transactionId");
        Mockito.when(tuple.getValueByField(ColorTuples.MAPPIING)).thenReturn(mapping);
        Mockito.when(vehicleSetConfigMap.get(mapping.getVehicleSet())).thenReturn(vehicleSetConfig);
        List<String> langList = new ArrayList<>();
        langList.add("en");
        langList.add("sp");
        Mockito.when(countryLanguageMap.get(Mockito.anyString())).thenReturn(langList);
        Mockito.when(cpsWebServiceClient.readColorData(vehicleSetConfig.getCpsOutput(), vehicleSetConfig.getCpsSet(),
                mapping.getVehicleSetId())).thenReturn(colorList.getBytes());
        Mockito.when(colorService.readColors(Mockito.anyString())).thenReturn(colorVOList);
        Mockito.when(colorBoltHelper.generateColorVOList(Mockito.anyList(), Mockito.anyList(), Mockito.anyObject(),
                Mockito.anyString())).thenReturn(colorVOListFromCPS);
        colorBolt.executeTuple(tuple);
 
        Mockito.verify(collector, Mockito.atMost(0)).emit(Mockito.any(Tuple.class), Mockito.any(Values.class));
    }
 
    /**
     * testExecuteTupleWhenCPSResultIsNullButGVIsNotNull executes the ColorBolt
     * and check if the CPS result is NULL but GV is not NULL. Verifies that the
     * collector should emit values as results are DIFFERENT.
     * @throws Throwable
     */
    @Test
    public void testExecuteTupleWhenCPSResultIsNullButGVIsNotNull() throws Throwable {
 
        ColorBolt colorBolt = new ColorBolt();
 
        Field cpsWebServiceClientField = ColorBolt.class.getDeclaredField("cpsWebServiceClient");
        cpsWebServiceClientField.setAccessible(true);
        cpsWebServiceClientField.set(colorBolt, cpsWebServiceClient);
 
        Field colorBoltHelperField = ColorBolt.class.getDeclaredField("colorBoltHelper");
        colorBoltHelperField.setAccessible(true);
        colorBoltHelperField.set(colorBolt, colorBoltHelper);
 
        Field colorServiceField = ColorBolt.class.getDeclaredField("colorService");
        colorServiceField.setAccessible(true);
        colorServiceField.set(colorBolt, colorService);
 
        Field collectorField = ColorBolt.class.getDeclaredField("collector");
        collectorField.setAccessible(true);
        collectorField.set(colorBolt, collector);
 
        Field vehicleSetConfigMapField = ColorBolt.class.getDeclaredField("vehicleSetConfigMap");
        vehicleSetConfigMapField.setAccessible(true);
        vehicleSetConfigMapField.set(colorBolt, vehicleSetConfigMap);
 
        Field countryLanguageMapField = ColorBolt.class.getDeclaredField("countryLanguageMap");
        countryLanguageMapField.setAccessible(true);
        countryLanguageMapField.set(colorBolt, countryLanguageMap);
 
        String vehicleLinkList = "";
        List<ColorVO> colorVOListFromCPS = null;
 
        Mapping mapping = createMapping();
        VehicleSetConfig vehicleSetConfig = new VehicleSetConfig("USC70AUS011A0", "CPP_NoConfig", "BaseAcode", null);
 
        List<ColorVO> colorVOList = generateTestData("testExecuteTupleWhenCPSResultIsNullButGVIsNotNull");
 
        Mockito.when(tuple.getStringByField(ColorTuples.GVUID)).thenReturn("1da2e95e-3752-488b-a3c2-3ead130d8dfd");
        Mockito.when(tuple.getStringByField(ColorTuples.TRANSACTION)).thenReturn("transactionId");
        Mockito.when(tuple.getValueByField(ColorTuples.MAPPIING)).thenReturn(mapping);
        Mockito.when(vehicleSetConfigMap.get(mapping.getVehicleSet())).thenReturn(vehicleSetConfig);
        List<String> langList = new ArrayList<>();
        langList.add("en");
        langList.add("sp");
        Mockito.when(countryLanguageMap.get(Mockito.anyString())).thenReturn(langList);
        Mockito.when(cpsWebServiceClient.readColorData(vehicleSetConfig.getCpsOutput(), vehicleSetConfig.getCpsSet(),
                mapping.getVehicleSetId())).thenReturn(vehicleLinkList.getBytes());
        Mockito.when(colorService.readColors(Mockito.anyString())).thenReturn(colorVOList);
        Mockito.when(colorBoltHelper.generateColorVOList(Mockito.anyList(), Mockito.anyList(), Mockito.anyObject(),
                Mockito.anyString())).thenReturn(colorVOListFromCPS);
        colorBolt.executeTuple(tuple);
 
        Mockito.verify(collector, Mockito.atLeast(1)).emit(Mockito.any(Tuple.class), Mockito.any(Values.class));
    }
 
    /**
     * testExecuteTupleWhenCPSResultIsNotNullButGVIsNull executes the ColorBolt
     * and check if the CPS result is not NULL but GV is NULL. Verifies that the
     * collector should emit values as results are DIFFERENT.
     * @throws Throwable
     */
    @Test
    public void testExecuteTupleWhenCPSResultIsNotNullButGVIsNull() throws Throwable {
 
        ColorBolt colorBolt = new ColorBolt();
 
        Field cpsWebServiceClientField = ColorBolt.class.getDeclaredField("cpsWebServiceClient");
        cpsWebServiceClientField.setAccessible(true);
        cpsWebServiceClientField.set(colorBolt, cpsWebServiceClient);
 
        Field colorBoltHelperField = ColorBolt.class.getDeclaredField("colorBoltHelper");
        colorBoltHelperField.setAccessible(true);
        colorBoltHelperField.set(colorBolt, colorBoltHelper);
 
        Field colorServiceField = ColorBolt.class.getDeclaredField("colorService");
        colorServiceField.setAccessible(true);
        colorServiceField.set(colorBolt, colorService);
 
        Field collectorField = ColorBolt.class.getDeclaredField("collector");
        collectorField.setAccessible(true);
        collectorField.set(colorBolt, collector);
 
        Field vehicleSetConfigMapField = ColorBolt.class.getDeclaredField("vehicleSetConfigMap");
        vehicleSetConfigMapField.setAccessible(true);
        vehicleSetConfigMapField.set(colorBolt, vehicleSetConfigMap);
 
        Field countryLanguageMapField = ColorBolt.class.getDeclaredField("countryLanguageMap");
        countryLanguageMapField.setAccessible(true);
        countryLanguageMapField.set(colorBolt, countryLanguageMap);
 
        String colorList = "<Options>"
                + "<Option optCode='KCSW' optLevel='01' realOrder='10010' optCluster='1000' paintCluster='1000' optTrim='*' ecc='0083'>"
                + "<optDescription><en>Black</en><translation lngCode='SP'>Negro</translation></optDescription>"
                + "<fullDescription><en>Black w/Sensatec Leatherette Upholstery</en><translation lngCode='SP'>Negro c/Tapicería en imitación cuero Sensatec</translation></fullDescription>"
                + "</Option>" + "</Options>" + "<PaintTables>"
                + "<Table tableCode='PTUSC60BMS19004' clusterCode='1000' isClrCombo='false' isNotToBeUsedWithCombo='false' chartNum='0'>"
                + "<InteriorColors>"
                + "<Color interiorID='1' rowCode='Row1' colorCode='KCSW' colorVar='01' genericColorCode='2'>"
                + "<shortDescription><en>Black</en></shortDescription>"
                + "<longDescription><en>Black w/Sensatec Leatherette Upholstery</en></longDescription>"
                + "<admsDescription><en>Black</en></admsDescription>" + "<rgb red='6' green='6' blue='6' />"
                + "</Color>" + "</InteriorColors>" + "</Table>" + "</PaintTables>";
 
        List<ColorVO> colorVOListFromCPS = generateTestDataForCPS();
 
        Mapping mapping = createMapping();
        VehicleSetConfig vehicleSetConfig = new VehicleSetConfig("USC70AUS011A0", "CPP_NoConfig", "BaseAcode", null);
 
        Mockito.when(tuple.getStringByField(ColorTuples.GVUID)).thenReturn("1da2e95e-3752-488b-a3c2-3ead130d8dfd");
        Mockito.when(tuple.getStringByField(ColorTuples.TRANSACTION)).thenReturn("transactionId");
        Mockito.when(tuple.getValueByField(ColorTuples.MAPPIING)).thenReturn(mapping);
        Mockito.when(vehicleSetConfigMap.get(mapping.getVehicleSet())).thenReturn(vehicleSetConfig);
        List<String> langList = new ArrayList<>();
        langList.add("en");
        langList.add("sp");
        Mockito.when(countryLanguageMap.get(Mockito.anyString())).thenReturn(langList);
        Mockito.when(cpsWebServiceClient.readColorData(vehicleSetConfig.getCpsOutput(), vehicleSetConfig.getCpsSet(),
                mapping.getVehicleSetId())).thenReturn(colorList.getBytes());
        Mockito.when(colorBoltHelper.generateColorVOList(Mockito.anyList(), Mockito.anyList(), Mockito.anyObject(),
                Mockito.anyString())).thenReturn(colorVOListFromCPS);
        Mockito.when(colorService.readColors(Mockito.anyString())).thenReturn(null);
 
        colorBolt.executeTuple(tuple);
 
        Mockito.verify(collector, Mockito.atLeast(1)).emit(Mockito.any(Tuple.class), Mockito.any(Values.class));
    }
 
    private List<ColorVO> generateTestData(String testCase) {
 
        List<ColorVO> colorVOList = new ArrayList<>();
        ColorVO colorVO = new ColorVO();
        if ("testExecuteTupleWhenResultsAreSameCPSAndGV".equals(testCase)) {
            ColorVO colorVOForOtherLng = new ColorVO();
            colorVOForOtherLng.setColor1Desc("Black");
            colorVOForOtherLng.setColor1GenericDesc("Black");
            colorVOForOtherLng.setColor1RGB("666");
            colorVOForOtherLng.setColor1Type("I");
            colorVOForOtherLng.setColorType("I");
            colorVOForOtherLng.setColorId("KCSW");
            colorVOForOtherLng.setColorCode1("KCSW");
            colorVOForOtherLng.setColor2Desc(null);
            colorVOForOtherLng.setColor2GenericDesc(null);
            colorVOForOtherLng.setColor2RGB(null);
            colorVOForOtherLng.setColor2Type(null);
            colorVOForOtherLng.setColor3Desc(null);
            colorVOForOtherLng.setColor3GenericDesc(null);
            colorVOForOtherLng.setColor3RGB(null);
            colorVOForOtherLng.setColor3Type(null);
            colorVOForOtherLng.setIsoLngCode("es");
 
            colorVOList.add(colorVOForOtherLng);
        }
        colorVO.setColor1Desc("Black");
        colorVO.setColor1GenericDesc("Black");
        colorVO.setColor1RGB("666");
        colorVO.setColor1Type("I");
        colorVO.setColorType("I");
        colorVO.setColorId("KCSW");
        colorVO.setColorCode1("KCSW");
        colorVO.setColor2Desc(null);
        colorVO.setColor2GenericDesc(null);
        colorVO.setColor2RGB(null);
        colorVO.setColor2Type(null);
        colorVO.setColor3Desc(null);
        colorVO.setColor3GenericDesc(null);
        colorVO.setColor3RGB(null);
        colorVO.setColor3Type(null);
        colorVO.setIsoLngCode("en");
 
        colorVOList.add(colorVO);
        return colorVOList;
 
    }
 
    private List<ColorVO> generateTestDataForCPS() {
 
        List<ColorVO> colorVOList = new ArrayList<>();
        ColorVO colorVO = new ColorVO();
        colorVO.setColor1Desc("Black");
        colorVO.setColor1GenericDesc("Black");
        colorVO.setColor1RGB("666");
        colorVO.setColor1Type("I");
        colorVO.setColorType("I");
        colorVO.setColorId("KCSW");
        colorVO.setColorCode1("KCSW");
        colorVO.setColor2Desc(null);
        colorVO.setColor2GenericDesc(null);
        colorVO.setColor2RGB(null);
        colorVO.setColor2Type(null);
        colorVO.setColor3Desc(null);
        colorVO.setColor3GenericDesc(null);
        colorVO.setColor3RGB(null);
        colorVO.setColor3Type(null);
        colorVO.setIsoLngCode("en");
        colorVOList.add(colorVO);
 
        ColorVO colorVOForOtherLng = new ColorVO();
        colorVOForOtherLng.setColor1Desc("Black");
        colorVOForOtherLng.setColor1GenericDesc("Black");
        colorVOForOtherLng.setColor1RGB("666");
        colorVOForOtherLng.setColor1Type("I");
        colorVOForOtherLng.setColorType("I");
        colorVOForOtherLng.setColorId("KCSW");
        colorVOForOtherLng.setColorCode1("KCSW");
        colorVOForOtherLng.setColor2Desc(null);
        colorVOForOtherLng.setColor2GenericDesc(null);
        colorVOForOtherLng.setColor2RGB(null);
        colorVOForOtherLng.setColor2Type(null);
        colorVOForOtherLng.setColor3Desc(null);
        colorVOForOtherLng.setColor3GenericDesc(null);
        colorVOForOtherLng.setColor3RGB(null);
        colorVOForOtherLng.setColor3Type(null);
        colorVOForOtherLng.setIsoLngCode("es");
 
        colorVOList.add(colorVOForOtherLng);
        return colorVOList;
 
    }
 
    private Mapping createMapping() {
        Mapping mapping = new Mapping();
        mapping.setGvuid("1da2e95e-3752-488b-a3c2-3ead130d8dfd");
        mapping.setVehicleSet("BaseAcode");
        mapping.setVehicleSetId("USC70AUS011A0");
        mapping.setWarehouseKey("USC70AUS011A0");
        return mapping;
 
    }
 
}