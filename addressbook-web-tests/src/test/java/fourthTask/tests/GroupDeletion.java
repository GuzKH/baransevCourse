package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletion extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        app.group().returnToGroupPage();
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);

    }

}
