package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.Riwayat;

public class RiwayatResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String msg;
    @SerializedName("pemesanan")
    private List<Riwayat> pemesanan;

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public List<Riwayat> getPemesanan() {
        return pemesanan;
    }
}
