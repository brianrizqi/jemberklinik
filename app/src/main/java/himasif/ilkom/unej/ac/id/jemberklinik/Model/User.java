package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private int idUser;
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("tanggal_lahir")
    private String tanggal_lahir;
    @SerializedName("bpjs")
    private String bpjs;
    @SerializedName("level")
    private String level;

    public User(int idUser, String nama, String email, String password, String alamat, String no_telp, String jenis_kelamin, String tanggal_lahir, String bpjs, String level) {
        this.idUser = idUser;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.bpjs = bpjs;
        this.level = level;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getBpjs() {
        return bpjs;
    }

    public void setBpjs(String bpjs) {
        this.bpjs = bpjs;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
