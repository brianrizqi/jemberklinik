package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Adapter.DokterPemesananAdapter;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.User;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.NomorResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.PemesananResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DokterPemesananFragment extends Fragment {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvDokterPemesanan)
    RecyclerView rvDokterPemesanan;
    DokterPemesananAdapter adapter;
    List<DokterPemesanan> list;
    @BindView(R.id.txtCek)
    TextView txtCek;


    public DokterPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dokter_pemesanan, container, false);
        ButterKnife.bind(this, view);

        rvDokterPemesanan.setHasFixedSize(true);
        rvDokterPemesanan.setLayoutManager(new LinearLayoutManager(getActivity()));
        cekNomor();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPemesanan();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void cekNomor() {
        Call<NomorResponse> call = Service
                .getInstance()
                .getAPI()
                .cekNomor();
        call.enqueue(new Callback<NomorResponse>() {
            @Override
            public void onResponse(Call<NomorResponse> call, Response<NomorResponse> response) {
                if (response.body().isError()) {
                    txtCek.setText("Belum ada yang pesan");
                    rvDokterPemesanan.setVisibility(View.GONE);
                } else {
                    getPemesanan();
                }
            }

            @Override
            public void onFailure(Call<NomorResponse> call, Throwable t) {

            }
        });
    }

    private void getPemesanan() {
        Call<PemesananResponse> call = Service
                .getInstance()
                .getAPI()
                .getPemesanan();
        call.enqueue(new Callback<PemesananResponse>() {
            @Override
            public void onResponse(Call<PemesananResponse> call, Response<PemesananResponse> response) {
                list = response.body().getPemesanan();
                adapter = new DokterPemesananAdapter(getActivity(), list);
                rvDokterPemesanan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PemesananResponse> call, Throwable t) {

            }
        });
    }

}
