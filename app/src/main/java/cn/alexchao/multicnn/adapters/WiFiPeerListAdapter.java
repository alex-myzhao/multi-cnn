package anuj.wifidirect.adapters;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.alexchao.multicnn.R;

import static cn.alexchao.multicnn.utils.Util.getDeviceStatus;

public class WiFiPeerListAdapter extends BaseAdapter {
    private Activity mActivity;
    private int mResourceId;
    private List<WifiP2pDevice> items;

    public WiFiPeerListAdapter(Activity activity, int resourceId, List<WifiP2pDevice> devices) {
        this.mActivity = activity;
        this.mResourceId = resourceId;
        this.items = devices;
    }

    @Override
    public int getCount() {
        if (this.items == null) return 0;
        return this.items.size();
    }

    @Override
    public Object getItem(int i) {
        if (this.items == null || this.items.size() <= i) {
            return null;
        }
        return this.items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(mActivity).inflate(mResourceId, null);
        ((TextView) inflate.findViewById(R.id.device_name)).setText(items.get(position).deviceName);
        WifiP2pDevice device = items.get(position);
        if (device != null) {
            TextView top = (TextView) inflate.findViewById(R.id.device_name);
            TextView bottom = (TextView) inflate.findViewById(R.id.device_details);
            if (top != null) {
                top.setText(device.deviceName);
            }
            if (bottom != null) {
                bottom.setText(getDeviceStatus(device.status));
            }
        }
        return inflate;
    }
}
