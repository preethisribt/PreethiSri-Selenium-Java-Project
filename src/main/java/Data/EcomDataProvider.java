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

    @DataProvider(name = "EcomData")
    public Object[][] getExcelDataEcom() throws IOException {
       return new Object[][] {{"monitor","Apple monitor 24"}};
    }
}