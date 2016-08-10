package com.sugar.android.socketclient;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sugar.android.socket.model.Request;
import com.sugar.android.socket.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName: SocketConnect
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 上午11:16
 */
public class SocketConnect {

    private static final String TAG = SocketConnect.class.getSimpleName();

    private static final String SOCKET_ADDRESS = "com.mwee.localsocket";

    //    private LocalSocket mClient;
    private Socket mClient;

    private int timeout = 1000 * 30;

    private InputStream mIn;
    private OutputStream mOut;

    private static SocketConnect _instance = new SocketConnect();

    public static SocketConnect getInstance() {
        return _instance;
    }

    private SocketConnect() {
    }

    /**
     * 连接server
     *
     * @return
     */
    public boolean connect() {

        if (mClient != null && mClient.isConnected()) {
            return true;
        }

        try {
//            mClient = new LocalSocket();
//            mClient.connect(new LocalSocketAddress(SOCKET_ADDRESS));
            mClient = new Socket("10.10.200.78", 10010);
            mClient.setSoTimeout(timeout);

//            mOut = mClient.getOutputStream();
//            mIn = mClient.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
            return false;
        }
        return true;
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        try {
            if (mClient != null) {
                mClient.close();
                mClient = null;
            }
            if (mIn != null) {
                mIn.close();
                mIn = null;
            }
            if (mOut != null) {
                mOut.close();
                mOut = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    public boolean send(String msg) {
        try {
            msg += "\n";
            mOut.write(msg.getBytes());
            mOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public boolean send(Request request) {
        try {
            if (ois == null) {
                ois = new ObjectInputStream(mClient.getInputStream());
            }
            if (oos == null) {
                oos = new ObjectOutputStream(mClient.getOutputStream());
            }

            oos.writeObject(request);
            oos.writeObject(request);
//            oos.flush();
//            oos.writeObject(null);

//            Response res = null;
//            try {
//                res = (Response) ois.readObject();
//                Log.d(TAG, "Current Response is:\n" + JSON.toJSONString(res));
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            oos.close();
//            ois.close();
//            mClient.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 接收消息
     *
     * @return
     */
//    public String receive() {
//        String result = null;
//
//        try {
//            byte[] buffer = new byte[2048];
//            int readBytes = 0;
//            StringBuilder stringBuilder = new StringBuilder();
//            while ((readBytes = mIn.read(buffer)) > 0) {
//                stringBuilder.append(new String(buffer, 0, readBytes));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public Response receive() {
        Response res = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(mIn);
            res = (Response) ois.readObject();
            Log.d(TAG, "Current Response is:\n" + JSON.toJSONString(res));
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
