package prescription.technology.code.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import prescription.technology.code.PrescriptionTechnologyWithNavigationDrawer;
import prescription.technology.code.navigation.drawer.CustomCordovaWebView;

/**
 * Created by novac on 07-Aug-14.
 */
public class CartBroadcastReceiver extends BroadcastReceiver {
    private final static String TAG = CartBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        CustomCordovaWebView cartView = PrescriptionTechnologyWithNavigationDrawer.__cart;
        Log.v(TAG, "send javascript");
        cartView.sendJavascript("window.dispatchEvent(evt)");
    }
}
