package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
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


    private Set<Issue> getIssues()   {
//        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?page=1&limit=200"))
//                .returnContent().asString();
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?page=1&limit=200").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }


    private int createIssue(Issue newIssue)   {
//        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
//                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
//                        new BasicNameValuePair("description", newIssue.getDescription())))
//                .returnContent().asString();
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
