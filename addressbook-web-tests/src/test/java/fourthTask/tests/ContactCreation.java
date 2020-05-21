package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreation extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(group);
        }
    }

    @Test
    public void testContactCreation() throws Exception {
        GroupData group = new GroupData().withName("test1");

        app.goTo().homePageFromGroup();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                .withFirstName("test1")
                .withLastName("test2")
                .withAddress("tt")
                .withEmail("tttt@uu.com")
                .withHomeNumber("test4")
                .withGroup(group.getName());

        app.contact().create(contact, true);
        app.goTo().homePage();
        //   Thread.sleep(1000);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(
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
