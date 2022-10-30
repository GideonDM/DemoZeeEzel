package nl.zeeezel.demoproject.listeners;

import javafx.scene.control.Toggle;
import nl.zeeezel.demoproject.DemoProject;
import nl.zeeezel.demoproject.scoreboard.ScoreboardManagement;
import nl.zeeezel.demoproject.scoreboard.ToggleManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ChooseDemoEvent implements Listener {

    @EventHandler
    public void choose(InventoryClickEvent e){

        Inventory inv = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().contains(ChatColor.RED + "Demo menu")){

            switch(e.getSlot()){
                case 8:
                    p.closeInventory();
                    p.sendMessage(DemoProject.plugin.prefix + "Demo is afgesloten...");
                    break;

                case 6:

                    ToggleManager toggleManager = new ToggleManager(p);
                    toggleManager.toggleScoreboard();


                    break;

                case 4:
                    p.closeInventory();
                   VerjaardagEvent jarige = new VerjaardagEvent(p);
                    jarige.createFeestje();
                    break;

                case 2:
                    p.closeInventory();
                    p.sendMessage(DemoProject.plugin.prefix + "Ik zei toch dat het nutteloos was...");

                    break;
            }


            e.setCancelled(true);
        }


    }
}
