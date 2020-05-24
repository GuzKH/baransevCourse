package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Contacts before = app.contact().all();
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
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(
                        after.stream()
                                .mapToInt((c) -> c.getId()).max().getAsInt())
        )));

    }

}
