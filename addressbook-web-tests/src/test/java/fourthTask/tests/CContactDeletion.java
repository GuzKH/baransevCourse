package fourthTask.tests;

import org.testng.annotations.Test;

public class CContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteConatct();
        app.getContactHelper().acceptContactDeletion();
        app.getContactHelper().acceptNextAlert = true;
    }
}