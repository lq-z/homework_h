package com.homeworkh.usermanagementserver.upload;

import com.homeworkh.usermanagementserver.entity.AlarmDevice;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lq
 * @date 2021/1/6 14:23
 */
@Slf4j
public class ExportExcel {

    public static void main(String[] args) throws IOException {
        List<AlarmDevice> deviceList = new ArrayList<>();
       for (int i=0;i<1000;i++){
           Random  random = new Random();
           String number = "33";
           for (int j=0;j<2;j++){
               number +=random.nextInt(4);
           }

           AlarmDevice  alarmDevice = new AlarmDevice();
           alarmDevice.setNumber(number);
           alarmDevice.setIsOpen("在线");
           deviceList.add(alarmDevice);
       }


        String title[] = {"设备编号", "设备状态"};
        String path = "D:\\opt";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss_S");
        String fileName = formatter.format(Calendar.getInstance().getTime());
        String fileType = "xlsx";

        writeData(path,fileName,fileType,deviceList,title);
    }

   private static File writeData(String path, String fileName, String fileType, List<AlarmDevice> list,
                                 String titleRow[]) throws IOException {
       Workbook wb = null;
       File directoryFile = new File(path);
       if (!directoryFile.exists()) {
           directoryFile.mkdirs();
       }
       String excelPath = path + File.separator + fileName + "." + fileType;
       File file = new File(excelPath);
       Sheet sheet = null;
       // 创建工作文档对象
       if (!file.exists()) {
           if (fileType.equals("xls")) {
               wb = new HSSFWorkbook();
           } else if (fileType.equals("xlsx")) {
               wb = new XSSFWorkbook();
           }
           sheet = (Sheet) wb.createSheet("Sheet1");
       } else {
           if (fileType.equals("xls")) {
               wb = new HSSFWorkbook();
           } else if (fileType.equals("xlsx")) {
               wb = new XSSFWorkbook();
           } else {
               // throw new SimpleException("文件格式不正确");
           }
       }
       // 创建sheet对象
       if (sheet == null) {
           sheet = (Sheet) wb.createSheet("导入数据分析");
       }

       // 添加表头
       Row row = sheet.createRow(0);
       Cell cell = row.createCell(0);
       row.setHeight((short) 540);
//       cell.setCellValue("导入数据分析"); // 创建第一行
       CellStyle style = wb.createCellStyle(); // 样式对象
       // 设置单元格的背景颜色为淡蓝色
       style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
       style.setAlignment(HorizontalAlignment.CENTER);// 水平
       style.setWrapText(true);// 指定当单元格内容显示不下时自动换行
//       cell.setCellStyle(style); // 样式，居中
       Font font = wb.createFont();
       font.setBold(true);
       font.setFontName("宋体");
       font.setFontHeight((short) 280);
       style.setFont(font);
       // 单元格合并
       // 四个参数分别是：起始行，起始列，结束行，结束列
       sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
       sheet.autoSizeColumn(5200);
       row = sheet.createRow(1); // 创建第二行 header
       for (int i = 0; i < titleRow.length; i++) {
           cell = row.createCell(i);
           cell.setCellValue(titleRow[i]);
           cell.setCellStyle(style); // 样式，居中
           sheet.setColumnWidth(i , 20 * 256);
       }
       row.setHeight((short) 540);
       // 循环写入行数据
       for (int i = 0; i < list.size(); i++) {
           row = (Row) sheet.createRow(i + 2);
           row.setHeight((short) 500);
           row.createCell(0).setCellValue(list.get(i).getNumber());
           row.createCell(1).setCellValue(list.get(i).getIsOpen());
       }
       OutputStream stream = new FileOutputStream(excelPath);
       // 创建文件流
       // 写入数据
       wb.write(stream);
       // 关闭文件流
       stream.close();
       return file;
   }

    public static List<String> readDeviceStateExcelData(InputStream inputStream) throws IOException, NumberFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet worksheet = workbook.getSheet("Sheet1");
        int lastRowNum = worksheet.getLastRowNum();

        List<String> alarmDeviceList = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = worksheet.getRow(i);
            try {
                String alarmDeviceNumCellVal = "";
                if (HSSFCell.CELL_TYPE_STRING == row.getCell(0).getCellType()) {
                    alarmDeviceNumCellVal = row.getCell(0).getStringCellValue();
                } else {
                    alarmDeviceNumCellVal = row.getCell(0).getRawValue();
                }
                String alarmDeviceNameCellVal = row.getCell(1).getStringCellValue();

                alarmDeviceList.add(alarmDeviceNumCellVal);
                alarmDeviceList.add(alarmDeviceNameCellVal);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return alarmDeviceList;
    }
}
