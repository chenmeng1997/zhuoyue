package com.cm.zhuouyue.common.utils.excelUtils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.cm.zhuouyue.common.utils.excelUtils.excelExportStyle.ExcelExportStyle;
import com.cm.zhuouyue.common.utils.excelUtils.hanlder.MyReadHandler;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 陈萌
 * @date 2021/6/11 14:18
 */
public class ExcelUtil {

    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * EXCEL 导出
     *
     * @param exportParams 导出参数/表格标题属性
     * @param pojoClass    Excel对象Class
     * @param dataset      Excel对象数据List
     * @param outputStream 输出流
     * @param <T>
     */
    public static <T> void exportExcel(ExportParams exportParams, Class<?> pojoClass, Collection<T> dataset, OutputStream outputStream) {
        if (exportParams == null) {
            exportParams = new ExportParams();
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, dataset);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error("EXCEL 导出异常 :{}", e);
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                log.error("EXCEL 导出IO关闭异常 :{}", e);
                e.printStackTrace();
            }
        }
    }

    public static <T> void exportExcel(Class<?> pojoClass, Collection<T> dataset, OutputStream outputStream) {
        exportExcel(null, pojoClass, dataset, outputStream);
    }

    /**
     * 大量数据导出
     */
    public static <T> void exportBigExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet, OutputStream outputStream) {
        if (entity == null) {
            entity = new ExportParams();
        }
        Workbook workbook = ExcelExportUtil.exportBigExcel(entity, pojoClass, dataSet);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error("bigExcel 导出异常 :{}", e);
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                workbook.close();
            } catch (IOException e) {
                log.error("bigExcel 导出IO关闭异常 :{}", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出多个sheet
     *
     * @param list
     * @param type
     * @return
     */
    public static void exportExcelWorkbook(List<Map<String, Object>> list, ExcelType type, OutputStream outputStream) throws IOException {
        Workbook workbook = null;
        switch (type) {
            case HSSF:
                workbook = new HSSFWorkbook();
                break;
            case XSSF:
                workbook = new XSSFWorkbook();
                break;
        }
        for (Map<String, Object> map : list) {
            ExcelExportService exportService = new ExcelExportService();
            exportService.createSheet(workbook, (ExportParams) map.get("title"), (Class<?>) map.get("entity"), (Collection<?>) map.get("data"));
        }
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    /**
     * @param sheetNum
     */
    /**
     * 单个sheet输出构造器
     *
     * @param templateUrl 模板路径
     * @param sheetName   sheet的名称
     * @param templateUrl 模板路径
     * @param sheetName
     * @param map         模板集合
     * @param listMap     数据列表
     * @param out         输出流
     * @param sheetNum    sheet的位置,可不填
     * @throws Exception
     */
    private static void templateExport(String templateUrl, String sheetName, Map<String, Object> map,
                                List<Map<String, String>> listMap, OutputStream out,
                                Integer... sheetNum) throws IOException {
        TemplateExportParams params = new TemplateExportParams(templateUrl, sheetName, sheetNum);
        map.put("maplist", listMap);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(out);
        workbook.close();
        out.close();
    }

    /**
     * 样式定义
     */
    private void excelExportStyle(Workbook workbook, Sheet sheet) {
        ExcelExportStyle style = new ExcelExportStyle();
        style.createStyle(workbook);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(ExcelExportStyle.class);
    }

    /**
     * 大数据导入
     */
    private void importExcelBySax(InputStream inputStream, Class<?> pojoClass, ImportParams params, IReadHandler hanlder, Integer titleRows) {

        try {
            if (hanlder == null) {
                hanlder = new MyReadHandler();
            }
            params.setTitleRows(titleRows);
            ExcelImportUtil.importExcelBySax(inputStream, pojoClass, params, hanlder);
        } catch (Exception e) {
            log.error("导入失败");
        }
    }

    /**
     * 返回单元格数据值
     *
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        Object cellValue = null;
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                // 数字
                case HSSFCell.CELL_TYPE_NUMERIC:
                    //判断单元格的类型是否则NUMERIC类型
                    if (0 == cell.getCellType()) {
                        // 判断是否为日期类型
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            cellValue = formater.format(date);
                        } else {
                            cellValue = cell.getNumericCellValue();
                        }
                    }
                    break;
                // 字符串
                case HSSFCell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                // Boolean
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    cellValue = cell.getBooleanCellValue();
                    break;
                // 公式
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                // 空值
                case HSSFCell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                // 故障
                case HSSFCell.CELL_TYPE_ERROR:
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }

}
