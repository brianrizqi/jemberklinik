package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananPasienResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananResponse;
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
    @BindView(R.id.edtKategori)
    RadioGroup edtKategori;
    @BindView(R.id.radioBiasa)
    RadioButton radioBiasa;
    @BindView(R.id.radioSerius)
    RadioButton radioSerius;
    @BindView(R.id.edtKeluhan)
    EditText edtKeluahan;
    String keluhan, kategori, status;

    public PasienPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasien_pemesanan, container, false);
        ButterKnife.bind(this, view);
        tinyDB = new TinyDB(getActivity());
        idUser = tinyDB.getInt("id_user");
        getPemesanan();
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        return view;
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
                } else {
                    txtAntrian.setText(String.valueOf(pemesananPasienResponse.getPemesanan().getNomor()));
                    sebelumPesan.setVisibility(View.GONE);
                    sesudahPesan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<PemesananPasienResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "erereorere", Toast.LENGTH_SHORT).show();
            }
        });
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
        keluhan = edtKeluahan.getText().toString();
        int selectedId = edtKategori.getCheckedRadioButtonId();
        if (selectedId == radioBiasa.getId()) {
            kategori = "biasa";
        } else if (selectedId == radioSerius.getId()) {
            kategori = "serius";
        }
        status = "menunggu";

        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .pemesanan(idUser, keluhan, kategori, status);
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
