package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
//        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId((modifiedContact).getId())
                .withFirstName("test1")
                .withLastName("test2")
                .withAddress("tt")
                .withEmail("tttt@uu.com")
                .withHomeNumber("test4");

        app.contact().modify(contact);

        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contact);

//        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        after.sort(byId);
//        before.sort(byId);

        Assert.assertEquals(before, after);
    }
}
