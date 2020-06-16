package fourthTask.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreation extends TestBase {

    // Провайдер тестовых данных для отчетов
    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());  //List<ContactData>.class

            return contacts.stream()
                    .map((c) -> new Object[]{c})
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getName()));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
        File photo = new File("src/test/resources/stru.png");

        app.goTo().homePageFromGroup();
        Contacts before = app.db().contacts();

        app.contact().create(contact, true);
        app.goTo().homePage();
        //   Thread.sleep(1000);
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(
                        after.stream()
                                .mapToInt((c) -> c.getId()).max().getAsInt())
        )));

        verifyContactListinUI();
    }
}

//    @Test
//    public void testCurrentDir(){
//        File currentDir = new File(" . ");
//        System.out.println(currentDir.getAbsolutePath());
//        File photo = new File("src/test/resources/stru.png");
//        System.out.println(photo.getAbsolutePath());
//        System.out.println(currentDir.exists());
//    }


