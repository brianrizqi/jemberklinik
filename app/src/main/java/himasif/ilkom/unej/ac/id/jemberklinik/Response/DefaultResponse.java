package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String msg;

    public DefaultResponse(boolean error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
