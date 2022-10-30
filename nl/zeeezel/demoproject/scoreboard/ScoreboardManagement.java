package nl.zeeezel.demoproject.scoreboard;

import nl.zeeezel.demoproject.DemoProject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;


public class ScoreboardManagement {

    public static void createScoreboard(Player p){

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("DemoScoreboard", "dummy", cc("&cDEMO"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // &cDEMO
        // (10)
        // Speler: (9)
        // SPELERNAAM (8)
        // (7)
        // Operator: (6)
        // NEE/JA (5)

        Score blank = objective.getScore(" ");
        //blank.setScore(10);

        Score speler = objective.getScore(cc("&7Speler:"));
        speler.setScore(9);

        Score spelerName = objective.getScore(cc("&c " + p.getName()));
        spelerName.setScore(8);

        Score blank1 = objective.getScore("  ");
        blank1.setScore(7);

        Score isOp = objective.getScore(cc("&7Operator:"));
        isOp.setScore(6);

        Score operator = objective.getScore(cc("&c " + isOp(p)));
        operator.setScore(5);

        Score blank2 = objective.getScore("   ");
        blank2.setScore(4);

        p.setScoreboard(scoreboard);



    }

    private static String cc(String translate){
        return ChatColor.translateAlternateColorCodes('&', translate);
    }

    private static String isOp(Player p){
        if(p.isOp()) {
            return "Ja";

        }else {
            return "Nee";
        }
    }
}
