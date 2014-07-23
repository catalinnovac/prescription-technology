package prescription.technology.code;

import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.DroidGap;

public class PrescriptionTechnologyActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        appView.addJavascriptInterface(this, "prescription");
    }

    public void NavigateTo(String action) {
        Intent i = new Intent(action);
        getApplicationContext().startActivity(i);
    }
}
