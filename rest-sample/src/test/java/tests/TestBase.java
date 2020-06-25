package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.List;
import java.util.Set;

public class TestBase {

    protected int createIssue(Issue newIssue) {
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

    protected Set<Issue> getIssues() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?page=1&limit=200").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }


    protected void modifyIssue(int idLastIssue, String oldSubject) {
        String path = "https://bugify.stqa.ru/api/issues/" + idLastIssue + ".json";
        String json = RestAssured.given()
                .parameter("method", "update")
                .parameter("issue[subject]", oldSubject + " kek")
                .post(path).asString();
        System.out.println(json);
    }



    protected Issue getIssue(int idLastIssue) {
        String path = "https://bugify.stqa.ru/api/issues/" + idLastIssue + ".json";
        String json = RestAssured.given()
                .get(path).asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        List<Issue> issuesList = new Gson().fromJson(issues, new TypeToken<List<Issue>>() {
        }.getType());

        return issuesList.get(0);
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issueID: " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        Issue issue = getIssue(issueId);

        if (issue.getState_name().equals("Resolved")  || issue.getState_name().equals("Closed")) {
            return false;
        }
        return true;
    }
}
