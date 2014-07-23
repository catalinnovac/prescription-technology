package prescription.technology;

import android.os.Bundle;
import prescription.technology.code.PrescriptionTechnologyActivity;

public class Order_confirmation extends PrescriptionTechnologyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/orderconfirmation.html");
    }

}
