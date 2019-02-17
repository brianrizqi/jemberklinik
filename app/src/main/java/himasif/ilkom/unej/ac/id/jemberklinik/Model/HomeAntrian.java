package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class HomeAntrian {
    @SerializedName("antrian")
    String antrian;
    @SerializedName("selesai")
    String selesai;

    public HomeAntrian(String antrian, String selesai) {
        this.antrian = antrian;
        this.selesai = selesai;
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
}
