package prescription.technology;

import android.os.Bundle;
import prescription.technology.code.PrescriptionTechnologyActivity;

public class Index extends PrescriptionTechnologyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/views/index.html");
    }
    
}
