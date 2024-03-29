package himasif.ilkom.unej.ac.id.jemberklinik.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import himasif.ilkom.unej.ac.id.jemberklinik.BottomNavigationHelper;
import himasif.ilkom.unej.ac.id.jemberklinik.Fragments.DokterHomeFragment;
import himasif.ilkom.unej.ac.id.jemberklinik.Fragments.DokterPemesananFragment;
import himasif.ilkom.unej.ac.id.jemberklinik.Fragments.DokterProfileFragment;
import himasif.ilkom.unej.ac.id.jemberklinik.R;

public class Dokter extends AppCompatActivity {
    @BindView(R.id.fragment)
    RelativeLayout fragment;
    android.support.v4.app.Fragment home, pemesanan, profile;
    String id, nama, email, alamat;

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Bundle data = new Bundle();
            android.support.v4.app.Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = home;
                    if (fragment == null) {
                        home = new DokterHomeFragment();
                    }
                    break;
                case R.id.navigation_pemesanan:
                    fragment = pemesanan;
                    if (fragment == null) {
                        pemesanan = new DokterPemesananFragment();
                        fragment = pemesanan;
                    }
                    break;
                case R.id.navigation_profile:
                    fragment = profile;
                    if (fragment == null) {
                        profile = new DokterProfileFragment();
                        fragment = profile;
                    }
                    break;
            }
            if (fragment != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
            return fragment != null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);
        ButterKnife.bind(this);
        DokterHomeFragment dokterHomeFragment = new DokterHomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, dokterHomeFragment);
        fragmentTransaction.commit();


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(listener);
        BottomNavigationHelper.disableShiftMode(navigationView);
    }
}
