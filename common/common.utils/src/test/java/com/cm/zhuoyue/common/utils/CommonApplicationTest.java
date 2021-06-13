package com.cm.zhuoyue.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.cm.zhuouyue.common.utils.CommonApplication;
import com.cm.zhuouyue.common.utils.excelUtils.ExcelUtil;
import com.cm.zhuoyue.common.utils.excelDemo.Hobby;
import com.cm.zhuoyue.common.utils.excelDemo.User;
import com.cm.zhuoyue.common.utils.excelDemo.Wife;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author 陈萌
 * @date 2021/6/11 14:21
 */
@SpringBootTest(classes = {CommonApplication.class})
public class CommonApplicationTest {

    OutputStream outputStream = null;
    Workbook workbook = null;

    /**
     * 单个sheet 一行表头
     */
    @Test
    void simpleTest() throws IOException{
        try {
            List<User> userList = getUserList();
            outputStream = new FileOutputStream("D:\\POI测试\\POI测试.xlsx");
//            workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), User.class, userList);
            ExportParams exportParams = new ExportParams();
            exportParams.setSheetName("用户");
            workbook = ExcelExportUtil.exportExcel(exportParams, User.class, userList);
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            workbook.close();
            outputStream.close();
        }
    }
    @Test
    void simpleTest1() throws IOException{
        outputStream = new FileOutputStream("D:\\POI测试\\POI测试.xlsx");
        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName("SheetName");
        ExcelUtil.exportExcel(null, User.class, getUserList(), outputStream);
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            Wife wife = new Wife();
            wife.setAge(i);
            wife.setName("女"+i);
            user.setName(String.valueOf("小陈") + i);
            user.setSex(i % 2);
            user.setBirthday(new Date());
            user.setHobbyList(getHobbyList());
            user.setWife(wife);
            list.add(user);
        }
        return list;
    }

    private List<Hobby> getHobbyList() {
        List<Hobby> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Hobby hobby = new Hobby();
            hobby.setHobbyName("爱好"+i);
            hobby.setHobbyDepict("描述"+i);
            list.add(hobby);
        }
        return list;
    }

    /**
     * 模板导出
     * @throws Exception
     */
    @Test
    public void fe_map() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "WEB-INF/doc/专项支出用款申请书_map.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
        workbook.write(fos);
        fos.close();
    }

}
