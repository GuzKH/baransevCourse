package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddingToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("test1");
//        group.getId();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getName()));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePageFromGroup();
            app.contact().create(new ContactData()
                            .withFirstName("test1")
                            .withLastName("test2")
                            .inGroup(group)
                    , true);
        }
    }

    @Test
    public void contactAddingToGroup() throws InterruptedException {
        Groups allGroups = app.db().groups();
        Contacts contacts = app.db().contacts();

        app.goTo().homePageFromGroup();

        if (contacts.stream()
                .noneMatch(contact -> contact.getGroups().size() < allGroups.size())) {
            app.contact().create(new ContactData()
                            .withFirstName("test1")
                            .withLastName("test2")
                    , true);
        }
        ContactData contactToAddGroup = contacts.stream()
                .filter(contact -> contact.getGroups().size() < allGroups.size()).iterator().next();

        Groups beforeGroups = contactToAddGroup.getGroups();
        GroupData groupToAdd = allGroups.without(beforeGroups).iterator().next();

        app.contact().addingToGroup(contactToAddGroup, groupToAdd);
        app.goTo().homeFromGroup(groupToAdd);

        Groups afterGroups = app.db().contacts().stream()
                .filter(s -> s.getId() == contactToAddGroup.getId()).findFirst().get().getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.withAdded(groupToAdd)));

        verifyContactListinUI();
    }
}



