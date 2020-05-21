package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletion extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        app.getNavigationHelper().goToHomePageFromGroupPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }
    }

    @Test (enabled = false)
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToHomePageFromGroupPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
//        app.getContactHelper().editContactCreation();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptContactDeletion();
        app.getContactHelper().acceptNextAlert = true;

        app.getNavigationHelper().goToHomePageFromGroupPage();
        Thread.sleep(5000);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);



    }
}
