package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);


//        int max = 0;
//        for (GroupData g : after){
//            if (g.getId() > max) {
//                max = g.getId();
//            }
//        }
//        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//        int max1 = after.stream()
//                .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
//                .get()
//                .getId();
        group.setId(
                after.stream()
                        .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
                        .get()
                        .getId()
        );
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    }

}
