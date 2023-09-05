package egydio.camila.galeriapublica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.Manifest;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_REQUEST_PERMISSION = 2;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

        bottomNavigationView = findViewById(R.id.btNav);
        // seleção de menu
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // opção escolhida
                vm.setNavigationOpSelected(item.getItemId());
                // cria os fragments no main de acordo com a opção escolhida
                switch (item.getItemId()) {
                    case R.id.gridViewOp:
                        GridViewFragment gridViewFragment = GridViewFragment.newInstance();
                        setFragment(gridViewFragment);
                        break;
                    case R.id.listViewOp:
                        ListViewFragment listViewFragment = ListViewFragment.newInstance();
                        setFragment(listViewFragment);
                        break;
                }
                return true;
            }
        });
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // permissao do dispositivo
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkForPermissions(permissions);
    }

    private void checkForPermissions(List<String> permissions) {
        List<String> permissionsNotGranted = new ArrayList<>();

        for (String permission : permissions){
            if( !hasPermission(permission)) {
                permissionsNotGranted.add(permission);
            }
        }
        if(permissionsNotGranted.size() > 0) {
            // pedir permissoes ainda nao concedidas
            requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), RESULT_REQUEST_PERMISSION);
        }
        else {
            MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
            int navigationOpSelected = vm.getNavigationOpSelected();
            bottomNavigationView.setSelectedItemId(navigationOpSelected);
        }
    }

    private boolean hasPermission(String permission) {
        // verifica se a permissao ja foi autorizada pelo usuario
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        // metodo chamado após conceder as permissoes
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        final List<String> permissionsRejected = new ArrayList<>();
        // verifica se cada permissao foi autorizada
        if(requestCode == RESULT_REQUEST_PERMISSION) {

            for(String permission : permissions) {
                if ( !hasPermission(permission)) {
                    permissionsRejected.add(permission);
                }
            }
        }

        if (permissionsRejected.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0)));
            }
            else{
                MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
                int navigationOpSelected = vm.getNavigationOpSelected();
                bottomNavigationView.setSelectedItemId(navigationOpSelected);

            }

            // avisa o usuario que a permissao é necessaria para o funcionamento da app
            new AlertDialog.Builder(MainActivity.this).setMessage("Para usar essa app é preciso conceder essas permissões").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                // pede as permissoes novamente
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                }
            }).create().show();

        }
    }



}
