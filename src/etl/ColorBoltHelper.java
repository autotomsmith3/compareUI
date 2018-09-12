package etl;

//package org.autodatacorp.cpp.etl.color.bolt;

import java.util.List;
 
import org.autodatacorp.cpp.etl.color.model.ColorVO;
import org.autodatacorp.cpp.etl.color.model.PaintTablesVO;
import org.autodatacorp.cpp.etl.cps.Languages;
import net.cds.cps.model.option.Option;
import net.cds.cps.model.paint.BaseColor;
import net.cds.cps.model.paint.ExteriorColor;
import net.cds.cps.model.paint.ExtraColor;
import net.cds.cps.model.paint.InteriorColor;
import net.cds.cps.model.paint.PaintRow;
 
/**
 * Helper class to aid Color Bolt to deal with addition of CPS Response To
 * ColorVO
 * @author yashbhan.singh
 *
 */
public class ColorBoltHelper {
 
    /**
     * generateColorVOList
     * @param colorVOList
     * @param optionsForEcc
     * @param paintTable
     * @param lngCode
     * @return
     */
    public List<ColorVO> generateColorVOList(List<ColorVO> colorVOList, List<Option> optionsForEcc,
            PaintTablesVO paintTable, String lngCode) {
 
        generateColorVOListForInteriors(colorVOList, paintTable, lngCode);
        generateColorVOListForExteriors(colorVOList, paintTable, optionsForEcc, lngCode);
 
        return colorVOList;
    }
 
    private void generateColorVOListForInteriors(List<ColorVO> colorVOList, PaintTablesVO paintTable, String lngCode) {
 
        String cpsLang = Languages.toCps(lngCode);
        for (InteriorColor interiorColor : paintTable.getInteriors()) {
 
            ColorVO colorVOForInterior = new ColorVO();
            colorVOForInterior.setIsoLngCode(lngCode);
            colorVOForInterior.setColorType("I");
            colorVOForInterior.setColor1Type("I");
            if (interiorColor.getShortDescription() != null
                    && interiorColor.getShortDescription().getExactDescription(cpsLang) != null) {
                colorVOForInterior.setColor1Desc(interiorColor.getShortDescription().getExactDescription(cpsLang));
 
            } else {
                continue;
            }
            if (interiorColor.getAdmsDescription() != null) {
                colorVOForInterior
                        .setColor1GenericDesc(interiorColor.getAdmsDescription().getExactDescription(cpsLang));
            }
            colorVOForInterior.setColorCode1(interiorColor.getColorCode());
            colorVOForInterior.setColorId(interiorColor.getCodeAndVar());
 
            if (interiorColor.getRgb() != null) {
                colorVOForInterior.setColor1RGB(
                        getHexValue(interiorColor.getRgb().getRed()) + getHexValue(interiorColor.getRgb().getGreen())
                                + getHexValue(interiorColor.getRgb().getBlue()));
            }
 
            if (!(colorVOList.stream()
                    .anyMatch(color -> color.getColorId().equalsIgnoreCase(colorVOForInterior.getColorId())
                            && color.getIsoLngCode().equals(colorVOForInterior.getIsoLngCode())))) {
                colorVOList.add(colorVOForInterior);
            }
        }
    }
 
    private void generateColorVOListForExteriors(List<ColorVO> colorVOList, PaintTablesVO paintTable,
            List<Option> optionsForEcc, String lngCode) {
 
        String cpsLang = Languages.toCps(lngCode);
        for (PaintRow paintRow : paintTable.getRows()) {
            ColorVO colorVOForExterior = new ColorVO();
            colorVOForExterior.setIsoLngCode(lngCode);
            colorVOForExterior.setColorType("E");
            colorVOForExterior.setColor1Type("E");
            int counter = 0;
            String colorIdForGridRows = "";
            if (!paintRow.getExteriors().isEmpty()) {
                colorVOForExterior = setPrimaryExterior(colorVOForExterior, paintRow.getExteriors().get(0), cpsLang);
                counter = counter + 1;
                colorIdForGridRows = generateColorIdForExteriors(colorIdForGridRows, counter,
                        paintRow.getExteriors().get(0));
                paintRow.getExteriors().remove(0);
 
            }
            if (colorVOForExterior != null) {
                // traverse the exteriors and extra to fill the exterior VOs
                for (ExteriorColor exteriorColor : paintRow.getExteriors()) {
                    if (checkLngSpecificData(exteriorColor, cpsLang)) {
                        counter = setExteriorColors(colorVOForExterior, exteriorColor, counter, optionsForEcc, cpsLang);
                        if (counter == 3) {
                            break;
                        }
                        colorIdForGridRows = generateColorIdForExteriors(colorIdForGridRows, counter, exteriorColor);
                    }
                }
                for (ExtraColor extraColor : paintRow.getExtras()) {
                    if (checkLngSpecificData(extraColor, cpsLang)) {
                        counter = setExtraColors(colorVOForExterior, extraColor, counter, optionsForEcc, cpsLang);
                        if (counter == 3) {
                            break;
                        }
                        colorIdForGridRows = generateColorIdForExtras(colorIdForGridRows, counter, extraColor);
                    }
                }
 
                colorVOForExterior.setColorId(colorIdForGridRows);
                final ColorVO colorVO = colorVOForExterior;
                if (colorVO.getColorCode1() != null && !(colorVOList.stream()
                        .anyMatch(color -> color.getColorId().equalsIgnoreCase(colorVO.getColorId())
                                && color.getIsoLngCode().equals(colorVO.getIsoLngCode())))) {
                    colorVOList.add(colorVO);
                }
            }
 
 
 
 
 
 
        }
 
    }
 
    private String generateColorIdForExteriors(String colorIdForGridRow, int counter, ExteriorColor exteriorColor) {
        String colorId;
        if (counter == 1) {
            colorId = exteriorColor.getCodeAndVar();
        } else {
            colorId = colorIdForGridRow.concat("+" + exteriorColor.getCodeAndVar());
        }
        return colorId;
    }
 
    private String generateColorIdForExtras(String colorIdForGridRow, int counter, ExtraColor extraColor) {
        String colorId;
        if (counter == 1) {
            colorId = extraColor.getCodeAndVar();
        } else {
            colorId = colorIdForGridRow.concat("||" + extraColor.getCodeAndVar());
        }
        return colorId;
    }
 
    private String findECC(String optCode, List<Option> optionsForEcc) {
        Option option = findOption(optCode, optionsForEcc);
        String eccVal;
        if (option != null) {
            eccVal = option.getECC();
            if ("0160".equals(eccVal)) {
                return "ES";
            } else if ("0085".equals(eccVal)) {
                return "ST";
            } else {
                return "AC";
            }
        }
        return "AC";
    }
 
    private Option findOption(String optCode, List<Option> optionsForEcc) {
        for (Option option : optionsForEcc) {
            if (option.getOptCode().equals(optCode)) {
                return option;
            }
        }
        return null;
    }
 
    private ColorVO setPrimaryExterior(ColorVO colorVOForExterior, ExteriorColor exteriorColor, String cpsLang) {
        if (exteriorColor.getShortDescription() != null
                || exteriorColor.getShortDescription().getExactDescription(cpsLang) != null) {
            colorVOForExterior.setColorCode1(exteriorColor.getColorCode());
            colorVOForExterior.setColor1Desc(exteriorColor.getShortDescription().getExactDescription(cpsLang));
            colorVOForExterior.setColor1GenericDesc(exteriorColor.getAdmsDescription().getExactDescription(cpsLang));
 
 
 
 
 
 
 
 
            if (exteriorColor.getRgb() != null) {
                colorVOForExterior.setColor1RGB(
                        getHexValue(exteriorColor.getRgb().getRed()) + getHexValue(exteriorColor.getRgb().getGreen())
                                + getHexValue(exteriorColor.getRgb().getBlue()));
            }
            return colorVOForExterior;
        }
        return null;
    }
 
    private int setExteriorColors(ColorVO colorVOForExteriors, ExteriorColor exteriorColor, int counter,
            List<Option> optionsForEcc, String cpsLang) {
        if (counter == 1) {
            colorVOForExteriors.setColorCode2(exteriorColor.getColorCode());
            String color2TypeForExteriors = findECC(exteriorColor.getColorCode(), optionsForEcc);
            colorVOForExteriors.setColor2Type(color2TypeForExteriors);
            colorVOForExteriors.setColor2Desc(exteriorColor.getShortDescription().getExactDescription(cpsLang));
            colorVOForExteriors.setColor2GenericDesc(exteriorColor.getAdmsDescription().getExactDescription(cpsLang));
 
 
 
            if (exteriorColor.getRgb() != null) {
                colorVOForExteriors.setColor2RGB(Integer.toHexString(exteriorColor.getRgb().getRed())
                        + Integer.toHexString(exteriorColor.getRgb().getGreen())
                        + Integer.toHexString(exteriorColor.getRgb().getBlue()));
            }
        } else if (counter == 2) {
            colorVOForExteriors.setColorCode3(exteriorColor.getColorCode());
            String color3TypeForExteriors = findECC(exteriorColor.getColorCode(), optionsForEcc);
            colorVOForExteriors.setColor3Type(color3TypeForExteriors);
            colorVOForExteriors.setColor3Desc(exteriorColor.getShortDescription().getExactDescription(cpsLang));
            colorVOForExteriors.setColor3GenericDesc(exteriorColor.getAdmsDescription().getExactDescription(cpsLang));
 
 
 
            if (exteriorColor.getRgb() != null) {
                colorVOForExteriors.setColor3RGB(
                        getHexValue(exteriorColor.getRgb().getRed()) + getHexValue(exteriorColor.getRgb().getGreen())
                                + getHexValue(exteriorColor.getRgb().getBlue()));
            }
        }
        counter++;
 
        return counter;
    }
 
    private int setExtraColors(ColorVO colorVOForExteriors, ExtraColor extraColor, int counter,
            List<Option> optionsForEcc, String cpsLang) {
        if (counter == 1) {
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
            colorVOForExteriors.setColorCode2(extraColor.getColorCode());
            colorVOForExteriors.setColorCode2(extraColor.getColorCode());
            String color2TypeForExteriors = findECC(extraColor.getColorCode(), optionsForEcc);
            colorVOForExteriors.setColor2Type(color2TypeForExteriors);
            colorVOForExteriors.setColor2Desc(extraColor.getShortDescription().getExactDescription(cpsLang));
            colorVOForExteriors.setColor2GenericDesc(extraColor.getAdmsDescription().getExactDescription(cpsLang));
 
 
 
            if (extraColor.getRgb() != null) {
                colorVOForExteriors.setColor2RGB(getHexValue(extraColor.getRgb().getRed())
                        + getHexValue(extraColor.getRgb().getGreen()) + getHexValue(extraColor.getRgb().getBlue()));
            }
        } else if (counter == 2) {
            colorVOForExteriors.setColorCode3(extraColor.getColorCode());
            String color3TypeForExteriors = findECC(extraColor.getColorCode(), optionsForEcc);
            colorVOForExteriors.setColor3Type(color3TypeForExteriors);
            colorVOForExteriors.setColor3Desc(extraColor.getShortDescription().getExactDescription(cpsLang));
            colorVOForExteriors.setColor3GenericDesc(extraColor.getAdmsDescription().getExactDescription(cpsLang));
 
 
 
            if (extraColor.getRgb() != null) {
                colorVOForExteriors.setColor3RGB(Integer.toHexString(extraColor.getRgb().getRed())
                        + Integer.toHexString(extraColor.getRgb().getGreen())
                        + Integer.toHexString(extraColor.getRgb().getBlue()));
            }
        }
        counter++;
        return counter;
    }
 
    private String getHexValue(Integer intVal) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(intVal));
        if (sb.length() < 2) {
            sb.insert(0, '0'); // pad with leading zero if needed
        }
        return sb.toString();
    }
 
    private boolean checkLngSpecificData(BaseColor color, String lngCode) {
        return color.getShortDescription() != null
                && color.getShortDescription().getExactDescription(lngCode) != null;
    }
 
}