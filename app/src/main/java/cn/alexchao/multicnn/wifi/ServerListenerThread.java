package cn.alexchao.multicnn.wifi;

import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private synchronized void replyMsg(String msg) {
        try {
            TransModel model = new TransModel();
            model.getMessages().add(msg);
            getOs().writeObject(model);
            getOs().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcastMsg(String msg) {
        try {
            for (int i = 0; i < mActivity.clients.size(); i++) {
                Socket client = mActivity.clients.get(i);
                ObjectOutputStream tmpOs = new ObjectOutputStream(client.getOutputStream());
                TransModel model = new TransModel();
                model.getMessages().add(msg);
                tmpOs.writeObject(model);
                tmpOs.flush();
                tmpOs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void handleMsg(final String msg) {
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

    private synchronized void handleRequest(TransModel req) {

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