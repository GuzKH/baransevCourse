package fourthTask.tests;

import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Groups before = app.group().all();
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
        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

//        ///////////////
//        before.remove(modifiedGroup);
//        before.add(group);
//        ///////////////
//
//        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
