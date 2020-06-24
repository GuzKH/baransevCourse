package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.core.Is;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init(){
        RestAssured.authentication =
                RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testCreateIssue()   {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue Guz").withDescription("New test issue Guz");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));

        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testModifyIssue(){
//        List<Issue> oldIssues = new ArrayList<>(getIssues());
//        Collections.reverse(oldIssues);
//        Issue lastIssue = oldIssues.iterator().next();

        Issue lastIssue = getIssue(118);
        int idLastIssue = lastIssue.getId();
        String oldSubject = lastIssue.getSubject();
        skipIfNotFixed(idLastIssue);

        modifyIssue(idLastIssue, oldSubject);
        Issue updatedSubjectIssue = getIssue(idLastIssue);

        assertEquals(updatedSubjectIssue.getSubject(), oldSubject + " kek");



    }



}
