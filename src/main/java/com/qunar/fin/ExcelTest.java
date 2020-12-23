package com.qunar.fin;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2019/11/26 11:59
 */
public class ExcelTest {
    private static final String WRITE_DIR = "D:\\sql1.txt";

    private static final String READ_DIR = "D:\\sqllab_untitled_query_20191126T063450.xlsx";

    private static final String SQL = "update tbl_loan_notify_info set notice_first_time='" + getDate() + "', notice_count=0, version=0 where loan_notify_id=";


    public static void main(String[] args) {

//        writeContent(READ_DIR);

        readExcel(READ_DIR, WRITE_DIR);
    }

    public static String getDate() {
        long currentTime = System.currentTimeMillis() ;
        currentTime +=30*60*1000;
        Date date=new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }

    public static void writeContent(String dir) {
        int number=1;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            os = new FileOutputStream(dir);
            pw=new PrintWriter(os);
            for(int i=0;i<10;i++) {
                String s=""+number;
                //每输入一个数据，自动换行，便于我们每一行每一行地进行读取
                pw.println(s);
                //pw.print(s+",");//不会自动换行，必要时可以自己添加分隔符
                number++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void readExcel(String readDir, String writeDir) {
        //excel文件路径
        String excelPath = readDir;

        try {
            OutputStream os = new FileOutputStream(writeDir);
            PrintWriter pw = new PrintWriter(os);
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);
//                    wb = new XSSFWorkbook(fis);
                    wb = WorkbookFactory.create(fis);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
//                                System.out.println(cell.toString());
                                String sqlStr = SQL + "'" + cell.toString() + "';";
                                System.out.println(sqlStr);
                                pw.println(sqlStr);
                            }
                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
