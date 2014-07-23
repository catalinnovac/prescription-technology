package prescription.technology;

import android.os.Bundle;
import prescription.technology.code.PrescriptionTechnologyActivity;

public class Customer_personal_info extends PrescriptionTechnologyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/customerpersonalinfo.html");
    }

}
