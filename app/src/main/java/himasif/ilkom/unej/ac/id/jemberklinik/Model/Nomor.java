package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class Nomor {

    @SerializedName("antrian")
    String antrian;
    @SerializedName("selesai")
    String selesai;
    @SerializedName("error")
    Boolean error;

    public Nomor(String antrian, String selesai, Boolean error) {
        this.antrian = antrian;
        this.selesai = selesai;
        this.error = error;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getAntrian() {
        return antrian;
    }

    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
