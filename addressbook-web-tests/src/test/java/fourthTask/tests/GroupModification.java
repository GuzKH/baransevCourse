package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GroupModification extends TestBase {

    @Test
    public void testGroupModification() {
        // given at least one group
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        //modify existed group
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData modifiedGroup = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
        app.getGroupHelper().fillGroupForm(modifiedGroup);
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();

        //check whether group amount hasn't changed
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        ///////////////
        before.remove(before.size() - 1);
        before.add(modifiedGroup);
        ///////////////

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        after.sort(byId);
        before.sort(byId);

        Assert.assertEquals(before, after);
    }
}
