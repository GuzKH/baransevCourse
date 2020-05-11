package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BContactModification extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getNavigationHelper().goToHomePageFromGroupPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }

        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", null), false);
        app.getContactHelper().updateContactEdition();

        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
