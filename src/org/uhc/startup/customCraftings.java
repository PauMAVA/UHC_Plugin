package org.uhc.startup;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class customCraftings implements Listener{
	
	uhcCore directPlugin;
	public customCraftings(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
		
	}
	public void recipes() {
		addGoldenApple();
		removeOriginalGoldenMelon();
		addGoldenMelon();
	}
	
	
	public void addGoldenApple() {
		ItemStack golden_apple = new ItemStack(Material.GOLDEN_APPLE, 1);
		NamespacedKey name = new NamespacedKey(uhcCore.getInstance(), "golden_apple");
		ShapedRecipe goldenApple = new ShapedRecipe(name, golden_apple);
		goldenApple.shape(" * ","*%*"," * ");	
		goldenApple.setIngredient('*', Material.GOLD_INGOT);
		goldenApple.setIngredient('%', Material.PLAYER_HEAD);
		Bukkit.getServer().addRecipe(goldenApple);
		return;
	}
	
	

	public void addGoldenMelon() {
		ItemStack golden_melon1 = new ItemStack(Material.GLISTERING_MELON_SLICE, 1);
		NamespacedKey name1 = new NamespacedKey(uhcCore.getInstance(), "golden_melon1");
		ShapedRecipe goldenMelon1 = new ShapedRecipe(name1, golden_melon1);
		goldenMelon1.shape("AAA","A%A","A-A");
		goldenMelon1.setIngredient('%', Material.MELON);
		goldenMelon1.setIngredient('-', Material.GOLD_BLOCK);
		
		ItemStack golden_melon2 = new ItemStack(Material.GLISTERING_MELON_SLICE, 1);
		NamespacedKey name2 = new NamespacedKey(uhcCore.getInstance(), "golden_melon2");
		ShapedRecipe goldenMelon2 = new ShapedRecipe(name2, golden_melon2);
		goldenMelon2.shape("*-*","*%*","***");
		goldenMelon2.setIngredient('*', Material.AIR);
		goldenMelon2.setIngredient('%', Material.MELON);
		goldenMelon2.setIngredient('-', Material.GOLD_BLOCK);
		
		ItemStack golden_melon3 = new ItemStack(Material.GLISTERING_MELON_SLICE, 1);
		NamespacedKey name3 = new NamespacedKey(uhcCore.getInstance(), "golden_melon3");
		ShapedRecipe goldenMelon3 = new ShapedRecipe(name3, golden_melon3);
		goldenMelon3.shape("***","-%*","***");
		goldenMelon3.setIngredient('*', Material.AIR);
		goldenMelon3.setIngredient('%', Material.MELON);
		goldenMelon3.setIngredient('-', Material.GOLD_BLOCK);
		
		ItemStack golden_melon4 = new ItemStack(Material.GLISTERING_MELON_SLICE, 1);
		NamespacedKey name = new NamespacedKey(uhcCore.getInstance(), "golden_melon4");
		ShapedRecipe goldenMelon4 = new ShapedRecipe(name, golden_melon4);
		goldenMelon4.shape("***","*%-","***");
		goldenMelon4.setIngredient('*', Material.AIR);
		goldenMelon4.setIngredient('%', Material.MELON);
		goldenMelon4.setIngredient('-', Material.GOLD_BLOCK);
		
		Bukkit.getServer().addRecipe(goldenMelon1);
		Bukkit.getServer().addRecipe(goldenMelon2);
		Bukkit.getServer().addRecipe(goldenMelon3);
		Bukkit.getServer().addRecipe(goldenMelon4);
		return;
	}
	
	
	
	public void removeOriginalGoldenMelon() {
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		while(recipes.hasNext()) {
			if(recipes.next().getResult().getType().equals(Material.GLISTERING_MELON_SLICE)) {
				recipes.remove();
			}
			
		}
		
	}
}