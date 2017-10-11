package cn.alexchao.multicnn.wifi;

import android.widget.Toast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.alexchao.multicnn.StaticConfig;
import cn.alexchao.multicnn.activities.ServerActivity;

public class ServerThread extends Thread {
    private ServerSocket ss;
    private ServerActivity mActivity;

    public ServerThread(ServerActivity activity) {
        mActivity = activity;
        this.start();
    }

    public void run() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity, "Start Listening", Toast.LENGTH_SHORT).show();
            }
        });
        try {
            ss = new ServerSocket(StaticConfig.SERVER_PORT);
            while (true) {
                Socket client = ss.accept();
                mActivity.clientsMap.put(client.getInetAddress() + "", mActivity.clients.size());
                mActivity.clients.add(client);
                new ServerListenerThread(client, mActivity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeServer() {
        try {
            if (ss != null)
                ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
