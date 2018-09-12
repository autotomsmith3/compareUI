package etl;
//package org.autodatacorp.cpp.etl.color.bolt;

import java.util.ArrayList;
import java.util.List;
 
import org.autodatacorp.cpp.etl.color.model.ColorVO;
import org.autodatacorp.cpp.etl.color.model.PaintTablesVO;
import org.junit.Assert;
import org.junit.Test;
 
import net.cds.cps.model.Description;
import net.cds.cps.model.option.Option;
import net.cds.cps.model.paint.ExteriorColor;
import net.cds.cps.model.paint.ExtraColor;
import net.cds.cps.model.paint.InteriorColor;
import net.cds.cps.model.paint.PaintRow;
import net.cds.cps.model.paint.RGB;
 
public class ColorBoltHelperTest {
 
    @Test
    public void testGenerateColorVOListForECCType1() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType1");
        PaintTablesVO paintTable = generateTestData("random");
        String lngCode = "en";
        
        List<ColorVO> colorList = colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
        
        Assert.assertNotNull(colorList);
        Assert.assertEquals(2, colorList.size());
        Assert.assertEquals("INTER-01", colorList.get(0).getColorId());
        Assert.assertEquals("EXTER-02+EXTER2-02", colorList.get(1).getColorId());
 
    }
 
    @Test
    public void testGenerateColorVOListForECCType2() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType2");
        PaintTablesVO paintTable = generateTestData("random");
        String lngCode = "es";
        
        List<ColorVO> colorList = colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
        
        Assert.assertNotNull(colorList);
        //color list will be zero if data is not present for 'es' langauage.
        Assert.assertEquals(1, colorList.size());
    }
 
    @Test
    public void testGenerateColorVOListForOther() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType3");
        PaintTablesVO paintTable = generateTestData("random");
        String lngCode = "en";
        List<ColorVO> colorList = colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
        
        Assert.assertNotNull(colorList);
        Assert.assertEquals(2, colorList.size());
        Assert.assertEquals("INTER-01", colorList.get(0).getColorId());
        Assert.assertEquals("EXTER-02+EXTER2-02", colorList.get(1).getColorId());
 
    }
 
    @Test
    public void testExteriorColorVOList() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType3");
        PaintTablesVO paintTable = generateTestData("exteriors");
        String lngCode = "es";
        colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
 
    }
 
    @Test
    public void testExtraColorVOList() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType3");
        PaintTablesVO paintTable = generateTestData("extras");
        String lngCode = "en";
        
        List<ColorVO> colorList = colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
        
        Assert.assertNotNull(colorList);
        Assert.assertEquals(1, colorList.size());
        Assert.assertEquals("INTER-01", colorList.get(0).getColorId());
 
 
    }
 
    @Test
    public void testWithTwoExteriorAndOneExtraColorVOList() {
 
        ColorBoltHelper colorBoltHelper = new ColorBoltHelper();
        List<ColorVO> colorVOList = new ArrayList<>();
        List<Option> optionsforECC = generateTestDataForOption("ECCType3");
        PaintTablesVO paintTable = generateTestData("exteriorandextra");
        String lngCode = "es";
        
        List<ColorVO> colorList = colorBoltHelper.generateColorVOList(colorVOList, optionsforECC, paintTable, lngCode);
        
        Assert.assertNotNull(colorList);
        //color list will be zero if data is not present for 'es' langauage.
        Assert.assertEquals(1, colorList.size());
    }
 
    private List<Option> generateTestDataForOption(String ECCType) {
 
        List<Option> optionForECC = new ArrayList<>();
        Option option = new Option();
        option.setOptCode("ECSW");
        option.setOptLevel("01");
 
        if (ECCType.equals("ECCType1")) {
            option.setECC("0082");
        } else if (ECCType.equals("ECCType2")) {
            option.setECC("0085");
        } else {
            option.setECC("0090");
        }
        optionForECC.add(option);
        return optionForECC;
 
    }
 
    private PaintTablesVO generateTestData(String testName) {
        PaintTablesVO paintTableVO = new PaintTablesVO();
        List<InteriorColor> interiorColorList = new ArrayList<>();
        List<ExteriorColor> exteriorColorList = new ArrayList<>();
        List<ExtraColor> extraColorsList = new ArrayList<>();
 
        if ("random".equals(testName)) {
            return generateTestDataWithRandomColors(paintTableVO, interiorColorList, exteriorColorList,
                    extraColorsList);
        } else if ("exteriors".equals(testName)) {
            return generateTestDataForExteriorColors(paintTableVO, interiorColorList, exteriorColorList,
                    extraColorsList);
        } else if ("extras".equals(testName)) {
            return generateTestDataForExtraColors(paintTableVO, interiorColorList, exteriorColorList, extraColorsList);
        } else if ("exteriorandextra".equals(testName)) {
            return generateTestDataForExteriorAndExtraColors(paintTableVO, interiorColorList, exteriorColorList,
                    extraColorsList);
        } else {
            return generateTestDataWithRandomColors(paintTableVO, interiorColorList, exteriorColorList,
                    extraColorsList);
        }
    }
 
    private PaintTablesVO generateTestDataWithRandomColors(PaintTablesVO paintTableVO,
            List<InteriorColor> interiorColorList, List<ExteriorColor> exteriorColorList,
            List<ExtraColor> extraColorsList) {
 
        Description admsDescription = new Description();
        Description longDescription = new Description();
        RGB rgb = new RGB();
        rgb.setRed(6);
        rgb.setGreen(6);
        InteriorColor interiorColor = createInteriorColor(admsDescription, longDescription, rgb);
        interiorColorList.add(interiorColor);
 
        ExteriorColor exteriorColor1 = createExteriorColor(admsDescription, longDescription, rgb);
        exteriorColorList.add(exteriorColor1);
 
        ExteriorColor exteriorColor2 = createExteriorColor("EXTER2", admsDescription, longDescription, rgb);
        exteriorColorList.add(exteriorColor2);
 
        ExtraColor extraColor1 = createExtraColor(admsDescription, longDescription, rgb);
        extraColorsList.add(extraColor1);
 
        return generatePaintTablesVO(interiorColorList, exteriorColorList, extraColorsList);
    }
 
    private PaintTablesVO generateTestDataForExtraColors(PaintTablesVO paintTableVO,
            List<InteriorColor> interiorColorList, List<ExteriorColor> exteriorColorList,
            List<ExtraColor> extraColorsList) {
 
        Description admsDescription = new Description();
        Description longDescription = new Description();
        RGB rgb = new RGB();
        rgb.setRed(6);
        rgb.setGreen(6);
        InteriorColor interiorColor = createInteriorColor(admsDescription, longDescription, rgb);
        interiorColorList.add(interiorColor);
 
        ExtraColor extraColor1 = createExtraColor(admsDescription, longDescription, rgb);
        ExtraColor extraColor2 = createExtraColor("EXTRA2", admsDescription, longDescription, rgb);
        ExtraColor extraColor3 = createExtraColor("EXTRA3", admsDescription, longDescription, rgb);
        extraColorsList.add(extraColor1);
        extraColorsList.add(extraColor2);
        extraColorsList.add(extraColor3);
 
        return generatePaintTablesVO(interiorColorList, exteriorColorList, extraColorsList);
    }
 
    private PaintTablesVO generateTestDataForExteriorColors(PaintTablesVO paintTableVO,
            List<InteriorColor> interiorColorList, List<ExteriorColor> exteriorColorList,
            List<ExtraColor> extraColorsList) {
 
        Description admsDescription = new Description();
        Description longDescription = new Description();
        RGB rgb = new RGB();
        rgb.setRed(6);
        rgb.setGreen(6);
        InteriorColor interiorColor = createInteriorColor(admsDescription, longDescription, rgb);
        interiorColorList.add(interiorColor);
 
        ExteriorColor exteriorColor1 = createExteriorColor(admsDescription, longDescription, rgb);
        ExteriorColor exteriorColor2 = createExteriorColor("EXTER2", admsDescription, longDescription, rgb);
        exteriorColorList.add(exteriorColor1);
        exteriorColorList.add(exteriorColor2);
 
        ExtraColor extraColor1 = createExtraColor(admsDescription, longDescription, rgb);
        extraColorsList.add(extraColor1);
 
        return generatePaintTablesVO(interiorColorList, exteriorColorList, extraColorsList);
    }
 
    private PaintTablesVO generateTestDataForExteriorAndExtraColors(PaintTablesVO paintTableVO,
            List<InteriorColor> interiorColorList, List<ExteriorColor> exteriorColorList,
            List<ExtraColor> extraColorsList) {
 
        Description admsDescription = new Description();
        Description longDescription = new Description();
        RGB rgb = new RGB();
        rgb.setRed(6);
        rgb.setGreen(6);
        InteriorColor interiorColor = createInteriorColor(admsDescription, longDescription, rgb);
        interiorColorList.add(interiorColor);
 
        ExteriorColor exteriorColor1 = createExteriorColor(admsDescription, longDescription, rgb);
        ExteriorColor exteriorColor2 = createExteriorColor("EXTER2", admsDescription, longDescription, rgb);
        ExteriorColor exteriorColor3 = createExteriorColor("EXTER3", admsDescription, longDescription, rgb);
        exteriorColorList.add(exteriorColor1);
        exteriorColorList.add(exteriorColor2);
        exteriorColorList.add(exteriorColor3);
 
        return generatePaintTablesVO(interiorColorList, exteriorColorList, extraColorsList);
    }
 
    private InteriorColor createInteriorColor(Description admsDescription, Description longDescription, RGB rgb) {
        InteriorColor interiorColor = new InteriorColor();
        admsDescription.putDescription("EN", "Black");
        admsDescription.putDescription("EN", "Black");
        interiorColor.setAdmsDescription(admsDescription);
        interiorColor.setLongDescription(longDescription);
        interiorColor.setColorCode("INTER");
        interiorColor.setColorVar("01");
        interiorColor.setRgb(rgb);
        return interiorColor;
    }
 
    
    private ExteriorColor createExteriorColor(Description admsDescription, Description longDescription, RGB rgb) {
      return createExteriorColor("EXTER", admsDescription, longDescription, rgb);
  }
    
    private ExteriorColor createExteriorColor(String code, Description admsDescription, Description longDescription, RGB rgb) {
        ExteriorColor exteriorColor = new ExteriorColor();
        admsDescription.putDescription("EN", "Black");
        admsDescription.putDescription("EN", "Black");
        exteriorColor.setAdmsDescription(admsDescription);
        exteriorColor.setColorCode(code);
        exteriorColor.setColorVar("02");
        exteriorColor.setLongDescription(longDescription);
        exteriorColor.setRgb(rgb);
        return exteriorColor;
    }
 
    private ExtraColor createExtraColor(Description admsDescription, Description longDescription, RGB rgb) {
      return createExtraColor("EXTRA", admsDescription, longDescription, rgb);
    }
    private ExtraColor createExtraColor(String code, Description admsDescription, Description longDescription, RGB rgb) {
        ExtraColor extraColor = new ExtraColor();
        extraColor.setAdmsDescription(admsDescription);
        extraColor.setColorCode(code);
        extraColor.setColorVar("03");
        extraColor.setLongDescription(longDescription);
        extraColor.setRgb(rgb);
        return extraColor;
    }
 
    private PaintTablesVO generatePaintTablesVO(List<InteriorColor> interiorColorList,
            List<ExteriorColor> exteriorColorList, List<ExtraColor> extraColorsList) {
        PaintTablesVO paintTableVO = new PaintTablesVO();
        PaintRow paintRow = new PaintRow();
        paintRow.setExteriors((ArrayList<ExteriorColor>) exteriorColorList);
        paintRow.setExtras((ArrayList<ExtraColor>) extraColorsList);
        List<PaintRow> paintRowList = new ArrayList<>();
        paintRowList.add(paintRow);
        paintTableVO.setInteriors(interiorColorList);
        paintTableVO.setRows(paintRowList);
        return paintTableVO;
    }
 
}
