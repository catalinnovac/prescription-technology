package prescription.technology.code;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import org.apache.cordova.DroidGap;

public class PrescriptionTechnologyActivity extends DroidGap {

    public final String TAG = "PRESCRIPTION TECHNOLOGY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        appView.addJavascriptInterface(this, "prescription");
        appView.addJavascriptInterface(new Constants(), "constants");
    }

    @JavascriptInterface
    public void NavigateTo(String action) {
        Log.v(TAG, action);
        boolean valid = false;
        Constants constant = new Constants();
        if (action.equals(constant.CUSTOMER_ADDRESS_ACTIVITY))
            valid = true;
        if (action.equals(constant.CUSTOMER_PERSONAL_INFO_ACTIVITY))
            valid = true;
        if (action.equals(constant.ORDER_CONFIRMARTION_ACTIVITY))
            valid = true;
        if (action.equals(constant.QUESTIONS_ACTIVITY))
            valid = true;
        if (valid) {
            Intent i = new Intent(action);
            startActivity(i);
        } else
            try {
                throw new Exception("Invalid action!");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
