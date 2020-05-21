package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() throws Exception {
        GroupData group = new GroupData("test1", null, null);

        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){

            app.getGroupHelper().createGroup(group);
        }

        app.getNavigationHelper().goToHomePageFromGroupPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("test10*", "test2", "tt", "test4", "tttt@uu.com", group.getName());
        app.getContactHelper().createContact(contact,true);
        app.getNavigationHelper().goToHomePage();
     //   Thread.sleep(1000);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(
                after.stream()
                        .max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
                        .get().getId()
        );
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }
}
