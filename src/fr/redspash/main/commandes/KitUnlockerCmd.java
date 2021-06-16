package fr.redspash.main.commandes;

import fr.redspash.main.utils.sendToBukkit;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;


public class KitUnlockerCmd extends Command {
    
    sendToBukkit utils;
    
    public KitUnlockerCmd() {
        super ("DPKitUnlocker", "deadpvp.bungee.kit", "dpkit");
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        if(!(sender instanceof ConsoleCommandSender)) return;
        String amount = args[1];
        ServerInfo serverInfo = BungeeCord.getInstance().getServerInfo("pvpsoup");
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
        utils.sendToBukkit ("command", "kb KitUnlocker give " + player + " " + amount, serverInfo);
        
        for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) all.
                sendMessage(new TextComponent("§f\\n§f§l[§4§lD§1§lP§f§l-§d§lBOUTIQUE§f§l] §c§l\\" + player + "§b§lvien d'acheter §c§l" + amount + " §b§lKitUnlocker !"));
    }
}
