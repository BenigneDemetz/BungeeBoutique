package fr.redspash.main;

import fr.redspash.main.commandes.DPBoutique;
import fr.redspash.main.commandes.KitUnlockerCmd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;

public class Main extends Plugin {
    
    private static Main instance;
    
    @Override
    public void onEnable(){
        instance = this;
        getProxy().getConsole().sendMessage("§2§l[DP BOUTIQUE] >>> Activation de la boutique !");
        ProxyServer.getInstance().getPluginManager().registerCommand(this,new DPBoutique());
        ProxyServer.getInstance().getPluginManager().registerCommand(this,new KitUnlockerCmd ());
        BungeeCord.getInstance().registerChannel("Return");
    }
    
    @Override
    public void onDisable(){
        getProxy().getConsole().sendMessage("§c§l[DP BOUTIQUE] >>> Arrêt du plugin ...");
    }
    
    public static boolean hasGrade(String player, String grade) {
        File playerFile = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/" + grade + "/" + player + ".yml");
        if (playerFile.exists()) return true;
        return false;
    }
    
    public static Main getInstance(){
        return instance;
    }
}
