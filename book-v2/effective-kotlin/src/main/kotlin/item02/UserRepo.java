package item02;

import item01.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserRepo {
    public User getUser() {
        return new User("kwang");
    }


    public @NotNull ArrayList<User> getUsers() {
        return new ArrayList();
    }

}
