package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.Penyakit;

public class PenyakitResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("penyakit")
    private List<Penyakit> penyakit;

    public PenyakitResponse(boolean error, String msg, List<Penyakit> penyakit) {
        this.error = error;
        this.msg = msg;
        this.penyakit = penyakit;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public List<Penyakit> getPenyakit() {
        return penyakit;
    }
}
