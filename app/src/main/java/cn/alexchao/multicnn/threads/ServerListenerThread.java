package cn.alexchao.multicnn.threads;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cn.alexchao.multicnn.R;
import cn.alexchao.multicnn.activities.ServerActivity;

public class ServerListenerThread extends Thread {
    BufferedReader reader;
    PrintWriter writer;
    Socket client;
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
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
                msg = reader.readLine();
                handleMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    // send message to all clients
    public synchronized void sendMsg(String msg) {
        try {
            for (int i = 0; i < mActivity.clients.size(); i++) {
                Socket client = mActivity.clients.get(i);
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void handleMsg(final String msg) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView display = (TextView) mActivity.findViewById(R.id.server_display);
                String tmpMsg = display.getText().toString() + msg;
                display.setText(tmpMsg);
                sendMsg("Server: Hi");
            }
        });
    }
}