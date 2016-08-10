package com.sugar.android.socketclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.sugar.localsocketclient.R;
import com.sugar.android.socket.model.Request;
import com.sugar.android.socket.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_save_user).setOnClickListener(this);
        findViewById(R.id.btn_delete_user).setOnClickListener(this);
        findViewById(R.id.btn_execute_event).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_user:
                Request reqSave = new Request();
                reqSave.setMethod("user/save");
                send(reqSave);
                break;
            case R.id.btn_delete_user:
                Request reqDele = new Request();
                reqDele.setMethod("user/delete");
                List<Object> params = new ArrayList<>();
                User user = new User();
                user.setName("Tom");
                params.add(user);
                reqDele.setParams(params);
                send(reqDele);
                break;
            case R.id.btn_execute_event:
                Request reqExec = new Request();
                reqExec.setMethod("event/execute");
                send(reqExec);
                break;
            default:
                break;
        }
    }

    private void send(final Request request) {
        new Thread() {
            @Override
            public void run() {
                boolean isConnected = SocketConnect.getInstance().connect();
                if (isConnected) {
                    Log.d(TAG, "Local Socket connected");
                    SocketConnect.getInstance().send(request);
//                    SocketConnect.getInstance().receive();
//                    SocketConnect.getInstance().disconnect();
                } else {
                    Log.d(TAG, "Local Socket disconnected");
                }
            }
        }.start();
    }
}
