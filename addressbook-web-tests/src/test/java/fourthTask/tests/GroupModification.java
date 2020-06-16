package fourthTask.tests;

import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }

    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");

        //modify existed group
        app.group().modifyExistedGroup(group);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size()));

        //check whether group amount hasn't changed
        Groups after = app.db().groups();  //uploading group list from db
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }



}
