package Market.Model;

import lombok.Data;

@Data
public abstract class User {
    private int UID;
    private String name;

    public void login() {}
    public void logout() {}
    public abstract void manageProfile();
}
