package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddingToGroup extends TestBase {

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
                    , true);
        }
    }

    @Test
    public void contactAddingToGroup() throws InterruptedException {
        Groups groups = app.db().groups();
        app.goTo().homePageFromGroup();
        Contacts beforeContacts = app.db().contacts();
        ContactData contact = beforeContacts.iterator().next();
        app.contact().addingToGroup(contact);
        app.goTo().homeFromGroup();
//        Thread.sleep(1000);

        assertThat(app.contact().count(), equalTo(beforeContacts.size()));

//        Contacts after = app.db().contacts();
//        assertThat(after, equalTo(before.withAdded(
//                contact.withId(
//                        after.stream()
//                                .mapToInt((c) -> c.getId()).max().getAsInt())
//        )));

        verifyContactListinUI();
    }
}



