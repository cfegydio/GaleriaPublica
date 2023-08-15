package egydio.camila.galeriapublica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

        bottomNavigationView = findViewById(R.id.btNav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()) {

            @Override
            public boolean onNavegationItemSelected(@NonNull MenuItem item){
            vm.setNavigationOpSelected(item.getItemId());
            switch (item.getItemId()) {
                case R.id.gridViewOp:
                    GridViewFragment gridViewFragment = GridViewFragment.newInstance();
                    setFragment(gridViewFragment);
                    break;
                case R.id.listViewOp:
                    ListViewFragment = ListViewFragment.newInstance();
                    break;
            }
            return true;
            }
        };

    }
}