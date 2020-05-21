package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        // given at least one group
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData modifiedGroup = new GroupData(before.get(index).getId(), "test1", "test2", "test3");

        //modify existed group
        app.group().modifyExistedGroup(index, modifiedGroup);
        app.goTo().groupPage();

        //check whether group amount hasn't changed
        List<GroupData> after = app.group().list();
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
