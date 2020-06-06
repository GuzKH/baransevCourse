package fourthTask.tests;

import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreation extends TestBase {

    // Провайдер тестовых данных для отчетов
    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
//        list.add(new Object[]{ new GroupData().withName("test1").withHeader("header1").withFooter( "footer1")});
//        list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter( "footer2")});
//        list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter( "footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();

        //проверки
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

//    @Test
//    public void testBadGroupCreation() throws Exception {
//
//        app.goTo().groupPage();
//        Groups before = app.group().all();
//        GroupData group = new GroupData()
//                .withName("Test1'");
//        app.group().create(group);
//        assertThat(app.group().count(), equalTo(before.size()));
//        Groups after = app.group().all();
//        assertThat(after, equalTo(before));
//
//    }
}
