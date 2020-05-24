package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePageFromGroup();
        if ((app.contact().list().size() == 0)) {
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomeNumber("test4"), true);
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
                .withHomeNumber("test4");

        app.contact().modify(contact);

        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contact);

        assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
