package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
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
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.txtKeluhan)
    TextView txtKeluhan;
    @BindView(R.id.btnVerif)
    Button btnVerif;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    String id, verif, id_pemesanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_detail_pemesanan);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id_user");
        getUser();
        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verif();
            }
        });
    }

    private void verif() {
        verif = btnVerif.getText().toString();
        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .verifPemesanan(verif, id_pemesanan);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (!response.body().isError()) {
                    Toast.makeText(DokterDetailPemesanan.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                } else {
                    Toast.makeText(DokterDetailPemesanan.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    private void getUser() {
        progressBar.setVisibility(View.INVISIBLE);
        Call<PemesananPasienResponse> call = Service
                .getInstance()
                .getAPI()
                .getPemesananId(Integer.parseInt(id));
        call.enqueue(new Callback<PemesananPasienResponse>() {
            @Override
            public void onResponse(Call<PemesananPasienResponse> call, Response<PemesananPasienResponse> response) {
                id_pemesanan = response.body().getPemesanan().getId_pemesanan();
                txtNama.setText(response.body().getPemesanan().getNama());
                txtKeluhan.setText(response.body().getPemesanan().getKeluhan());
                txtStatus.setText("Status : " + response.body().getPemesanan().getStatus());
                if (response.body().getPemesanan().getJenis_kelamin().equalsIgnoreCase("perempuan")) {
                    imgProfile.setImageResource(R.drawable.girl);
                } else {
                    imgProfile.setImageResource(R.drawable.boy);
                }
                if (response.body().getPemesanan().getStatus().equalsIgnoreCase("masuk")) {
                    btnVerif.setText("selesai");
                } else if (response.body().getPemesanan().getStatus().equalsIgnoreCase("menunggu")) {
                    btnVerif.setText("masuk");
                } else {
                    btnVerif.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PemesananPasienResponse> call, Throwable t) {
                Toast.makeText(DokterDetailPemesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
