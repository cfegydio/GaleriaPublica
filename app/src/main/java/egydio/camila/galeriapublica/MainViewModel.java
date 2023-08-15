package egydio.camila.galeriapublica;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    // guarda a escolha do usuário
    int navigationOpSelected = R.id.gridViewOp;

    // acessar o mainView
    public MainViewModel(@NonNull Application application){
        super(application);
    }

    // setar o valor
    public int getNavigationOpSelected(){
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }
}
