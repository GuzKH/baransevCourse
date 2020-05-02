package fourthTask.tests;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
