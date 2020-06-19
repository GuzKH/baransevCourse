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


        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getName()));
        }


        if (app.db().contacts().size() == 0) {
            app.goTo().homePageFromGroup();
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                  //  .withGroup(group.getName())
                    , true);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
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

        Contacts after = app.db().contacts();

//        before.remove(modifiedContact);
//        before.add(contact);
//
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        verifyContactListinUI();
    }
}
