package himasif.ilkom.unej.ac.id.jemberklinik.Response;

import com.google.gson.annotations.SerializedName;

public class NomorResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("nomor")
    private String nomor;

    public NomorResponse(boolean error, String nomor) {
        this.error = error;
        this.nomor = nomor;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
