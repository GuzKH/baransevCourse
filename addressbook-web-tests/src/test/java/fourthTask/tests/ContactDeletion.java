package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().delete(index);

        app.goTo().homePageFromGroup();
        Thread.sleep(5000);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);


    }

}
