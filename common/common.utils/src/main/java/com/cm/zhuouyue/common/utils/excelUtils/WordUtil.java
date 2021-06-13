package com.cm.zhuouyue.common.utils.excelUtils;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.afterturn.easypoi.word.entity.params.ExcelListEntity;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author 陈萌
 * @date 2021/6/13 23:32
 */
public class WordUtil {

    /**
     * 导出Word map数据
     *
     * @param url          模板路径 docx格式
     * @param map          Word 信息
     * @param outputStream 输出流
     */
    public static void exportWord07(String url, Map<String, Object> map, OutputStream outputStream) {
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(url, map);
            doc.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出Word 指定实体类
     *
     * @param url          模板URL
     * @param map          模板信息
     * @param mapList      列表数据
     * @param classz       实体类
     * @param outputStream 输出流
     */
    public static void exportWord07(String url, Map<String, Object> map, List<?> mapList,
                                    Class<?> classz, OutputStream outputStream) {
        map.put("datalist", new ExcelListEntity(mapList, classz));
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(url, map);
            doc.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
