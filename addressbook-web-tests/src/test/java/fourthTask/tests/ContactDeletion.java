package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactDeletion extends TestBase {


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
