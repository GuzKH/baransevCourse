package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getNavigationHelper().goToHomePageFromGroupPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("test10*", "test2*", "tt", "test4", "tttt@uu.com", "test1"),true);
//        app.getContactHelper().initContactCreation();
//        app.getContactHelper().fillContactForm(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com", "test1"), true);
//        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);

    }
}
