package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName(group.getName()));
        }

        app.goTo().homePageFromGroup();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withGroup(group.getName()), true);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId((modifiedContact).getId())
                .withFirstName("test1")
                .withLastName("test2")
                .withAddress("tt")
                .withEmail("tttt@uu.com")
                .withHomePhone("111")
                .withMobilePhone("222")
                .withWorkPhone("333");

        app.contact().modify(contact);

        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();

//        before.remove(modifiedContact);
//        before.add(contact);
//
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
