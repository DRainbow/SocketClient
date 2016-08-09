package com.sugar.android.socketclient;

import com.sugar.android.socket.model.Request;

import java.io.IOException;
import java.io.InputStream;
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

        if (mClient != null) {
            return true;
        }

        try {
//            mClient = new LocalSocket();
//            mClient.connect(new LocalSocketAddress(SOCKET_ADDRESS));
            mClient = new Socket("10.10.200.78", 10010);
            mClient.setSoTimeout(timeout);

            mIn = mClient.getInputStream();
            mOut = mClient.getOutputStream();
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

    public boolean send(Request request) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mOut);
            oos.writeObject(request);
            oos.writeObject(null);
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
    public String receive() {
        String result = null;

        try {
            byte[] buffer = new byte[2048];
            int readBytes = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while ((readBytes = mIn.read(buffer)) > 0) {
                stringBuilder.append(new String(buffer, 0, readBytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
