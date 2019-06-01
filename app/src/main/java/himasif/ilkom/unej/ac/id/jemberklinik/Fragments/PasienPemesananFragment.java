package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.Penyakit;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeAntrianResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeKuotaResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananPasienResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PenyakitResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PasienPemesananFragment extends Fragment {
    @BindView(R.id.sebelumPesan)
    LinearLayout sebelumPesan;
    @BindView(R.id.sesudahPesan)
    LinearLayout sesudahPesan;
    @BindView(R.id.btnPesan)
    Button btnPesan;
    @BindView(R.id.txtAntrian)
    TextView txtAntrian;
    TinyDB tinyDB;
    int idUser;
    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtUmur)
    EditText edtUmur;
    @BindView(R.id.spinPenyakit)
    Spinner spinPenyakit;
    String keluhan, nama, umur;
    @BindView(R.id.txtCek)
    TextView txtCek;
    @BindView(R.id.txtSisa)
    TextView txtSisa;
    Calendar calendar;
    int sisa;
    List<Penyakit> list = new ArrayList<>();

    public PasienPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasien_pemesanan, container, false);
        ButterKnife.bind(this, view);
        tinyDB = new TinyDB(getActivity());
        calendar = Calendar.getInstance();
        idUser = tinyDB.getInt("id_user");
//        getPemesanan();
//        getPenyakit();
        checkWaktu();
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        return view;
    }

    private void checkWaktu() {
        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        final String dayName = sdf_.format(date);
        if (dayName.equalsIgnoreCase("minggu")) {
            Toast.makeText(getActivity(), "Praktek Tutup Hari Minggu", Toast.LENGTH_SHORT).show();
        } else {
            Call<HomeKuotaResponse> call = Service
                    .getInstance()
                    .getAPI()
                    .getKuota();
            call.enqueue(new Callback<HomeKuotaResponse>() {
                @Override
                public void onResponse(Call<HomeKuotaResponse> call, Response<HomeKuotaResponse> response) {
                    HomeKuotaResponse kuotaResponse = response.body();
                    if (kuotaResponse.isError()) {
                        txtCek.setText("Belum Memasuki Waktu Pemesanan");
                        txtCek.setVisibility(View.VISIBLE);
                        sebelumPesan.setVisibility(View.GONE);
                        sesudahPesan.setVisibility(View.GONE);
                    } else {
                        try {
                            String string1 = kuotaResponse.getKuota().getJam_awal();
                            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(time1);

                            String string2 = kuotaResponse.getKuota().getJam_akhir();
                            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(time2);
                            calendar2.add(Calendar.DATE, 1);

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            String currentDate = sdf.format(calendar.getTime());
                            Date d = new SimpleDateFormat("HH:mm:ss").parse(currentDate);

                            if (d.after(calendar1.getTime()) && d.before(calendar2.getTime())) {
                                getPemesanan();
                            } else {
                                txtCek.setVisibility(View.VISIBLE);
                                sebelumPesan.setVisibility(View.GONE);
                                sesudahPesan.setVisibility(View.GONE);
                                txtCek.setText("Belum Memasuki Waktu Pemesanan");
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<HomeKuotaResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getAntrian() {
        Call<HomeAntrianResponse> call = Service
                .getInstance()
                .getAPI()
                .getAntrian();
        call.enqueue(new Callback<HomeAntrianResponse>() {
            @Override
            public void onResponse(Call<HomeAntrianResponse> call, Response<HomeAntrianResponse> response) {
                if (response.body().getAntrian().getAntrian().equalsIgnoreCase("0")) {
                    txtAntrian.setText(response.body().getAntrian().getSelesai());
                    sisa = Integer.parseInt(response.body().getAntrian().getSelesai());
                } else {
                    sisa = Integer.parseInt(response.body().getAntrian().getAntrian());
                }
            }

            @Override
            public void onFailure(Call<HomeAntrianResponse> call, Throwable t) {

            }
        });
    }

    private void getPemesanan() {
        Call<PemesananPasienResponse> call = Service
                .getInstance()
                .getAPI()
                .getPemesananId(idUser);
        call.enqueue(new Callback<PemesananPasienResponse>() {
            @Override
            public void onResponse(Call<PemesananPasienResponse> call, Response<PemesananPasienResponse> response) {
                PemesananPasienResponse pemesananPasienResponse = response.body();
                if (response.body().isError()) {
                    sebelumPesan.setVisibility(View.VISIBLE);
                    sesudahPesan.setVisibility(View.GONE);
                    txtCek.setVisibility(View.GONE);
                    getPenyakit();
                } else {
                    txtAntrian.setText(String.valueOf(pemesananPasienResponse.getPemesanan().getNomor()));
                    sebelumPesan.setVisibility(View.GONE);
                    sesudahPesan.setVisibility(View.VISIBLE);
                    txtCek.setVisibility(View.GONE);
                    getSisa(pemesananPasienResponse.getPemesanan().getNomor());
                }
            }

            @Override
            public void onFailure(Call<PemesananPasienResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSisa(int nomor) {
        getAntrian();
        txtSisa.setText(String.valueOf(nomor - sisa));
    }

    private void getPenyakit() {
        Call<PenyakitResponse> call = Service
                .getInstance()
                .getAPI()
                .getPenyakit();
        call.enqueue(new Callback<PenyakitResponse>() {
            @Override
            public void onResponse(Call<PenyakitResponse> call, Response<PenyakitResponse> response) {
                list = response.body().getPenyakit();
                ArrayAdapter<Penyakit> adapter = new ArrayAdapter<Penyakit>(getActivity(),
                        android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinPenyakit.setAdapter(adapter);
                spinPenyakit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Penyakit penyakit = (Penyakit) spinPenyakit.getSelectedItem();
                        displayPenyakit(penyakit);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {

            }
        });
    }

    private void displayPenyakit(Penyakit penyakit) {
        keluhan = penyakit.getId();
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_pesan, null);
        Button btnYa = (Button) view.findViewById(R.id.btnYa);
        Button btnTidak = (Button) view.findViewById(R.id.btnTidak);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pemesanan();
                sesudahPesan.setVisibility(View.VISIBLE);
                sebelumPesan.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    private void pemesanan() {
        nama = edtNama.getText().toString();
        umur = edtUmur.getText().toString();
        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .pemesanan(idUser, nama, Integer.parseInt(umur), keluhan);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (!defaultResponse.isError()) {
                    Toast.makeText(getActivity(), defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
