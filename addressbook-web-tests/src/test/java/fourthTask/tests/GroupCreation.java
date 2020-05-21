package fourthTask.tests;

import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData()
                .withName("Test1");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

//        group.withId(
//                after.stream()
//                        .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
//                        .get()
//                        .getId()
//        );
        assertThat(after, CoreMatchers.equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
}
