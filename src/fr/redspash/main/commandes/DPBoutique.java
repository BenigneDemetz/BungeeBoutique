package fr.redspash.main.commandes;

import fr.redspash.main.Main;
import fr.redspash.main.utils.sendToBukkit;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class DPBoutique extends Command {
    
    sendToBukkit utils;
    
    public DPBoutique() {
        super ("DPAutoGrade");
    }
    
    public String[] grades = {"SWAG", "VIP+", "VIP", "VIP++"};
    String message;
    
    // Commande : /DPAutoGrade <joueur> <grade> <nbr de mois>
    @Override
    public void execute(CommandSender Sender, String[] args) {
        if (Sender instanceof ConsoleCommandSender) {
            String Grade = "§4§lERREUR";
            String mois = "x";
            String joueur = "§4§lERREUR";
            joueur = args[0];
            Grade = args[1];
    
            if (Grade == "§4§lERREUR" || joueur == "§4§lERREUR") {
                BungeeCord.getInstance ().getConsole ().sendMessage ("§4§lUne erreur est survenue ! ");
                return;
            }
            Iterator var10 = ProxyServer.getInstance ().getPlayers ().iterator ();
            ServerInfo serverInfo = BungeeCord.getInstance ().getServerInfo ("pvpsoup");
            ProxiedPlayer player = ProxyServer.getInstance ().getPlayer (args[0]);
            if (mois == "x") {
                message = "§f\n§f§l[§4§lD§1§lP§f§l-§d§lBOUTIQUE§f§l] §c§l" + joueur + " §b§lvient §b§ld'acheter §b§lle §b§lgrade §c§l" + Grade + " !!\n§f";
                sendToBukkit ("command", "perm player " + args[0] + " addgroup -a " + Grade, serverInfo);
                sendToBukkit ("command", "playsound mob.enderdragon.end @a 0 0 0 1000", serverInfo);
                if (player.isConnected ()) {
                    String barre = "§7§l----------------------------------------------";
                    String kickmessage = "§d§l§nBOUTIQUE\n" + barre + "§f\n§f\n§d§lMerci d'avoir acheté un grade sur §4§lDEAD§1§lPVP §d§l! \n§f\n" + "§b§lVotre pseudo: §c§l" + args[0] + "§b§l\nGrade acheté: §c§l" + Grade + "\n§f\n§6§lMerci et bon jeu sur §4§lDEAD§1§lPVP !\n§f" + "§7§lEn cas de problème merci de screen cet écran avec F2" + "\n§f" + barre;
                    player.disconnect (new TextComponent (kickmessage));
                }
    
    
            } else {
                message = "§f\n§f§l[§4§lD§1§lP§f§l-§d§lBOUTIQUE§f§l] §c§l" + joueur + " §b§lvient §b§ld'acheter §b§lle §b§lgrade §c§l" + Grade + "§b§l pendant §c§l" + mois + " mois !" + "\n§f";
                int jour = 31;
                int nbrdemois = Integer.parseInt (mois);
                jour = 31 * nbrdemois;
                sendToBukkit ("command", "perm player " + args[0] + " addgroup -a " + Grade + " " + jour, serverInfo);
                sendToBukkit ("command", "playsound mob.enderdragon.end @a 0 0 0 1000", serverInfo);
                if (player.isConnected ()) {
                    String barre = "§7§l----------------------------------------------";
                    String kickmessage = "§d§l§nBOUTIQUE\n" + barre + "§f\n§f\n§d§lMerci d'avoir acheté un grade sur §4§lDEAD§1§lPVP §d§l! \n§f\n" + "§b§lVotre pseudo: §c§l" + args[0] + "§b§l\nGrade acheté: §c§l" + Grade + "\n§b§lDurée: §c§l" + jour + " jours" + "\n§f\n§6§lMerci et bon jeu sur §4§lDEAD§1§lPVP !\n§f" + "§7§lEn cas de problème merci de screen cet écran avec F2" + "\n§f" + barre;
                    player.disconnect (new TextComponent (kickmessage));
                }
            }
        }
    }
    
    public void sendToBukkit(String channel, String message, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(channel);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Note the "Return". It is the channel name that we registered in our Main class of Bungee plugin.
        server.sendData("Return", stream.toByteArray());
        
    }
}

