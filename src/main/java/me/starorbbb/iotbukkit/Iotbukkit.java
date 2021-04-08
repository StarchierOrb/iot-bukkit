package me.starorbbb.iotbukkit;

import me.starorbbb.iotbukkit.Listeners.PlayerInteract;
import me.starorbbb.iotbukkit.command.Command;
import me.starorbbb.iotbukkit.websocket.SocketClient;
import me.starorbbb.iotbukkit.websocket.SocketThread;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.URISyntaxException;

public final class Iotbukkit extends JavaPlugin {
    public static SocketClient socketClient;
    public static String URL;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("加载配置文件...");
        URL = getConfig().getString("server-url");
        getLogger().info("注册监听器...");
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getLogger().info("初始化WebSocket...");
        try {
            socketClient = new SocketClient(new URI(URL));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socketClient.connect();
        SocketThread socketThread = new SocketThread();
        //TODO: Socket should be run on async thread.
        socketThread.start();
        Bukkit.getLogger().info("正在连接至服务器，请稍等...");
        getLogger().info("注册指令...");
        getCommand("iot").setExecutor(new Command(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
