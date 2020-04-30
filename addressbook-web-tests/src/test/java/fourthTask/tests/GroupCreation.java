package fourthTask.tests;

import fourthTask.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goToGroupPage();
        app.initGroup();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submit();
        app.backToGroupPage();
    }

}
