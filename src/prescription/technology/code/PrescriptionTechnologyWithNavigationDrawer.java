package prescription.technology.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import prescription.technology.R;
import prescription.technology.code.navigation.drawer.Adapter;
import prescription.technology.code.navigation.drawer.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by novac on 24-Jul-14.
 */
public class PrescriptionTechnologyWithNavigationDrawer extends Activity implements CordovaInterface {
    public final String TAG = "PRESCRIPTION TECHNOLOGY";
    protected CordovaWebView appView;
    protected CordovaWebView leftView;
    boolean activityResultKeepRunning;
    boolean keepRunning;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CordovaPlugin activityResultCallback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appView = (CordovaWebView) findViewById(R.id.cordova_main_webview);
        Config.init(this);
        appView.addJavascriptInterface(this, "prescription");
        appView.addJavascriptInterface(new Constants(), "constants");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        List<Item> items = new ArrayList<Item>();
        Item item = new Item();
        item.Id = "shopping-cart";
        item.FILE_URL = "file:///android_asset/www/shoppingcart.html";
        items.add(item);
        Item item1 = new Item();
        item1.Id = "account-info";
        item1.FILE_URL = "file:///android_asset/www/accountinfo.html";
        items.add(item1);

        mDrawerList.setAdapter(new Adapter(this,
                R.layout.left_drawer_item, items));
    }

    @Override
    public void startActivityForResult(CordovaPlugin cordovaPlugin, Intent intent, int requestCode) {
        this.activityResultCallback = cordovaPlugin;
        this.activityResultKeepRunning = this.keepRunning;


// If multitasking turned on, then disable it for activities that return results
        if (cordovaPlugin != null) {
            this.keepRunning = false;
        }


// Start activity
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {
        this.activityResultCallback = cordovaPlugin;
    }

    @Override
/**
 * Called when an activity you launched exits, giving you the requestCode you started it with,
 * the resultCode it returned, and any additional data from it.
 *
 * @param requestCode       The request code originally supplied to startActivityForResult(),
 *                          allowing you to identify who this result came from.
 * @param resultCode        The integer result code returned by the child activity through its setResult().
 * @param data              An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
 */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        CordovaPlugin callback = this.activityResultCallback;
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Object onMessage(String s, Object o) {
        return null;
    }

    @Override
    public ExecutorService getThreadPool() {
        return Executors.newSingleThreadExecutor();
    }
}
