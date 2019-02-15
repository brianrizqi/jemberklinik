package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananPasienResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterDetailPemesanan extends AppCompatActivity {
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtKategori)
    TextView txtKategori;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.txtKeluhan)
    TextView txtKeluhan;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_detail_pemesanan);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id_user");
        getUser();

    }

    private void getUser() {
        Call<PemesananPasienResponse> call = Service
                .getInstance()
                .getAPI()
                .getPemesananId(Integer.parseInt(id));
        call.enqueue(new Callback<PemesananPasienResponse>() {
            @Override
            public void onResponse(Call<PemesananPasienResponse> call, Response<PemesananPasienResponse> response) {
                txtNama.setText(response.body().getPemesanan().getNama());
                txtKategori.setText("Sakit " + response.body().getPemesanan().getKategori());
                txtKeluhan.setText(response.body().getPemesanan().getKeluhan());
                txtStatus.setText("Status : " + response.body().getPemesanan().getStatus());
                if (response.body().getPemesanan().getJenis_kelamin().equalsIgnoreCase("perempuan")) {
                    imgProfile.setImageResource(R.drawable.girl);
                } else {
                    imgProfile.setImageResource(R.drawable.boy);
                }
            }

            @Override
            public void onFailure(Call<PemesananPasienResponse> call, Throwable t) {
                Toast.makeText(DokterDetailPemesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
