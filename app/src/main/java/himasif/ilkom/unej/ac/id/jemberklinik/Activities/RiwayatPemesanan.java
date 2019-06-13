package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Adapter.RiwayatAdapter;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.Riwayat;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.RiwayatResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemesanan extends AppCompatActivity {
    @BindView(R.id.rvRiwayat)
    RecyclerView rvRiwayat;
    private List<Riwayat> list;
    private RiwayatAdapter adapter;
    TinyDB tinyDB;
    int id;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemesanan);
        ButterKnife.bind(this);
        tinyDB = new TinyDB(getApplicationContext());
        level = tinyDB.getString("level");
        if (level.equalsIgnoreCase("1")) {
            id = getIntent().getIntExtra("id_user", 0);
        } else {
            id = tinyDB.getInt("id_user");
        }
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));
        getRiwayat(id);
    }

    private void getRiwayat(int id) {
        Call<RiwayatResponse> call = Service
                .getInstance()
                .getAPI()
                .riwayat(id);
        call.enqueue(new Callback<RiwayatResponse>() {
            @Override
            public void onResponse(Call<RiwayatResponse> call, Response<RiwayatResponse> response) {
                list = response.body().getPemesanan();
                adapter = new RiwayatAdapter(getApplicationContext(), list);
                rvRiwayat.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RiwayatResponse> call, Throwable t) {
                Toast.makeText(RiwayatPemesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
