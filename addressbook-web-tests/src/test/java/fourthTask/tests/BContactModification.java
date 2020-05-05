package fourthTask.tests;

import fourthTask.model.ContactData;
import org.testng.annotations.Test;

public class BContactModification extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com", "test1"), false);
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com", null), false);
        app.getContactHelper().updateContactEdition();
    }
}
