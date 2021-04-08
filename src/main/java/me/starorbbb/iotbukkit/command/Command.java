package me.starorbbb.iotbukkit.command;

import me.starorbbb.iotbukkit.Iotbukkit;
import me.starorbbb.iotbukkit.json.RequestObject;
import me.starorbbb.iotbukkit.websocket.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    private final Iotbukkit plugin;

    public Command(Iotbukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(cmd.getName().equals("iot")) {
            if(sender.hasPermission("biot.admin")) {
                switch (args[0]) {
                    case "connect": {
                        if(!Iotbukkit.socketClient.isConnecting() || Iotbukkit.socketClient.isClosed()) {
                            Iotbukkit.socketClient.connect();
                        } else {
                            Bukkit.getLogger().warning("WebSocket连接中。");
                        }
                        return true;
                    }
                    case "token": {
                        sender.sendMessage("正在发送TOKEN！");
                        Iotbukkit.socketClient.send(args[1]);
                        return true;
                    }
                    case "send": {
                        StringBuilder arg = new StringBuilder();
                        for(int i = 2; i < args.length; i++) {
                            arg.append(args[i]);
                            if(i != args.length - 1) {
                                arg.append(" ");
                            }
                        }
                        sender.sendMessage("正在发送数据！！数据内容： target: " + args[1] + " msg: " + arg.toString());
                        RequestObject requestObject = new RequestObject(args[1], arg.toString());
                        String json = JsonReader.gson.toJson(requestObject);
                        Iotbukkit.socketClient.send(json);
                        break;
                    }
                    case "reload": {
                        plugin.reloadConfig();
                        Iotbukkit.URL = plugin.getConfig().getString("server-url");
                        Bukkit.getLogger().info("重新加载配置文件。");
                        return true;
                    }
                    default: {
                        sender.sendMessage(ChatColor.RED + "Invalid command!");
                        return true;
                    }
                }
            } else {
                return true;
            }
        }
        return true;
    }
}
