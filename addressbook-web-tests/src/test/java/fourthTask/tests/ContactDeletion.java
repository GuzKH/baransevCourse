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

public class ContactDeletion extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }

        app.goTo().homePageFromGroup();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomeNumber("test4"), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        app.goTo().homePageFromGroup();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
//        int index = before.size() - 1;

        app.contact().delete(deletedContact);

        app.goTo().homePageFromGroup();
        Thread.sleep(5000);
        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(deletedContact)));


    }

}
