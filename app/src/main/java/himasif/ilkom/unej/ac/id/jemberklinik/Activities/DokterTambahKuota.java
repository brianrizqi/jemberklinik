package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeKuotaResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterTambahKuota extends AppCompatActivity {
    @BindView(R.id.edtKuota)
    EditText edtKuota;
    @BindView(R.id.btnJamAwal)
    Button btnJamAwal;
    @BindView(R.id.btnJamAkhir)
    Button btnJamAkhir;
    @BindView(R.id.txtJamAwal)
    TextView txtJamAwal;
    @BindView(R.id.txtJamAkhir)
    TextView txtJamAkhir;
    @BindView(R.id.btnTambah)
    Button btnTambah;
    String jamAwal, jamAkhir, kuota;
    TimePickerDialog pickerJamAwal;
    TimePickerDialog pickerJamAkhir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_tambah_kuota);
        ButterKnife.bind(this);
        btnJamAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jam = c.get(Calendar.HOUR_OF_DAY);
                int menit = c.get(Calendar.MINUTE);
                pickerJamAwal = new TimePickerDialog(DokterTambahKuota.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        txtJamAwal.setText(String.format("%02d:%02d", hourOfDay, minute) + ":00");
                    }
                }, jam, menit, true);
                pickerJamAwal.show();
            }
        });
        btnJamAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jam = c.get(Calendar.HOUR_OF_DAY);
                int menit = c.get(Calendar.MINUTE);
                pickerJamAkhir = new TimePickerDialog(DokterTambahKuota.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        txtJamAkhir.setText(String.format("%02d:%02d", hourOfDay, minute) + ":00");
                    }
                }, jam, menit, true);
                pickerJamAkhir.show();
            }
        });
        getKuota();
    }

    private void getKuota() {
        Call<HomeKuotaResponse> call = Service
                .getInstance()
                .getAPI()
                .getKuota();
        call.enqueue(new Callback<HomeKuotaResponse>() {
            @Override
            public void onResponse(Call<HomeKuotaResponse> call, Response<HomeKuotaResponse> response) {
                final HomeKuotaResponse kuotaResponse = response.body();
                if (!kuotaResponse.isError()) {
                    edtKuota.setText(kuotaResponse.getKuota().getKuota());
                    txtJamAwal.setText(kuotaResponse.getKuota().getJam_awal());
                    txtJamAkhir.setText(kuotaResponse.getKuota().getJam_akhir());
                    btnTambah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editkuota(kuotaResponse.getKuota().getId_kuota());
                        }
                    });
                } else {
                    btnTambah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            kuota();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<HomeKuotaResponse> call, Throwable t) {

            }
        });
    }

    private void editkuota(int id_kuota) {
        kuota = edtKuota.getText().toString();
        jamAwal = txtJamAwal.getText().toString();
        jamAkhir = txtJamAkhir.getText().toString();

        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .editKuota(id_kuota, kuota, jamAwal, jamAkhir);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (!defaultResponse.isError()) {
                    Toast.makeText(DokterTambahKuota.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                } else {
                    Toast.makeText(DokterTambahKuota.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(DokterTambahKuota.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kuota() {
        kuota = edtKuota.getText().toString();
        jamAwal = txtJamAwal.getText().toString();
        jamAkhir = txtJamAkhir.getText().toString();

        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .kuota(kuota, jamAwal, jamAkhir);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (!defaultResponse.isError()) {
                    Toast.makeText(DokterTambahKuota.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                } else {
                    Toast.makeText(DokterTambahKuota.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(DokterTambahKuota.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
