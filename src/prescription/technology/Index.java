package prescription.technology;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import prescription.technology.code.PrescriptionTechnologyWithNavigationDrawer;
import prescription.technology.code.receivers.CartBroadcastReceiver;

import java.util.HashMap;

public class Index extends PrescriptionTechnologyWithNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    protected HashMap<String, BroadcastReceiver> GetBroadcastsMap() {
        HashMap<String, BroadcastReceiver> map = new HashMap<String, BroadcastReceiver>();
        CartBroadcastReceiver br = new CartBroadcastReceiver();
        map.put("CART", br);
        return map;
    }

}
