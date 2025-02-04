package Data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EcomDataProvider {
    Map<String, String> map;
    int rowCount = 0;
    int columnCount = 0;
    MongoCollection<Document> mongoCollection;
    @DataProvider(name = "EcomData")

    public Object[][] getExcelDataEcom() throws IOException {
        return new Object[][]{{"monitor", "Apple monitor 24"}};
    }

    public void connectMongoDB() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("autodb");
        mongoCollection = mongoDatabase.getCollection("userDB");
    }

    public void loadDatainToDB() {
//        Document document = new Document();
//        document.append("userName", RegistrationPage.userName);
//        document.append("password", RegistrationPage.password);
//
//        List<Document> documentList = new ArrayList<Document>();
//        documentList.add(document);
//        mongoCollection.insertMany(documentList);
    }


    @DataProvider(name = "data")
    public Object[][] dataSupplier() throws IOException {
        File file = new File("D:\\GitHub public repo\\PreethiSri-Selenium-Java-Project\\src\\main\\java\\Data\\EcomData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        wb.close();
        int lastRowNum = sheet.getLastRowNum() ;
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[lastRowNum][1];
        for (int i = 0; i < lastRowNum; i++) {
            Map<Object, Object> datamap = new HashMap<>();
            for (int j = 0; j < lastCellNum; j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
            }
            obj[i][0] = datamap;
        }
        return  obj;
    }
}