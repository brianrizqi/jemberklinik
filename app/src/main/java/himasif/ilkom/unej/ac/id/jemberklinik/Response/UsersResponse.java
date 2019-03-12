package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.User;

public class UsersResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("user")
    private List<User> users;

    public UsersResponse(boolean error, String msg, List<User> users) {
        this.error = error;
        this.msg = msg;
        this.users = users;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public List<User> getUsers() {
        return users;
    }
}
