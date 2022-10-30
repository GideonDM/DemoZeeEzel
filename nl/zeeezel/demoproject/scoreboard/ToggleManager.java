package nl.zeeezel.demoproject.scoreboard;

import net.minecraft.server.v1_16_R1.Schedule;
import nl.zeeezel.demoproject.DemoProject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.List;

public class ToggleManager {





    private Player p;
    int scoreboardtime;

    public ToggleManager(Player p){
        this.p = p;

    }

    public void setToggle(Player p){
        DemoProject.list.add(p);
    }


    public boolean hasToggled(Player p){
        if(DemoProject.list.contains(p)){
            return true;

        }else{
            return false;
        }
    }

    public void stopScoreboard(){
        DemoProject.list.remove(p);
        Bukkit.getServer().getScheduler().cancelTask(scoreboardtime);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(DemoProject.plugin, new Runnable() {
            public void run() {
                ToggleManager.this.p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
        }, 20L);


    }

    public void toggleScoreboard(){
        if(!hasToggled(p)){
        ScoreboardManagement.createScoreboard(p);
        setToggle(p);
            p.sendMessage(DemoProject.plugin.prefix + "Scoreboard geactiveerd!");
        scoreboardtime = Bukkit.getScheduler().scheduleSyncRepeatingTask(DemoProject.plugin, new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                if(hasToggled(p)) {
                    if (this.count == 6)
                        this.count = 0;

                    switch (count) {


                        case 0:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&7D&cEMO"));
                            break;
                        case 1:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&cD&7E&cMO"));
                            break;
                        case 2:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&cDE&7M&cO"));
                            break;
                        case 3:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&cDEM&7O"));
                            break;
                        case 4:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&7DEMO"));
                            break;
                        case 5:
                            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(cc("&cDEMO"));
                            ScoreboardManagement.createScoreboard(p);
                            break;
                    }
                    count++;


                }else{
                    stopScoreboard();
                }

            }
        }, 0, 10);
    }else {
        stopScoreboard();
            p.sendMessage(DemoProject.plugin.prefix + "Scoreboard gedeactiveerd!");
        }

        }

    private String cc(String translate){
        return ChatColor.translateAlternateColorCodes('&', translate);
    }
}
