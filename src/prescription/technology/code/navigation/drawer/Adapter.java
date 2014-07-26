package prescription.technology.code.navigation.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import org.apache.cordova.CordovaWebView;
import prescription.technology.R;

import java.util.List;

/**
 * Created by novac on 26-Jul-14.
 */
public class Adapter extends ArrayAdapter<Item> {

    public Adapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public Adapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.left_drawer_item, null);
        }

        Item p = getItem(position);

        if (p != null) {

            CordovaWebView cordovaWebView = (CordovaWebView) v.findViewById(R.id.cordova_left_item_webview);
            if (cordovaWebView != null) {
                cordovaWebView.loadUrl(p.FILE_URL);
            }
        }
        return v;

    }
}
