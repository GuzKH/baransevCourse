package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {

    @Test (enabled = false)
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

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);

    }
}
