package me.starorbbb.iotbukkit.websocket;

import com.google.gson.Gson;
import me.starorbbb.iotbukkit.json.MessageObject;
import org.bukkit.Bukkit;

public class JsonReader {
    public static Gson gson = new Gson();
    public static void readJson(String jsonText) {
        MessageObject messageObject = gson.fromJson(jsonText, MessageObject.class);
        Bukkit.getLogger().info("Received Json text from server:");
        Bukkit.getLogger().info("type: " + messageObject.getType());
        Bukkit.getLogger().info("data: " + messageObject.getData());
        if("success".equalsIgnoreCase(messageObject.getData())) {
            Bukkit.getLogger().info("数据发送成功。");
        }
    }
}
