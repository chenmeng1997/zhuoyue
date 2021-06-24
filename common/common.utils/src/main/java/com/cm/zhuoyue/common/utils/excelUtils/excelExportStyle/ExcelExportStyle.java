package com.cm.zhuoyue.common.utils.excelUtils.excelExportStyle;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

/**
 * 样式定义
 *
 * @author 陈萌
 * @date 2021/6/13 16:20
 */
public class ExcelExportStyle extends AbstractExcelExportStyler implements IExcelExportStyler {
    protected Workbook workbook;

    public void createStyle(Workbook workbook) {
        this.stringNoneStyle = stringNoneStyle(workbook, false);
        this.stringNoneWrapStyle = stringNoneStyle(workbook, true);
        this.stringSeptailStyle = stringSeptailStyle(workbook, false);
        this.stringSeptailWrapStyle = stringSeptailStyle(workbook, true);
        this.workbook = workbook;
    }

    /**
     * 列表头样式
     *
     * @param headerColor
     * @return
     */
    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 24);
        titleStyle.setFont(font);
        titleStyle.setFillForegroundColor(headerColor);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return titleStyle;
    }

    /**
     * 标题样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setDataFormat(STRING_FORMAT);
        style.setWrapText(true);
        return style;
    }

    /**
     * 获取样式方法
     *
     * @param parity
     * @param entity
     * @return
     */
    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        return null;
    }

    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return null;
    }
}
