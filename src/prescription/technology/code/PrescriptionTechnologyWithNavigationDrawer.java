package prescription.technology.code;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.widget.ListView;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import prescription.technology.R;
import prescription.technology.code.navigation.drawer.Adapter;
import prescription.technology.code.navigation.drawer.CustomCordovaWebView;
import prescription.technology.code.navigation.drawer.DrawerToggle;
import prescription.technology.code.navigation.drawer.Item;
import prescription.technology.code.receivers.CartBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by novac on 24-Jul-14.
 */
public class PrescriptionTechnologyWithNavigationDrawer extends Activity implements CordovaInterface {
    public static CustomCordovaWebView __cart;
    public final String TAG = "PRESCRIPTION TECHNOLOGY";
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    protected CordovaWebView appView;
    boolean activityResultKeepRunning;
    boolean keepRunning;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CordovaPlugin activityResultCallback;
    private ActionBarDrawerToggle mDrawerToggle;
    private CartBroadcastReceiver cartBroadcastReceiver = new CartBroadcastReceiver();

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appView = (CordovaWebView) findViewById(R.id.cordova_main_webview);
        Config.init(this);
        appView.getSettings().setJavaScriptEnabled(true);
        appView.addJavascriptInterface(this, "prescription");
        appView.addJavascriptInterface(new Constants(), "constants");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new DrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        List<Item> items = new ArrayList<Item>();

        Item item = new Item();
        item.Id = "shopping-cart";
        item.CONTENT = "file:///android_asset/www/shoppingcart.html";
        items.add(item);
        /*
        Item item1 = new Item();
        item1.Id = "account-info";
        item1.CONTENT = "file:///android_asset/www/accountinfo.html";
        items.add(item1);
        */
        mDrawerList.setAdapter(new Adapter(this,
                R.layout.left_drawer_item, items));
        registerReceiver(cartBroadcastReceiver, new IntentFilter("CART"));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cartBroadcastReceiver);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivityForResult(CordovaPlugin cordovaPlugin, Intent intent, int requestCode) {
        this.activityResultCallback = cordovaPlugin;
        this.activityResultKeepRunning = this.keepRunning;
        if (cordovaPlugin != null) {
            this.keepRunning = false;
        }
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {
        this.activityResultCallback = cordovaPlugin;
    }

    @Override
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
        if (s.equalsIgnoreCase("exit")) {
            super.finish();
        }
        return null;
    }

    @Override
    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void onDestroy() {
        if (this.appView != null) {
            this.appView.handleDestroy();
        }
        super.onDestroy();
    }

    @JavascriptInterface
    public void sendMessage(final String message) {
        Intent i = new Intent("CART");
        Log.v(TAG, "send Broadcast");
        sendBroadcast(i);
    }
}
