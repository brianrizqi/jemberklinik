package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;


import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;

public class PemesananPasienResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("pemesanan")
    private DokterPemesanan pemesanan;

    public PemesananPasienResponse(boolean error, String msg, DokterPemesanan pemesanan) {
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

    public DokterPemesanan getPemesanan() {
        return pemesanan;
    }
}
