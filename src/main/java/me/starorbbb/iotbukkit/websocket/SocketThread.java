package me.starorbbb.iotbukkit.websocket;

import me.starorbbb.iotbukkit.Iotbukkit;
import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;

public class SocketThread extends Thread {
    @Override
    public void run() {
        if(!Iotbukkit.socketClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            Bukkit.getLogger().info("服务器连接中，请稍等..");
        } else if(Iotbukkit.socketClient.getReadyState().equals(WebSocket.READYSTATE.CLOSED)) {
            Bukkit.getLogger().info("服务器连接中断。正在尝试重新连接..");
            Iotbukkit.socketClient.reconnect();
        } else if(Iotbukkit.socketClient.getReadyState().equals(WebSocket.READYSTATE.CONNECTING)){
            Bukkit.getLogger().info("服务器已经连接。");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.interrupt();
    }
}
