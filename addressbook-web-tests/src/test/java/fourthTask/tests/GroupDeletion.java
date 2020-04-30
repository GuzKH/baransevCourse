package fourthTask.tests;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
//    wd.findElement(By.xpath("//div[@id='content']/form/span")).click();
//    wd.findElement(By.xpath("//form[@action='/addressbook/group.php']")).click();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().backToGroupPage();
    }

}
