package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        // given at least one group
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1;
        GroupData modifiedGroup = new GroupData(before.get(index).getId(), "test1", "test2", "test3");

        //modify existed group
        app.getGroupHelper().modifyExistedGroup(index, modifiedGroup);
        app.getNavigationHelper().goToGroupPage();

        //check whether group amount hasn't changed
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        ///////////////
        before.remove(index);
        before.add(modifiedGroup);
        ///////////////

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);
    }

}
