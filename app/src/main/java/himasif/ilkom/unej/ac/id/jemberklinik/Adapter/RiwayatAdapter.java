package himasif.ilkom.unej.ac.id.jemberklinik.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import himasif.ilkom.unej.ac.id.jemberklinik.Model.Riwayat;
import himasif.ilkom.unej.ac.id.jemberklinik.R;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    private Context context;
    private List<Riwayat> list;

    public RiwayatAdapter(Context context, List<Riwayat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_riwayat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Riwayat riwayat = list.get(i);
        viewHolder.txtNama.setText(riwayat.getNama());
        viewHolder.txtKeluhan.setText(riwayat.getKeluhan());
        viewHolder.txtTanggal.setText(riwayat.getTanggal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtKeluhan)
        TextView txtKeluhan;
        @BindView(R.id.txtTanggal)
        TextView txtTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
