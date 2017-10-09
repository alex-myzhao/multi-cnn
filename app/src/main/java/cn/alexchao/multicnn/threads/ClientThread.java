package cn.alexchao.multicnn.threads;

import android.app.Activity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cn.alexchao.multicnn.R;
import cn.alexchao.multicnn.StaticConfig;

public class ClientThread extends Thread {
    private Activity mActivity;
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private String serverIP;

    public ClientThread(Activity activity, String serverIP) {
        this.mActivity = activity;
        this.serverIP = serverIP;
        this.start();
    }

    public void run() {
        try {
            client = new Socket(serverIP, StaticConfig.serverPort);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg;
        while (true) {
            try {
                msg = reader.readLine();
                handleMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            writer.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMsg(final String msg) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView display = (TextView) mActivity.findViewById(R.id.client_display);
                String tmpMsg = display.getText().toString() + msg;
                display.setText(tmpMsg);
            }
        });
    }
}
