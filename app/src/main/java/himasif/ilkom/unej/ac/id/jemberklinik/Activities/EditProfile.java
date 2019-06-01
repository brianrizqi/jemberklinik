package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.LoginResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.edtNoTelp)
    EditText edtNoTelp;
    @BindView(R.id.edtTanggal)
    TextView edtTanggal;
    @BindView(R.id.btnTanggal)
    Button btnTanggal;
    @BindView(R.id.edtJenisKelamin)
    RadioGroup edtJenisKelamin;
    @BindView(R.id.radioLaki)
    RadioButton radioLaki;
    @BindView(R.id.radioPerempuan)
    RadioButton radioPerempuan;
    @BindView(R.id.edtBpjs)
    EditText edtBpjs;
    @BindView(R.id.btnEdit)
    Button btnEdit;
    String nama, email, pass, alamat, noTelp, tanggal, jenisKelamin, bpjs;
    int id;
    TinyDB tinyDB;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        tinyDB = new TinyDB(getApplicationContext());
        id = tinyDB.getInt("id_user");
        tampil();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    private void tampil() {
        Call<LoginResponse> call = Service
                .getInstance()
                .getAPI()
                .getUserId(id);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                edtNama.setText(loginResponse.getUser().getNama());
                edtAlamat.setText(loginResponse.getUser().getAlamat());
                edtEmail.setText(loginResponse.getUser().getEmail());
                edtPass.setText(loginResponse.getUser().getPassword());
                edtNoTelp.setText(loginResponse.getUser().getNo_telp());
                edtTanggal.setText(loginResponse.getUser().getTanggal_lahir());
                if (loginResponse.getUser().getJenis_kelamin() == "perempuan") {
                    radioPerempuan.setChecked(true);
                    radioLaki.setChecked(false);
                } else {
                    radioPerempuan.setChecked(false);
                    radioLaki.setChecked(true);
                }
                edtBpjs.setText(loginResponse.getUser().getBpjs());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void edit() {
        nama = edtNama.getText().toString();
        email = edtEmail.getText().toString();
        pass = edtPass.getText().toString();
        alamat = edtAlamat.getText().toString();
        noTelp = edtNoTelp.getText().toString();
        tanggal = edtTanggal.getText().toString();
        int selectedId = edtJenisKelamin.getCheckedRadioButtonId();
        if (selectedId == radioLaki.getId()) {
            jenisKelamin = "laki_laki";
        } else if (selectedId == radioPerempuan.getId()) {
            jenisKelamin = "perempuan";
        }
        bpjs = edtBpjs.getText().toString();
        if (nama.isEmpty()) {
            edtNama.setError("Nama is required");
            edtNama.requestFocus();
            return;
        }
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
        if (alamat.isEmpty()) {
            edtAlamat.setError("Alamat is required");
            edtAlamat.requestFocus();
            return;
        }
        if (noTelp.isEmpty()) {
            edtNoTelp.setError("No Telp is required");
            edtNoTelp.requestFocus();
            return;
        }
        if (bpjs.isEmpty()) {
            edtBpjs.setError("Bpjs is required");
            edtBpjs.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            edtPass.setError("Password required");
            edtPass.requestFocus();
            return;
        }

        Call<DefaultResponse> call = Service
                .getInstance()
                .getAPI()
                .updateUser(nama, email, pass, alamat, noTelp, jenisKelamin, tanggal, bpjs, id);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (!defaultResponse.isError()) {
                    Toast.makeText(EditProfile.this, defaultResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                edtTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
