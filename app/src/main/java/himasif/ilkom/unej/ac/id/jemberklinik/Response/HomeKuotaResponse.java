package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.Kuota;

public class HomeKuotaResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("kuota")
    private Kuota kuota;

    public HomeKuotaResponse(boolean error, String msg, Kuota kuota) {
        this.error = error;
        this.msg = msg;
        this.kuota = kuota;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public Kuota getKuota() {
        return kuota;
    }
}
