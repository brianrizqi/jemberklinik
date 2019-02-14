package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    String email, pass;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tinyDB = new TinyDB(getApplicationContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        email = edtEmail.getText().toString();
        pass = edtPass.getText().toString();
        if (email.isEmpty()) {
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            edtPass.setError("Password required");
            edtPass.requestFocus();
            return;
        }

        Call<LoginResponse> call = Service
                .getInstance()
                .getAPI()
                .login(email, pass);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (!loginResponse.isError()) {
                    if (loginResponse.getUser().getLevel().equalsIgnoreCase("1")) {
                        tinyDB.putString("level", loginResponse.getUser().getLevel());
                        tinyDB.putInt("id_user", loginResponse.getUser().getIdUser());
                        Intent i = new Intent(Login.this, Dokter.class);
                        startActivity(i);
                        finish();
                    } else {
                        tinyDB.putString("level", loginResponse.getUser().getLevel());
                        tinyDB.putInt("id_user", loginResponse.getUser().getIdUser());
                        Intent i = new Intent(Login.this, Pasien.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(Login.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
