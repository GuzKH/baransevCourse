package model;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Users groups) {
        this.delegate = new HashSet<UserData>(groups.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();

    }

    public Users(Collection<UserData> groups) {
        this.delegate = new HashSet<UserData>(groups);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Users withAdded(UserData user) {
        Users users = new Users(this);
        users.add(user);
        return  users;
    }

    public Users without(UserData user) {
        Users users = new Users(this);
        users.remove(user);
        return  users;
    }

    public Users without(Users delUsers) {
        Users users = new Users(this);
        for (UserData user : delUsers) {
            users.remove(user);
        }
        return  users;
    }
}
