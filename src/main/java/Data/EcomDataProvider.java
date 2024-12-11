package Data;

import javafx.scene.control.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class EcomDataProvider {
    Map<String, String> map;
    int rowCount = 0;
    int columnCount = 0;

    @DataProvider(name = "APIData")
    public Object[][] getExcelDataAPI() throws IOException {
        map = new LinkedHashMap<>();

        Object[][] data = getDataFromExcelusingObject("APIData", "EcomData.xlsx");
        return data;
    }

    @DataProvider(name = "EcomData")
    public Object[][] getExcelDataEcom() throws IOException {
        map = new LinkedHashMap<>();

        Object[][] data = getDataFromExcelusingObject("DemoBlaze", "EcomData.xlsx");
        return data;
    }

    public Object[][] getDataFromExcelusingObject(String sheetName, String ExcelName) throws IOException {
        FileInputStream excelFile = new FileInputStream("src/main/java/Data/" + ExcelName);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelFile);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        XSSFRow row;
        CellType cellType;

        rowCount = xssfSheet.getLastRowNum();
        columnCount = xssfSheet.getRow(0).getLastCellNum();
        Object[][] objects = new Object[rowCount-2][columnCount];

        //read data header
        for (int i = 0; i < columnCount; i++) {
            map.put(xssfSheet.getRow(0).getCell(i).getStringCellValue(), "");
        }

        //read and initialize object[][]
        int k = 0;
        for (int i = 1; i < rowCount-1; i++) {
            row = xssfSheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                cellType = row.getCell(j).getCellType();
                if (cellType.equals(CellType.NUMERIC) && row.getCell(j).getRawValue().contains("."))
                        objects[k][j] = row.getCell(j).getNumericCellValue();
                else if (cellType.equals(CellType.NUMERIC))
                        objects[k][j] = Integer.parseInt(row.getCell(j).getRawValue());
                else if (cellType.equals(CellType.STRING))
                    objects[k][j] = row.getCell(j).getStringCellValue();
                else if (cellType.equals(CellType.NUMERIC))
                    objects[k][j] = row.getCell(j).getNumericCellValue();

//                System.out.println( objects[k][j]);
            }
            k++;
        }
        return objects;
    }
}