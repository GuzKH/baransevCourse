package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContactCreation();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"test1*", "test2*", "tt", "test4", "tttt@uu.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContactEdition();

        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    }
}
