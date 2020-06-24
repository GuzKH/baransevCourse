package tests;

import model.Issue;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soapHelper().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soapHelper().getProjects();
        Issue issue = new Issue()
                .withSummary("Test issue")
                .withDescription("Test issue desc")
                .withProject(projects.iterator().next());
        Issue created = app.soapHelper().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testModifyIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soapHelper().getProjects();
        Project project = projects.iterator().next();

        List<Issue> allIssues = app.soapHelper().getAllIssues(project);
        Collections.reverse(allIssues);
        Issue lastIssue = allIssues.iterator().next();
        skipIfNotFixed(lastIssue.getId());

        //when
        app.soapHelper().modifyIssue(lastIssue.getId());
        String updatedSummary = app.soapHelper().getIssue(lastIssue.getId()).getSummary();

        assertEquals(updatedSummary, lastIssue.getSummary() + " kek");
    }


}
