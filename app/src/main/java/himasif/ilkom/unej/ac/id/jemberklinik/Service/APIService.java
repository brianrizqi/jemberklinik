package himasif.ilkom.unej.ac.id.jemberklinik.Service;

import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
