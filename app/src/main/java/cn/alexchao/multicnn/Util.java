package cn.alexchao.multicnn;

import android.content.Context;
import android.net.wifi.WifiManager;

public class Util {
    public static final int REQUEST_TAKE_PHOTO_CODE = 1;
    public static final int REQUEST_CHOP_PHOTO_CODE = 2;

    public static String getLocalIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int intIpAddress = wifiManager.getConnectionInfo().getIpAddress();
        return intToIp(intIpAddress);
    }

    private static String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
}
