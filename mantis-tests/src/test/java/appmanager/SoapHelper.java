package appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import model.Issue;
import model.IssueStatus;
import model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper extends HelperBase {

//    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        super(app);
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects)
                .stream().map((p) -> new Project()
                        .withId(p.getId().intValue())
                        .withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantis.connect")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));

        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));

        BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);


        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public List<Issue> getAllIssues(Project project) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        BigInteger pageNumber = BigInteger.ZERO;
        BigInteger countPerPage = BigInteger.valueOf(-1);

        IssueData[] issues = mc.mc_project_get_issues(
                app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"),
                BigInteger.valueOf(project.getId()),
                pageNumber,
                countPerPage);

        return Arrays.asList(issues).stream()
                .map(it -> new Issue()
                        .withId(it.getId().intValue())
                        .withDescription(it.getDescription())
                        .withSummary(it.getSummary())
                        .withStatus(IssueStatus.from(it.getStatus().getName())))
                .collect(Collectors.toList());
    }

    public Issue getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();

        IssueData issue = mc.mc_issue_get(
                app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"),
                BigInteger.valueOf(issueId));

        return new Issue()
                .withId(issue.getId().intValue())
                .withDescription(issue.getDescription())
                .withSummary(issue.getSummary())
                .withStatus(IssueStatus.from(issue.getStatus().getName()));
    }

    public void modifyIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();

        IssueData issue = mc.mc_issue_get(
                app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"),
                BigInteger.valueOf(issueId));

        String oldSummary = issue.getSummary();
        String newSummary = oldSummary + " kek";
        issue.setSummary(newSummary);

        mc.mc_issue_update(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"),
                BigInteger.valueOf(issueId),
                issue);
    }
}
