package nl.zeeezel.demoproject.listeners;

import com.mojang.serialization.MapEncoder;
import nl.zeeezel.demoproject.DemoProject;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerjaardagEvent {

    Player jarige;
    Location feestLocatie;
    HashMap<Block, Player> map = new HashMap();


    public VerjaardagEvent(Player jarige) {

        this.jarige = jarige;
        this.feestLocatie = jarige.getLocation();

    }

    public Location getLocation(){
        return this.feestLocatie;
    }

    public String getJarige(){

        return this.jarige.getName();
    }

    public void createFeestje() {

        this.jarige.playSound(jarige.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        jarige.sendTitle(ChatColor.RED + "FEEEESSSTTTTT", ChatColor.GRAY + "Welkom bij de DEMO!");
        maakFeestje(this.getLocation(), 4, Material.LEGACY_CAKE_BLOCK);

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(DemoProject.plugin, new Runnable() {
            @Override
            public void run() {
                deleteFeestje();

            }
        }, 5*20);



    }

    public void maakFeestje(Location loc, Integer r, Material m) {
        int x;
        int y = loc.getBlockY();
        int z;
        World w = jarige.getWorld();

        for (double i = 0.0; i < 360.0; i += 0.1) {
            double angle = i * Math.PI / 180;
            x = (int) (loc.getX() + r * Math.cos(angle));
            z = (int) (loc.getZ() + r * Math.sin(angle));

            Block spawnLoc = Bukkit.getServer().getWorld("world").getBlockAt(x, y, z);

            if (spawnLoc.getType() == Material.AIR) {
                Location mapLoc = new Location(w, x, y, z);
                spawnLoc.setType(m);
                map.put(spawnLoc, jarige);

            }

        }
        jarige.sendMessage(DemoProject.plugin.prefix + "Speciaal voor jou hebben wij alle taarten in de weide omgeving ingekocht!");
    }

    public void deleteFeestje(){

        for(Block key : map.keySet()){

            key.setType(Material.AIR);
            jarige.spawnParticle(Particle.CLOUD, key.getLocation(), 10);
            jarige.playSound(key.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            map.remove(jarige, key);

        }
        jarige.sendMessage(DemoProject.plugin.prefix + "Het feestje is helaas weer afgelopen :(");

    }


}
