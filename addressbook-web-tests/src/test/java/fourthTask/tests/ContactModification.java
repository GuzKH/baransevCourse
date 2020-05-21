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
            app.group().create(new GroupData("test1", null, null));
        }
        app.goTo().homePageFromGroup();
        if ((app.contact().list().size() == 0)) {
            app.contact().create(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }
    }

    @Test (enabled = false)
    public void testContactModification() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData(before.get(index).getId(),"test1*", "test2*", "tt", "test4", "tttt@uu.com", null);

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
