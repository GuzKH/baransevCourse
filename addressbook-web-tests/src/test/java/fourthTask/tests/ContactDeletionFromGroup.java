package fourthTask.tests;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import fourthTask.model.GroupData;
import fourthTask.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            GroupData group = new GroupData().withName("test1");
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getName()));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePageFromGroup();
            app.contact().create(new ContactData()
                            .withFirstName("test1")
                            .withLastName("test2")
                    , true);
        }
    }

    @Test
    public void contactDeletionFromGroup() throws InterruptedException {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();

        if (allContacts.stream()
                .noneMatch(contact -> contact.getGroups().size() > 0)) {
            app.contact().addingToGroup(allContacts.iterator().next(), allGroups.iterator().next());
            app.goTo().homePageFromGroup();
        }
        ContactData contactToRemoveGroup = app.db().contacts().stream()
                .filter(contact -> contact.getGroups().size() > 0).findAny().get();

        Groups beforeGroups = contactToRemoveGroup.getGroups();
        GroupData groupToRemove = beforeGroups.iterator().next();

        app.contact().deletionFromGroup(contactToRemoveGroup, groupToRemove);

        Groups afterGroups = app.db().contacts().stream()
                .filter(contract -> contract.getId() == contactToRemoveGroup.getId()).findFirst().get().getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.without(groupToRemove)));

        verifyContactListinUI();
    }
}


