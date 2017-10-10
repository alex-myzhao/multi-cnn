package cn.alexchao.multicnn.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.net.Socket;
import java.util.ArrayList;

import cn.alexchao.multicnn.R;
import cn.alexchao.multicnn.Util;
import cn.alexchao.multicnn.wifi.ServerThread;

public class ServerActivity extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<Socket> clients;
    private ServerThread server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        clients = new ArrayList<>();
        ((TextView) findViewById(R.id.server_ip)).setText(Util.getLocalIp(this));
        findViewById(R.id.start_server_btn).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        if (server != null) {
            server.closeServer();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_server_btn) {
            if (server == null) {
                server = new ServerThread(this);
            }
        }
    }
}
