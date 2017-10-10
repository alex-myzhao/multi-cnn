package cn.alexchao.multicnn.wifi;

import android.app.Activity;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.alexchao.multicnn.R;
import cn.alexchao.multicnn.StaticConfig;
import cn.alexchao.multicnn.bean.TransModel;

public class ClientThread extends Thread {
    private Activity mActivity;
    private Socket client;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private String serverIP;

    public ClientThread(Activity activity, String serverIP) {
        this.mActivity = activity;
        this.serverIP = serverIP;
        this.start();
    }

    public void run() {
        try {
            client = new Socket(serverIP, StaticConfig.serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg;
        while (true) {
            try {
                TransModel tm = (TransModel) getIs().readObject();
                msg = tm.getMessages().get(0);
                handleMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public synchronized void sendMsg(String msg) {
        try {
            TransModel tm = new TransModel();
            tm.getMessages().add(msg);
            getOs().writeObject(tm);
            getOs().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMsg(final String msg) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView display = (TextView) mActivity.findViewById(R.id.client_display);
                String tmpMsg = display.getText().toString() + "\n" + msg;
                display.setText(tmpMsg);
            }
        });
    }

    private ObjectInputStream getIs() {
        if (this.client == null) return null;
        if (this.is == null) {
            try {
                is = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.is;
    }

    private ObjectOutputStream getOs() {
        if (this.client == null) return null;
        if (this.os == null) {
            try {
                os = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.os;
    }
}
