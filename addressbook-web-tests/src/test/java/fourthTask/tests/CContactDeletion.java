package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        app.getNavigationHelper().goToHomePageFromGroupPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }

        app.getNavigationHelper().goToHomePageFromGroupPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before -1);
//        app.getContactHelper().editContactCreation();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptContactDeletion();
        app.getContactHelper().acceptNextAlert = true;

        app.getNavigationHelper().goToHomePageFromGroupPage();
        Thread.sleep(1000);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);

    }
}
