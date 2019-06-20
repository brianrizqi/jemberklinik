package himasif.ilkom.unej.ac.id.jemberklinik.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.Activities.DokterDetailPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.TinyDB;
import himasif.ilkom.unej.ac.id.jemberklinik.R;

public class DokterPemesananAdapter extends RecyclerView.Adapter<DokterPemesananAdapter.ViewHolder> {
    TinyDB tinyDB;
    private Activity activity;
    private List<DokterPemesanan> list;

    public DokterPemesananAdapter(Activity activity, List<DokterPemesanan> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(activity).inflate(R.layout.item_pemesanan, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        tinyDB = new TinyDB(activity);
        final DokterPemesanan dokterPemesanan = list.get(i);
        if (dokterPemesanan.getNomor() == 0) {
            viewHolder.txtNomor.setText("");
        } else {
            viewHolder.txtNomor.setText(String.valueOf(dokterPemesanan.getNomor()));
        }
        viewHolder.txtKategori.setText("Status : " + dokterPemesanan.getStatus());
        viewHolder.txtNama.setText(dokterPemesanan.getNama());
        if (dokterPemesanan.getJenis_kelamin().equalsIgnoreCase("P")) {
            viewHolder.imgProfile.setImageResource(R.drawable.girl);
        } else {
            viewHolder.imgProfile.setImageResource(R.drawable.boy);
        }
        if (tinyDB.getInt("id_user") == 2) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(activity, DokterDetailPemesanan.class);
                    i.putExtra("id_user", dokterPemesanan.getId());
                    activity.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNomor)
        TextView txtNomor;
        @BindView(R.id.imgProfile)
        CircleImageView imgProfile;
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtKategori)
        TextView txtKategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
