package himasif.ilkom.unej.ac.id.jemberklinik.Service;

import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananPasienResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananResponse;
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

    @FormUrlEncoded
    @POST("pemesanan")
    Call<DefaultResponse> pemesanan(
            @Field("id_user") int id_user,
            @Field("keluhan") String keluhan,
            @Field("kategori") String kategori,
            @Field("status") String status
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
}
