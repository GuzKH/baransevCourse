package model;

public enum IssueStatus {
    NEW,
    FEEDBACK,
    ACKNOWLEDGED,
    CONFIRMED,
    ASSIGNED,
    RESOLVED,
    CLOSED;

    public static IssueStatus from(String name) {
        return IssueStatus.valueOf(name.toUpperCase());
    }
}
