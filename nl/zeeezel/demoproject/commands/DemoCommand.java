package nl.zeeezel.demoproject.commands;

import nl.zeeezel.demoproject.DemoProject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class DemoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            openDemoInv(p);

        }else{
            sender.sendMessage(DemoProject.plugin.prefix + "Je bent geen speler!");
        }

        return false;
    }

    private void openDemoInv(Player p) {

        Inventory demoInv = Bukkit.createInventory(null, 9, ChatColor.RED + "Demo menu");

        createSkull(demoInv, 0, "ZeeEzel", "Developer");
        demoInv.setItem(2, createItem(Material.SPRUCE_SIGN, ChatColor.GREEN + "Nutteloos"));
        demoInv.setItem(4, createItem(Material.CAKE, ChatColor.GREEN + "Feestje"));
        demoInv.setItem(6, createItem(Material.DIAMOND, ChatColor.GRAY + "Toggle scoreboard"));
        demoInv.setItem(8, createItem(Material.DARK_OAK_DOOR, "Sluit menu"));

        p.openInventory(demoInv);

    }

    private void createSkull(Inventory demoInv, int i, String spelerSkull, String title) {

        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) playerSkull.getItemMeta();

        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer("ZeeEzel"));
        List<String> lores = new ArrayList<>();

        lores.add(ChatColor.GRAY + "Realname: " + ChatColor.RED + "Gideon");
        lores.add(ChatColor.GRAY + "Leeftijd: " + ChatColor.RED + "20");
        lores.add(ChatColor.GRAY + "Hobby's: " + ChatColor.RED + "Dit soort dingen maken..");
        skullMeta.setLore(lores);
        skullMeta.setDisplayName(ChatColor.RED + "Developer van dienst");

        playerSkull.setItemMeta(skullMeta);

        demoInv.setItem(i, playerSkull);
    }

    private ItemStack createItem(Material m, String title) {

        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(title);

        is.setItemMeta(im);
        return is;
    }
}
