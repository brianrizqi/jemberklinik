package himasif.ilkom.unej.ac.id.jemberklinik.Service;

import himasif.ilkom.unej.ac.id.jemberklinik.Model.Kuota;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeAntrianResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeKuotaResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.NomorResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananPasienResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PenyakitResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.UsersResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("regis")
    Call<DefaultResponse> regis(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("alamat") String alamat,
            @Field("no_telp") String no_telp,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("tanggal_lahir") String tanggal_lahir,
            @Field("bpjs") String bpjs,
            @Field("level") String level
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("getUserId")
    Call<LoginResponse> getUserId(
            @Field("id_user") int id_user
    );

    @GET("getPemesanan")
    Call<PemesananResponse> getPemesanan();

    @GET("getAntrian")
    Call<HomeAntrianResponse> getAntrian();

    @GET("getAllUsers")
    Call<UsersResponse> getAllUsers();

    @FormUrlEncoded
    @POST("pemesanan")
    Call<DefaultResponse> pemesanan(
            @Field("id_user") int id_user,
            @Field("nama") String nama,
            @Field("umur") int umur,
            @Field("id_penyakit") String id_penyakit
    );

    @FormUrlEncoded
    @POST("getPemesananId")
    Call<PemesananPasienResponse> getPemesananId(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("verifPemesanan")
    Call<DefaultResponse> verifPemesanan(
            @Field("verif") String verif,
            @Field("id_pemesanan") String id_pemesanan
    );

    @FormUrlEncoded
    @POST("kuota")
    Call<DefaultResponse> kuota(
            @Field("kuota") String kuota,
            @Field("jam_awal") String jam_awal,
            @Field("jam_akhir") String jam_akhir
    );

    @FormUrlEncoded
    @POST("editKuota")
    Call<DefaultResponse> editKuota(
            @Field("id_kuota") int id_kuota,
            @Field("kuota") String kuota,
            @Field("jam_awal") String jam_awal,
            @Field("jam_akhir") String jam_akhir
    );

    @GET("getKuota")
    Call<HomeKuotaResponse> getKuota();

    @GET("getPenyakit")
    Call<PenyakitResponse> getPenyakit();

    @GET("cekNomor")
    Call<NomorResponse> cekNomor();

    @GET("analisa")
    Call<DefaultResponse> analisa();
}
