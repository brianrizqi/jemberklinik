package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
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
    TinyDB tinyDB;
    int idUser;

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
        Call<PemesananResponse> call = Service
                .getInstance()
                .getAPI()
                .getPemesananId(idUser);
        call.enqueue(new Callback<PemesananResponse>() {
            @Override
            public void onResponse(Call<PemesananResponse> call, Response<PemesananResponse> response) {
                PemesananResponse pemesananResponse = response.body();
                Toast.makeText(getActivity(), String.valueOf(pemesananResponse.isError()), Toast.LENGTH_SHORT).show();
                if (pemesananResponse.isError()) {
                    sebelumPesan.setVisibility(View.VISIBLE);
                    sesudahPesan.setVisibility(View.GONE);
                } else {
                    sebelumPesan.setVisibility(View.GONE);
                    sesudahPesan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<PemesananResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
                sesudahPesan.setVisibility(View.VISIBLE);
                sebelumPesan.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    private void pemesanan() {

    }

}
