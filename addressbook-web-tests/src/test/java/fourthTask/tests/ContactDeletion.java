package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletion extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().list().size() == 0){
            app.group().create(new GroupData("test1", null, null));
        }

        app.goTo().homePageFromGroup();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("test1*", "test2*", "tt", "test4", "tttt@uu.com", "test1"), true);
        }
    }

    @Test (enabled = false)
    public void testContactDeletion() throws Exception {
        app.goTo().homePageFromGroup();
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;

        app.contact().delete(index);

        app.goTo().homePageFromGroup();
        Thread.sleep(5000);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);



    }

}
