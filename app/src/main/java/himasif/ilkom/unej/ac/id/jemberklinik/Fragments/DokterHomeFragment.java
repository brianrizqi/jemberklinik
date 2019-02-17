package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.HomeAntrianResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DokterHomeFragment extends Fragment {
    @BindView(R.id.txtWelcome)
    TextView txtWelcome;
    @BindView(R.id.txtHari)
    TextView txtHari;
    @BindView(R.id.txtJam)
    TextView txtJam;
    @BindView(R.id.txtAntrian)
    TextView txtAntrian;
    TinyDB tinyDB;
    Calendar calendar;
    int id;


    public DokterHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dokter_home, container, false);
        ButterKnife.bind(this, view);
        calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm a"); //Date and time
        String currentDate = sdf.format(calendar.getTime());
        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = sdf_.format(date);
        txtHari.setText("Hari " + dayName);
        txtJam.setText(currentDate);
        tinyDB = new TinyDB(getActivity());
        getAntrian();
        getNama();
        return view;
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
                } else {
                    txtAntrian.setText(response.body().getAntrian().getAntrian());
                }
            }

            @Override
            public void onFailure(Call<HomeAntrianResponse> call, Throwable t) {

            }
        });
    }

    private void getNama() {
        id = tinyDB.getInt("id_user");
        Call<LoginResponse> call = Service
                .getInstance()
                .getAPI()
                .getUserId(id);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                txtWelcome.setText("Welcome " + loginResponse.getUser().getNama());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
