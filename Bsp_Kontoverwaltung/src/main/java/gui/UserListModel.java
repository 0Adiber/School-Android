package gui;

import bl.AccountUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;


public class UserListModel extends AbstractListModel<AccountUser> {

    private List<AccountUser> users = new ArrayList<>();
    
    @Override
    public int getSize() {
        return users.size();
    }

    @Override
    public AccountUser getElementAt(int index) {
        return users.get(index);
    }

    public void add(AccountUser user){
        users.add(user);
        this.fireContentsChanged(users, 0, users.size());
    }
    
    public void addAll(List<AccountUser> users){
        users.addAll(users);
        this.fireContentsChanged(users, 0, users.size());
    }

    public List<AccountUser> getUsers(){
        return users;
    }
    
    public boolean exists(AccountUser user){
        return users.contains(user);
    }
}