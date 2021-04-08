package me.starorbbb.iotbukkit.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SocketClient extends WebSocketClient {
    public SocketClient(URI serverUri) {
        super(URI.create(serverUri + "/websocket"));
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("handshake!");
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("连接中断，中断原因：" + arg1 + " (Code " + arg0 + "),重新连接中。" );
        this.close();
        this.connect();
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("连接发生错误！错误原因： " + arg0.getLocalizedMessage() + arg0.toString());
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("Received messages:  " + arg0);
        if(arg0.contains("{")) {
            JsonReader.readJson(arg0);
        }
    }
}
