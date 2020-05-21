package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(!app.group().isThereAGroup()){
            app.group().create(new GroupData("test1", null, null));
        }
        app.goTo().goToHomePageFromGroupPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }
    }

    @Test (enabled = false)
    public void testContactModification() {
        app.goTo().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContactCreation();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"test1*", "test2*", "tt", "test4", "tttt@uu.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContactEdition();

        app.goTo().goToHomePage();
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
