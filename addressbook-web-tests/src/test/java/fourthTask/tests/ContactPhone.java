package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhone extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName(group.getName()));
        }

        app.goTo().homePageFromGroup();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test2")
                    .withAddress("tt")
                    .withEmail("tttt@uu.com")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withGroup(group.getName()), true);
        }
    }


    @Test (enabled = true)
    public void testContactPhones(){
        app.goTo().homePageFromGroup();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

//        assertThat(contact.getHomePhone(), equalTo((contactInfoFromEditForm.getHomePhone())));
//        assertThat(contact.getMobilePhone(), equalTo((contactInfoFromEditForm.getMobilePhone())));
//        assertThat(contact.getWorkPhone(), equalTo((contactInfoFromEditForm.getWorkPhone())));

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
//        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
//        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    }

    // метод обрытных проверок
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhone::cleaned)   //чистка
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return  phone.replaceAll("\\s", " ")
                .replaceAll("[-()]", " ");
    }

}
