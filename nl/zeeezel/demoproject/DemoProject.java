package nl.zeeezel.demoproject;

import nl.zeeezel.demoproject.commands.DemoCommand;
import nl.zeeezel.demoproject.listeners.ChooseDemoEvent;
import nl.zeeezel.demoproject.listeners.JoinQuitEvent;
import nl.zeeezel.demoproject.listeners.VerjaardagEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DemoProject extends JavaPlugin {

    public static DemoProject plugin;
    public static List<Player> list = new ArrayList<Player>();
    public String prefix = ChatColor.BOLD + "" + ChatColor.RED + "DEMO " + ChatColor.GRAY;

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChooseDemoEvent(), this);
        pm.registerEvents(new JoinQuitEvent(), this);
        plugin = this;

        getCommand("zeeezel").setExecutor(new DemoCommand());
    }

    @Override
    public void onDisable() {

    }
}
