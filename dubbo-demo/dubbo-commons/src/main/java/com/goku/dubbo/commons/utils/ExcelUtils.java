package com.goku.dubbo.commons.utils;

import com.goku.dubbo.commons.excel.Style;
import com.goku.dubbo.commons.excel.Title;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author fuyongde
 * @desc 操作Excel
 * @date 2017/11/29 16:38
 */
public class ExcelUtils {

    /**
     * 创建excel文件
     *
     * @param sheetNames sheet名称
     * @param titles     每个Sheet的标题名称
     * @param datas      每个Sheet的数据
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] createExcel(List<String> sheetNames, List<Title[]> titles, List<List<Map<String, String>>> datas) throws IOException {
        //创建Workbook
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        if (CollectionUtils.isNotEmpty(sheetNames)) {

            for (int i = 0; i < sheetNames.size(); i++) {
                //创建工作表
                String sheetName = sheetNames.get(i);
                SXSSFSheet sheet = workbook.createSheet(sheetName);

                //获取当前sheet的标题
                Title[] title = titles.get(i);

                //第0行，创建标题
                SXSSFRow titleRow = sheet.createRow(0);
                titleRow.setHeightInPoints(Style.defaultTitleRowHeight);
                for (int j = 0; j < title.length; j++) {
                    SXSSFCell titleCell = titleRow.createCell(j);
                    titleCell.setCellValue(title[j].getTitleName());
                    titleCell.setCellStyle(Style.defaultTitleCellStyle(workbook));

                    sheet.setColumnWidth(j, Style.defaultCellWidth);
                }

                //第1行开始，写入数据
                List<Map<String, String>> currentSheetData = datas.get(i);
                for (int j = 1; j <= currentSheetData.size(); j++) {
                    SXSSFRow dataRow = sheet.createRow(j);

                    Map<String, String> currentRowData = currentSheetData.get(j - 1);

                    for (int k = 0; k < title.length; k++) {
                        String key = title[k].getTitleKey();
                        SXSSFCell dataCell = dataRow.createCell(k);
                        dataCell.setCellValue(currentRowData.get(key));
                    }

                    sheet.setColumnWidth(j, Style.defaultCellWidth);
                }

            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }

    /**
     * 将数据写入到excel
     *
     * @param directory  目录
     * @param fileName   文件名
     * @param sheetNames sheet名称
     * @param titles     标题
     * @param datas      源数据
     *
     * @throws IOException
     */
    public static void createExcel(String directory, String fileName, List<String> sheetNames, List<Title[]> titles,
                                   List<List<Map<String, String>>> datas) throws IOException {
        byte[] bytes = createExcel(sheetNames, titles, datas);
        File file = new File(directory, fileName);
        FileUtils.writeByteArrayToFile(file, bytes);
    }

}
