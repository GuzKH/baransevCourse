package fourthTask.tests;

import fourthTask.model.ContactData;
import org.testng.annotations.Test;

public class AContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("test1*", "test2*", "tt", "test4", "01234", "tttt@uu.com"));
        app.getContactHelper().submitContactCreation();
    }
}
