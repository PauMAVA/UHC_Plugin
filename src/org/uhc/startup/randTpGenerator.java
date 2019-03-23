package org.uhc.startup;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class randTpGenerator {
	
	//CONSTRUCTOR-----------------------------------
	uhcStartConfig directPlugin;
	public randTpGenerator(uhcStartConfig passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	uhcCore indirectPlugin;
	public randTpGenerator(uhcCore passedPlugin) {
		this.indirectPlugin = passedPlugin;
	}
	//CONSTRUCTOR-----------------------------------
	
	World world = Bukkit.getWorld("world");
	WorldBorder border = world.getWorldBorder();
	private final int staticCoordinate =  (int) border.getSize();
	private int constant = 0;
	
	private static int getRandomNumberFromArray(int[] array) {
		int number = new Random().nextInt(array.length);
		return array[number];
	}
	
	//Scramble border sizes order of assignation
	public String[] setBorderNumeration() {
		int[] numArray = new int[] {1,2,3,4};
		String[] sidesArray = new String[] {"north","south","east","west"};
		String[] scrambledSidesArray = new String[4];
		int i = 0;
		while(numArray.length != 0) {
			int randomValue = getRandomNumberFromArray(numArray);
			scrambledSidesArray[i] = sidesArray[(randomValue - 1)]; 
			numArray = ArrayUtils.removeElement(numArray, randomValue);
			i++;
		}
		Bukkit.broadcastMessage(scrambledSidesArray[0] + ", " + scrambledSidesArray[1] + ", " + scrambledSidesArray[2] + ", " + scrambledSidesArray[3]);	
		return scrambledSidesArray;
	} 
	
	public void tpPlayers(String[] sides) {
		Set<String> teamNames = uhcCore.getInstance().getConfig().getConfigurationSection("teams").getKeys(false);
		String[] teams = teamNames.toArray(new String[0]);
		int exceedingTeams = teams.length % 4;
		HashMap<String, String> teamSide = new HashMap<String, String>();
		switch(constant == 0) {
			case exceedingTeams == 0:
				for (int i = 0; i != teams.length; i++) {
					teamSide.put(teams[i], sides[i]);
				}
				break;
			case exceedingTeams == 1:
				
				break;
			case exceedingTeams == 2:
				break;
			case exceedingTeams == 3:
				break;
		}
		return;
	}
}
