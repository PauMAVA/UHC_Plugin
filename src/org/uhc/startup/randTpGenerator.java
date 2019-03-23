package org.uhc.startup;


import java.util.ArrayList;
import java.util.List;
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
	private final int staticCoordinate =  (int) border.getSize() / 2;
	
	private static int getRandomNumberFromArray(int[] array) {
		int number = new Random().nextInt(array.length);
		return array[number];
	}
	
	private static int getRandomIntInRange(int min, int max) {
		Random random = new Random();
		int number = random.nextInt((max - min) + 1) + min;	
		return number;
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
		return scrambledSidesArray;
	} 
	
	//Non multiple of 4 team number assignation not developed yet!
	public String[][] setTeamSide(String[] sides) {
		Set<String> teamNames = uhcCore.getInstance().getConfig().getConfigurationSection("teams").getKeys(false);
		String[] teams = teamNames.toArray(new String[0]);
		//int teamsPerSide = teams.length / 4;
		int exceedingTeams = teams.length % 4;
		String[][] teamSide = new String[2][4];
		if (exceedingTeams == 0) {
			for (int i = 0; i <= teams.length - 1; i++) {
				teamSide[0][i] = teams[i];
				teamSide[1][i] = sides[i];
			}
		} 
		return teamSide;
	}
	
	public void generateRandTpCoords(String[][] teamSide) {
		int randomTpCoord = getRandomIntInRange(staticCoordinate * -1, staticCoordinate);
		int xCoord = 0, zCoord = 0;
		for(int i = 0; i < 4; i++) {
			String teamName = teamSide[0][i];
			String side = teamSide[1][i];
			if (side == "north") {
				xCoord = randomTpCoord;
				zCoord = staticCoordinate - 1;
			} else if (side == "south") {
				xCoord = randomTpCoord;
				zCoord = (staticCoordinate * -1) + 1;
			} else if (side == "east") {
				xCoord = (staticCoordinate * -1) + 1;
				zCoord = randomTpCoord;
			} else if (side == "west") {
				xCoord = staticCoordinate - 1;
				zCoord = randomTpCoord;
			}
			List<String> list = new ArrayList<String>();
			list = uhcCore.getInstance().getConfig().getStringList("teams." + teamName);
			String[] players = list.toArray(new String[0]);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + players[0] + " " + xCoord + " 150 " + zCoord);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + players[1] + " " + xCoord + " 150 " + zCoord);
		}
		
		
		return;
	}
	
}
