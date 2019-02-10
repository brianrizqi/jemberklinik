package himasif.ilkom.unej.ac.id.jemberklinik;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PasienPemesananFragment extends Fragment {
    @BindView(R.id.sebelumPesan)
    LinearLayout sebelumPesan;
    @BindView(R.id.sesudahPesan)
    LinearLayout sesudahPesan;
    @BindView(R.id.btnPesan)
    Button btnPesan;

    public PasienPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasien_pemesanan, container, false);
        ButterKnife.bind(this, view);
        sebelumPesan.setVisibility(View.VISIBLE);
        sesudahPesan.setVisibility(View.GONE);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        return view;
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_pesan, null);
        Button btnYa = (Button) view.findViewById(R.id.btnYa);
        Button btnTidak = (Button) view.findViewById(R.id.btnTidak);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesudahPesan.setVisibility(View.VISIBLE);
                sebelumPesan.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

}
