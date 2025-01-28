import BaseTest.BaseTest;
import Pages.RegistrationPage;
import org.bson.Document;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoadTestDataInToDB extends BaseTest {

//    @Test(dependsOnMethods = {"validateNewUserAbleToCompleteRegistration"})

    public void openNewAccount(){

    }
    public void loadDatainToDB() {
        Document document = new Document();
        document.append("userName",RegistrationPage.userName);
        document.append("password",RegistrationPage.password);

        List<Document> documentList = new ArrayList<Document>();
        documentList.add(document);
        mongoCollection.insertMany(documentList);
    }
}
