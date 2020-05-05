package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.annotations.Test;

public class CContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com", "test1"), true);
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
//        app.getContactHelper().editContactCreation();
        app.getContactHelper().deleteContact1();
        app.getContactHelper().acceptContactDeletion();
        app.getContactHelper().acceptNextAlert = true;
    }
}
