package himasif.ilkom.unej.ac.id.jemberklinik.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.DokterPemesanan;
import himasif.ilkom.unej.ac.id.jemberklinik.R;

public class DokterPemesananAdapter extends RecyclerView.Adapter<DokterPemesananAdapter.ViewHolder> {
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DokterPemesanan dokterPemesanan = list.get(i);
        viewHolder.txtNomor.setText(String.valueOf(i + 1));
        viewHolder.txtKategori.setText(dokterPemesanan.getKategori());
        viewHolder.txtNama.setText(dokterPemesanan.getNama());
        if (dokterPemesanan.getJenis_kelamin().equalsIgnoreCase("perempuan")) {
            viewHolder.imgProfile.setImageResource(R.drawable.girl);
        } else {
            viewHolder.imgProfile.setImageResource(R.drawable.boy);
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
