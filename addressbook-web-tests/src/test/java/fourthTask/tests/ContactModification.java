package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().list().size() == 0){
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
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId())
                .withFirstName("test1")
                .withLastName("test2")
                .withAddress("tt")
                .withEmail("tttt@uu.com")
                .withHomeNumber("test4");

        app.contact().modify(contact);

        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);
    }
}
