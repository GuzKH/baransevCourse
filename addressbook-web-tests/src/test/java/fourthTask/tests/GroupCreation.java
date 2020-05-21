package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData()
                .withName("Test1");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

//        group.withId(
//                after.stream()
//                        .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
//                        .get()
//                        .getId()
//        );
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);

    }
}
