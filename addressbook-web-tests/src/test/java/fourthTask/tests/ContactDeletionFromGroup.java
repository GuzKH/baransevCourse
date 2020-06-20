package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            GroupData group = new GroupData().withName("test1");
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


            ContactData contact = app.db().contacts().iterator().next();
//            GroupData group = app.db().groups().iterator().next();

            app.contact().addingToGroup(contact);
            app.goTo().homeFromGroup();
    }

    @Test
    public void contactDeletionFromGroup() throws InterruptedException {
        Groups groups = app.db().groups();
        Contacts beforeContacts = app.db().contacts();
        ContactData contact = beforeContacts.iterator().next();
        app.contact().deletionFromGroup(contact);
        app.goTo().homeFromGroup();
        Contacts afterUI = app.contact().all();
//        Thread.sleep(1000);

        assertThat(afterUI, equalTo(beforeContacts.without(contact)));

        Contacts afterDB = app.db().contacts();
        assertThat(afterDB, equalTo(beforeContacts));

        verifyContactListinUI();
    }
}

//         new Select(wd.findElement(By.name("new_group")))
//                .selectByVisibleText(contactData.getGroups().iterator().next().getName());


