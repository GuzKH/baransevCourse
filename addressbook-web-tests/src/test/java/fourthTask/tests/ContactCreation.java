package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        app.getNavigationHelper().goToHomePageFromGroupPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new ContactData("test10*", "test2*", "tt", "test4", "tttt@uu.com", "test1"),true);
//        app.getContactHelper().initContactCreation();
//        app.getContactHelper().fillContactForm(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com", "test1"), true);
//        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
     //   Thread.sleep(1000);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

    }
}
