package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmail extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getName()));
        }


        if (app.db().contacts().size() == 0) {
            app.goTo().homePageFromGroup();
            app.contact().create(new ContactData()
                            .withFirstName("test1")
                            .withLastName("test2")
                            .withAddress("tt")
                            .withEmail("tttt@uu.com")
                            .withEmail2("2tttt@uu.com")
                            .withEmail3("3tttt@uu.com")
                            .withHomePhone("111")
                            .withMobilePhone("222")
                            .withWorkPhone("333")
                    //     .withGroup(group.getName())
                    , true);
        }
    }


    @Test(enabled = true)
    public void testContactEmail() {
        app.goTo().homePageFromGroup();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        verifyContactListinUI();
    }

    // метод обрытных проверок
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactEmail::cleaned)   //чистка
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", " ")
                .replaceAll("[-()]", " ");
    }

}
