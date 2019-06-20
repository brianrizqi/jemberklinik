package himasif.ilkom.unej.ac.id.jemberklinik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.Activities.RiwayatPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.User;
import himasif.ilkom.unej.ac.id.jemberklinik.R;
import himasif.ilkom.unej.ac.id.jemberklinik.Response.DefaultResponse;
import himasif.ilkom.unej.ac.id.jemberklinik.Service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterUserAdapter extends RecyclerView.Adapter<DokterUserAdapter.ViewHolder> {
    private Context context;
    private List<User> list;
    TinyDB tinyDB;

    public DokterUserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User user = list.get(i);
        tinyDB = new TinyDB(context);
        viewHolder.txtNama.setText(user.getNama());
        if (user.getJenis_kelamin().equalsIgnoreCase("P")) {
            viewHolder.imgProfile.setImageResource(R.drawable.girl);
        } else {
            viewHolder.imgProfile.setImageResource(R.drawable.boy);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(user.getIdUser()), Toast.LENGTH_SHORT).show();
            }
        });
        if (tinyDB.getInt("id_user") == 2) {
            viewHolder.btnHapus.setVisibility(View.VISIBLE);
        } else {
            viewHolder.btnHapus.setVisibility(View.GONE);
        }
        viewHolder.btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RiwayatPemesanan.class);
                i.putExtra("id_user", user.getIdUser());
                context.startActivity(i);
            }
        });

        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DefaultResponse> call = Service
                        .getInstance()
                        .getAPI()
                        .deleteUser(user.getIdUser());
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgProfile)
        CircleImageView imgProfile;
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.btnHapus)
        Button btnHapus;
        @BindView(R.id.btnRiwayat)
        Button btnRiwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
