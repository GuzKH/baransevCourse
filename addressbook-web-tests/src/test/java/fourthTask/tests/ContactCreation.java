package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
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
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(
                after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }
}
