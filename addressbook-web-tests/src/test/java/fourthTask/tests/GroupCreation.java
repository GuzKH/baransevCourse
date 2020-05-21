package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData()
                .withName("Test1");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(
                after.stream()
                        .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
                        .get()
                        .getId()
        );

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
