package com.sale.panda.manager.utils;

import com.sale.panda.manager.entity.ExcelModel;
import com.sale.panda.manager.constants.ExcelTitleMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ExcelUtils {

    private static final String EXCEL2003L = ".xls";
    /**
     * 2007+ 版本的excel
     */
    private static final String EXCEL2007U = ".xlsx";

    /**
     * 导出Excel
     *
     * @param excelModel excelModel模型
     * @return
     */
    public static XSSFWorkbook getHSSFWorkbook(ExcelModel excelModel) {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(excelModel.getSheetName());

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //声明列对象
        XSSFCell cell = null;
        String[] title = excelModel.getTitle().toArray(new String[1]);
        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        int i = 0;
        if (CollectionUtils.isEmpty(excelModel.getContextList())) {
            return wb;
        }
        for (Map<String, String> map : excelModel.getContextList()) {
            if (map.size() == 0) {
                continue;
            }
            row = sheet.createRow(++i);
            int j = 0;
            for (Object cellStr : map.values()) {
                row.createCell(j++).setCellValue((String) cellStr);
            }
        }
        return wb;
    }


    /**
     * 将流中的Excel数据转成List<Map>(读取Excel)
     *
     * @param in       输入流
     * @param fileName 文件名（判断Excel版本）
     * @return
     * @throws Exception
     */
    public static ExcelModel readExcel(InputStream in, String fileName) throws Exception {
        // 根据文件名来创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        ExcelModel excelModel = new ExcelModel();
        // 返回数据
        List<Map<String, String>> contextList = new ArrayList<>();
        Sheet sheet = work.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        excelModel.setSheetName(sheet.getSheetName());
        // 取第一行标题
        Row rowTitle = sheet.getRow(0);
        List<String> title = new ArrayList<>();
        if (rowTitle != null) {
            for (int y = rowTitle.getFirstCellNum(); y < rowTitle.getLastCellNum(); y++) {
                title.add((String) getCellValue(rowTitle.getCell(y)));
            }
        }
        if (CollectionUtils.isEmpty(title)) {
            return null;
        }
        excelModel.setTitle(title);
        // 遍历当前sheet中的所有行
        for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
            if (row == null) {
                continue;
            }
            CompletableFuture<Map<String, String>> future = CompletableFuture.supplyAsync(()->{
               return getRowValue(row,title);
            });
            contextList.add(future.get(3, TimeUnit.SECONDS));
        }
        excelModel.setContextList(contextList);
        return excelModel;
    }

    private static Map<String, String> getRowValue(Row row,List<String> title){
        Map<String, String> contextMap = new LinkedHashMap<>();
        // 遍历所有的列
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
            if (row.getCell(y) == null) {
                continue;
            }
            String key = ExcelTitleMapping.getEXCELMAPPING().get(title.get(y));
            if (StringUtils.isBlank(key)) {
                key = title.get(y);
            }
            contextMap.put(key, (String) getCellValue(row.getCell(y)));
        }
        return contextMap;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr ,fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);
        } else if (EXCEL2007U.equals(fileType)) {
            wb = new XSSFWorkbook(OPCPackage.open(inStr));
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        DecimalFormat df2 = new DecimalFormat("0");
        DecimalFormat dff = new DecimalFormat("0.00");
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    double numericCellValue = cell.getNumericCellValue();
                    //浮点型保留2位小数
                    if (numericCellValue % 1 == 0) {
                        value = df.format(cell.getNumericCellValue());
                    } else {
                        value = dff.format(cell.getNumericCellValue());
                    }
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else if ("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf1.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


    public static void downExcel(ExcelModel excelModel, String filename, HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "application/vnd.ms-excel");
        // 解决导出文件名中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
        // 模板导出Excel
        XSSFWorkbook hssfWorkbook = ExcelUtils.getHSSFWorkbook(excelModel);
        hssfWorkbook.write(response.getOutputStream());
    }
}
