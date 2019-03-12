package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Adapter.DokterUserAdapter;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.User;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.UsersResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterSemuaUser extends AppCompatActivity {
    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;
    private List<User> list;
    private DokterUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_semua_user);
        ButterKnife.bind(this);
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        getAllUsers();
    }

    private void getAllUsers() {
        Call<UsersResponse> call = Service
                .getInstance()
                .getAPI()
                .getAllUsers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                list = response.body().getUsers();
                adapter = new DokterUserAdapter(getApplicationContext(), list);
                rvUsers.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(DokterSemuaUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
