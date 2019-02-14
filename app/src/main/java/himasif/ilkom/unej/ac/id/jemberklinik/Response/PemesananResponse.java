package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;

public class PemesananResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("pemesanan")
    private List<DokterPemesanan> pemesanan;

    public PemesananResponse(boolean error, String msg, List<DokterPemesanan> pemesanan) {
        this.error = error;
        this.msg = msg;
        this.pemesanan = pemesanan;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public List<DokterPemesanan> getPemesanan() {
        return pemesanan;
    }
}
