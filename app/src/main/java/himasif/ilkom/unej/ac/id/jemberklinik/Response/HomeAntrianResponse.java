package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.HomeAntrian;

public class HomeAntrianResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("antrian")
    private HomeAntrian antrian;

    public HomeAntrianResponse(boolean error, String msg, HomeAntrian antrian) {
        this.error = error;
        this.msg = msg;
        this.antrian = antrian;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public HomeAntrian getAntrian() {
        return antrian;
    }
}
