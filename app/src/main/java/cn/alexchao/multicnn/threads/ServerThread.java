package cn.alexchao.multicnn.threads;

import android.app.Activity;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import cn.alexchao.multicnn.StaticConfig;
import cn.alexchao.multicnn.activities.ServerActivity;
import cn.alexchao.multicnn.bean.TransModel;

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
            ss = new ServerSocket(StaticConfig.serverPort);
            while (true) {
                Socket client = ss.accept();
                mActivity.clients.add(client);
                new ServerListenerThread(client, mActivity);
            }
        } catch (IOException e) {
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

    public void closeServer() {
        try {
            if (ss != null)
                ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
