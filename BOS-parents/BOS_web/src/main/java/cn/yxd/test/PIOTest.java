package cn.yxd.test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PIOTest {
    @Test
    public  void fun1() throws IOException {
        String path="D:\\新建文件夹\\项目一：物流BOS系统.（58-71天）\\BOS-day05\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls";
        //包装Excal的工具类
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(new File(path)));
        //读取第一个额sheet页
        HSSFSheet sheet=hssfWorkbook.getSheetAt(0);
        //遍历标签页中所有的行
        for (Row r:sheet){
            System.out.println(" ");
            //遍历行中的所有数据
            for (Cell c:r){
                //跳出标题
                int rowNum = r.getRowNum();
                if(rowNum==0){
                   continue;
                }else {
                    String stringCellValue = c.getStringCellValue();
                    //获取数据
                    System.out.print(stringCellValue+"  ");
                }

            }
        }



    }
}
