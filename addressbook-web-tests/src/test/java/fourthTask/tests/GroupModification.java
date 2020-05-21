package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // given at least one group
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("Test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");

        //modify existed group
        app.group().modifyExistedGroup(group);
        app.goTo().groupPage();

        //check whether group amount hasn't changed
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        ///////////////
        before.remove(modifiedGroup);
        before.add(group);
        ///////////////

        Assert.assertEquals(before, after);
    }

}
