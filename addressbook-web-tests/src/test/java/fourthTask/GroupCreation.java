package fourthTask;

import org.testng.annotations.*;


public class GroupCreation extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroup();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submit();
    backToGroupPage();
  }

}
