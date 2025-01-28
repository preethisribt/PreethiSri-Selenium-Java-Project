package ParaBankTestCases;

import BaseTest.BaseTest;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.testng.annotations.Test;

public class OpenAccountTest extends BaseTest {

    @Test
    public void openNewAccount() {
        FindIterable<Document> documents = mongoCollection.find();
        for (Document doc : documents) {
            String user = doc.getString("userName");
            String password = doc.getString("password");
            System.out.println(user);
            System.out.println(password);
        }
    }
}
