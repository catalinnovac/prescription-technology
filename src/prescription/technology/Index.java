package prescription.technology;

import android.os.Bundle;
import prescription.technology.code.PrescriptionTechnologyWithNavigationDrawer;

public class Index extends PrescriptionTechnologyWithNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);
        appView.loadUrl("file:///android_asset/www/index.html");
    }
    
}
