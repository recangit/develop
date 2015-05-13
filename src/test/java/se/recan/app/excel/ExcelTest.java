package se.recan.app.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @date 2014-maj-09
 * @author Anders Recksén (recan)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExcelTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void createExcelFile() {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"METHOD", "VALUE", "MESSAGE"});
        data.put("2", new Object[]{"init", "", "Lägg till en post"});
        data.put("3", new Object[]{"save", "", "Det finns ingen information att spara"});
        data.put("4", new Object[]{"setFirstName", "K", ""});
        data.put("5", new Object[]{"save", "", "Förnamn måste bestå av minst tre tecken"});
        data.put("6", new Object[]{"setFirstName", "Kajsa", ""});
        data.put("7", new Object[]{"save", "", "Efternamn måste anges"});
        data.put("8", new Object[]{"setLastName", "R", ""});
        data.put("9", new Object[]{"save", "", "Efternamn måste bestå av minst två tecken"});
        data.put("10", new Object[]{"setLastName", "Recksén", ""});
        data.put("11", new Object[]{"save", "", "Detta är inte ett korrekt personnummer"});
        data.put("12", new Object[]{"setSocialSecurityNumb", "0403139465", ""});
        data.put("13", new Object[]{"save", "", "Detta är inte ett korrekt personnummer"});
        data.put("14", new Object[]{"setSocialSecurityNumb", "0403139462", ""});
        data.put("15", new Object[]{"setGender", "1", ""});
        data.put("16", new Object[]{"save", "", "Du har angett man men är kvinna"});
        data.put("17", new Object[]{"setGender", "0", ""});
        data.put("18", new Object[]{"save", "", "Användarnamn måste vara minst fem tecken"});
        data.put("19", new Object[]{"setUserName", "karra", ""});
        data.put("20", new Object[]{"save", "", "Lösenordet måste vara minst fem tecken långt"});
        data.put("21", new Object[]{"setPassword", "bucken", ""});
        data.put("22", new Object[]{"save", "", "Fyll i lösenordet igen"});
        data.put("23", new Object[]{"setPassword", "buck", ""});
        data.put("24", new Object[]{"save", "", "Lösenorden måste vara identiska"});
        data.put("25", new Object[]{"setPassword", "bucken", ""});
        data.put("26", new Object[]{"save", "", "Posten har sparats"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            File file = new File("src/test/resources/tmp.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            LOGGER.debug(file.getCanonicalPath() + " written successfully on disk.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(new File("src/test/resources/tmp.xlsx").exists());
    }

    @Test
    public void readExcelFile() throws FileNotFoundException, IOException {

        FileInputStream file = new FileInputStream(new File("src/test/resources/tmp.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            StringBuilder builder = new StringBuilder();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        builder.append(cell.getNumericCellValue());
                        builder.append(" ");
                        break;
                    case Cell.CELL_TYPE_STRING:
//                            LOGGER.debug(cell.getStringCellValue() + "\t");
                        builder.append(cell.getStringCellValue());
                        builder.append(" ");
                        break;
                }
            }
            LOGGER.debug(builder.toString());
        }
        file.close();

        Assert.assertTrue(new File("src/test/resources/tmp.xlsx").exists());
        Assert.assertTrue(new File("src/test/resources/tmp.xlsx").length() > 0);
    }

    @AfterClass
    public static void cleanUp() {
        File file = new File("src/test/resources/tmp.xlsx");
        
        if(file.exists()) {
            file.delete();
        }
    }
}
