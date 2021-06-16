package fr.redspash.main;

import fr.redspash.main.commandes.DPBoutique;
import fr.redspash.main.commandes.KitUnlockerCmd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;

public class Main extends Plugin {
    public static HashMap<ProxiedPlayer, Integer> caca = new HashMap<> ();
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
    
    public static Main getInstance() {
        return instance;
    }
}
