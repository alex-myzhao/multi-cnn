package cn.alexchao.multicnn.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.alexchao.multicnn.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_server:
                gotoServer();
                break;
            case R.id.to_cifar:
                gotoCifar();
                break;
            default:
                Toast.makeText(this, "no match", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        Button serverBtn = (Button) findViewById(R.id.to_server);
        Button cifarBtn = (Button) findViewById(R.id.to_cifar);
        serverBtn.setOnClickListener(this);
        cifarBtn.setOnClickListener(this);
    }

    private void gotoServer() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ServerActivity.class);
        startActivity(intent);
    }

    private void gotoCifar() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CifarActivity.class);
        startActivity(intent);
    }
}
