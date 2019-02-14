package himasif.ilkom.unej.ac.id.jemberklinik.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.Activities.Login;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DokterProfileFragment extends Fragment {
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    TinyDB tinyDB;
    int id;


    public DokterProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dokter_profile, container, false);
        ButterKnife.bind(this, view);
        tinyDB = new TinyDB(getActivity());
        getUser();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinyDB.clear();
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }

    private void getUser() {
        id = tinyDB.getInt("id_user");
        Call<LoginResponse> call = Service
                .getInstance()
                .getAPI()
                .getUserId(id);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                txtNama.setText(loginResponse.getUser().getNama());
                txtUser.setText("Admin");
                if (loginResponse.getUser().getJenis_kelamin().equalsIgnoreCase("laki_laki")) {
                    imgProfile.setImageResource(R.drawable.boy);
                } else {
                    imgProfile.setImageResource(R.drawable.girl);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
