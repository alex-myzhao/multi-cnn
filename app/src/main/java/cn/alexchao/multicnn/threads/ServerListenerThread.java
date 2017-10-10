package cn.alexchao.multicnn.threads;

import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cn.alexchao.multicnn.R;
import cn.alexchao.multicnn.activities.ServerActivity;
import cn.alexchao.multicnn.bean.TransModel;

public class ServerListenerThread extends Thread {
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Socket client;
    private ServerActivity mActivity;

    public ServerListenerThread(Socket client, ServerActivity activity) {
        this.client = client;
        mActivity = activity;
        this.start();
    }

    public void run() {
        String msg;
        while (true) {
            try {
                TransModel tm = (TransModel) getIs().readObject();
                msg = tm.getMessages().get(0);
                handleMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                break;
            }
        }
    }

    // send message back to the client
    public synchronized void replyMsg(String msg) {
        try {
            TransModel model = new TransModel();
            model.getMessages().add(msg);
            getOs().writeObject(model);
            getOs().flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleMsg(final String msg) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView display = (TextView) mActivity.findViewById(R.id.server_display);
                String tmpMsg = display.getText().toString() + "\n" + msg;
                display.setText(tmpMsg);
                replyMsg("Server: Hi" + client.getInetAddress());
            }
        });
    }

    public ObjectInputStream getIs() {
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

    public ObjectOutputStream getOs() {
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