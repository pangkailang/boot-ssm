package com.pkl.boot.common.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.pkl.boot.common.Listener.DemoDataListener;
import com.pkl.boot.entity.Dept;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {
    public static void main(String[] args) {
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("aaa");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellType cellType = cell.getCellType();
        try {
            long start = System.currentTimeMillis();
            String fileName = "aaa.xlsx";
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            String fileName1 = "bbb.xlsx";
            excelWriter = EasyExcel.write(fileName1, Dept.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(is, Dept.class, new DemoDataListener(excelWriter,writeSheet)).sheet().doRead();


//            for (int i = 0; i <5 ; i++) {
//                excelWriter.write(data(), writeSheet);
//            }

            System.out.println("解析时间为："+(System.currentTimeMillis()-start));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<Dept> data(){
        List<Dept> list = new ArrayList<>();
        for (int i=0;i<100000;i++){
            Dept dept = new Dept();
            dept.setId("1"+i);
            dept.setName("2"+i);
            dept.setValue1("xxx"+i);
            dept.setValue2("xxx"+i);
            dept.setValue3("xxx"+i);
            dept.setValue4("xxx"+i);
            dept.setValue5("xxx"+i);
            dept.setValue6("xxx"+i);
            dept.setValue7("xxx"+i);
            dept.setValue8("xxx"+i);
            dept.setValue9("xxx"+i);
            dept.setValue10("xxxyyrwey"+i);
            dept.setValue11("xxxyrwerwyy"+i);
            dept.setValue12("xxxwrwyyy"+i);
            dept.setValue13("xxxyywewy"+i);
            dept.setValue14("xxxyywefwy"+i);
            dept.setValue15("xxxyfwyy"+i);
            dept.setValue16("xxxrf2yyy"+i);
            dept.setValue17("xxxyfwyy"+i);
            dept.setValue18("xxx3242yyy"+i);
            dept.setValue19("xxxyy12y"+i);
            dept.setValue20("xxxy2332yy"+i);
            dept.setValue21("xxxy232yy"+i);
            dept.setValue22("xxxyy32y"+i);
            dept.setValue23("xxxy23yy"+i);
            dept.setValue24("xxxy232yy"+i);
            dept.setValue111("xxxyyy"+i);
            dept.setValue112("xxxyy232y"+i);
            dept.setValue113("xxxy23yy"+i);
            dept.setValue114("xxxy232yy"+i);
            dept.setValue115("xxxy232yy"+i);
            dept.setValue116("xxxy232y2y"+i);
            dept.setValue117("xxx232yy32y"+i);
            dept.setValue118("xxxy23r23yy"+i);
            list.add(dept);
        }
        return list;
    }
}
