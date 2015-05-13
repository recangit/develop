package se.recan.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @date 2014-maj-09
 * @author Anders Recksén (recan)
 */
public class Excel {

    private static final Logger LOGGER = Logger.getLogger("Logger");

//    private final String excel;
//
//    public Excel(String excel) {
//        this.excel = excel;
//    }

    public static List<String> readExcel(String excel) {
        List<String> parsed = new ArrayList<String>();
        try {

            FileInputStream file = new FileInputStream(new File(excel));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                StringBuilder builder = new StringBuilder();
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                int i = 0;

                while (cellIterator.hasNext()) {
                    i++;
                    
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (i == 2) {
                                int value = (int) cell.getNumericCellValue();
                                builder.append(value);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (i == 1) {
                                builder.append(cell.getStringCellValue());
                                builder.append(":");
                            } else if (i == 2) {
                                builder.append(cell.getStringCellValue());
                            }
                            break;
                    }

                }

                if (builder.length() > 0) {
                    parsed.add(builder.toString());
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsed;
    }
}
