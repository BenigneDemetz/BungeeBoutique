package fr.redspash.main;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.redspash.main.commandes.DPBoutique;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;

public class Main extends Plugin {
    @Override
    public void onEnable(){
        instance = this;
        getProxy().getConsole().sendMessage("§2§l[DP BOUTIQUE] >>> Activation de la boutique !");
        ProxyServer.getInstance().getPluginManager().registerCommand(this,new DPBoutique());
        BungeeCord.getInstance().registerChannel("Return");
    }

    @Override
    public void onDisable(){

        getProxy().getConsole().sendMessage("§c§l[DP BOUTIQUE] >>> Arrêt du plugin ...");
    }



    public static void updateGrade(String player, String grade, int date) {

        File playerFile = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/" + grade + "/" + player + ".yml");

        try {
            if (!playerFile.exists()) playerFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(playerFile.getAbsolutePath()));
            String line = reader.readLine();
            PrintWriter out = new PrintWriter(playerFile);
            if (line == null || line.isEmpty() || line == "") out.print(date);
            else {
                int jours = Integer.valueOf(line);
                out.print(jours + date);
            }

            out.close();
        } catch (IOException e1) {
            BungeeCord.getInstance().getConsole().sendMessage(ChatColor.RED + "Upgrade de grade failed, " + grade + ", " + player);
            e1.printStackTrace();
        }

    }

    public static boolean hasGrade(String player, String grade) {
        File playerFile = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/" + grade + "/" + player + ".yml");
        if (playerFile.exists()) return true;
        return false;
    }

    public static void reduceFromOneDayAllSubs() {
        try {
            File directoryPath = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/");
            //List of all files and directories
            String contents[] = directoryPath.list();
            if (contents == null) return;
            if (contents.length == 0) return;

            for (String grade : contents) {
                File grades = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/" + grade + "/");
                String gradeslist[] = grades.list();
                for (String players : gradeslist) {
                    File player = new File("/home/ubuntu/server/Bungee_server/plugins/DeadPvp/players/"+grade+"/" + players);
                    BufferedReader reader = new BufferedReader(new FileReader(player));
                    String line = reader.readLine();

                    while (line != null)
                    {
                        try {
                            int jours = Integer.parseInt(line);
                            if (jours == 1){
                                player.delete();
                                return;
                            }
                            PrintWriter writer = new PrintWriter(new File (player.getAbsolutePath()));
                            writer.print(jours-1);
                            writer.close();
                        }
                        catch (Exception e){ System.out.println(e);}
                        line = reader.readLine();
                    }
                    reader.close();
                }
            }
        }
        catch (Exception ee) {
            System.out.println(ee);
        }

    }



    private static Main instance;

    public static Main getInstance(){
        return instance;
    }
}
