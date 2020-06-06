package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreation extends TestBase {

    // Провайдер тестовых данных для отчетов
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2]).withEmail(split[3]).withAllPhones(split[4]).withGroup(split[5])});
            line = reader.readLine();
        }
//        list.add(new Object[]{new ContactData().withFirstName("test 0").withLastName("test 0").withPhoto(photo).withAddress("test 0").withEmail("test@0").withAllPhones("9811111110").withGroup("test1")});
//        list.add(new Object[]{new ContactData().withFirstName("test 1").withLastName("test 1").withPhoto(photo).withAddress("test 1").withEmail("test@1").withAllPhones("9811111111").withGroup("test1")});
//        list.add(new Object[]{new ContactData().withFirstName("test 2").withLastName("test 2").withPhoto(photo).withAddress("test 2").withEmail("test@2").withAllPhones("9811111112").withGroup("test1")});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName(group.getName()));
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().homePageFromGroup();
        Contacts before = app.contact().all();

        app.contact().create(contact, true);
        app.goTo().homePage();
        //   Thread.sleep(1000);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(
                        after.stream()
                                .mapToInt((c) -> c.getId()).max().getAsInt())
        )));


    }

//    @Test
//    public void testCurrentDir(){
//        File currentDir = new File(" . ");
//        System.out.println(currentDir.getAbsolutePath());
//        File photo = new File("src/test/resources/stru.png");
//        System.out.println(photo.getAbsolutePath());
//        System.out.println(currentDir.exists());
//    }

}
