package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreation extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName(group.getName()));
        }
    }

    @Test
    public void testContactCreation() throws Exception {
        GroupData group = new GroupData().withName("test1");

        String[] names = new String[]{"test1", "test2", "test3"};

        for (String name : names) {
            app.goTo().homePageFromGroup();
            Contacts before = app.contact().all();
            File photo = new File("src/test/resources/stru.png");
            ContactData contact = new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withPhoto(photo)
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withGroup(group.getName());

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
