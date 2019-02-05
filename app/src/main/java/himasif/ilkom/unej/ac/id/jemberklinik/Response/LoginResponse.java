package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.User;

public class LoginResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("user")
    private User user;

    public LoginResponse(boolean error, String msg, User user) {
        this.error = error;
        this.msg = msg;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }
}
