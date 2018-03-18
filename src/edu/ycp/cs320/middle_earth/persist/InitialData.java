package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;

public class InitialData {

/*	public static Map getMap() throws IOException {
		Map map = new Map();
		ReadCSV readMap = new ReadCSV("map.csv");
		try {
			while (true) {
				List<String> tuple = readMap.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				map.setID(Integer.parseInt(i.next()));
				map.setName(i.next());
				map.setShortDescription(i.next());
				map.setLongDescription(i.next());
			}
			return map;
		} finally {
			readMap.close();
		}
	}
	
	public static ArrayList<MapTile> getMapTiles() throws IOException {
		ArrayList<MapTile> mapTileList = new ArrayList<MapTile>();
		ReadCSV readMapTiles = new ReadCSV("mapTiles.csv");
		try {
			while (true) {
				List<String> tuple = readMapTiles.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				MapTile mapTile = new MapTile();
				// mapTile.setConnections(
				mapTile.setID(Integer.parseInt(i.next()));
				mapTile.setName(i.next());
				mapTile.setLongDescription(i.next());
				mapTile.setShortDescription(i.next());
				mapTileList.add(mapTile);
			}
			return mapTileList;
		} finally {
			readMapTiles.close();
		}
	}*/
	
	/*			Unsure how to bind the connections
	
	public static ArrayList<HashMap<String, MapTile>> getConnections() throws IOException {
		ArrayList<HashMap<String, MapTile>> connectionList = new ArrayList<HashMap<String, MapTile>>();
		ReadCSV readConnections = new ReadCSV("mapTileConnections.csv");
		try {
			while (true) {
				List<String> tuple = readConnections.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				HashMap<String, MapTile> connections = new HashMap<String, MapTile>();
				connections = i.next();
			}
		return connectionList;
		} finally {
			readConnections.close();
		}
	} */
	
	public static ArrayList<Object> getObjects() throws IOException {
		ArrayList<Object> objectList = new ArrayList<Object>();

		ReadCSV readObjects = new ReadCSV("objects.csv");

		try {
			while (true) {
				List<String> tuple = readObjects.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
		
				objectList.add(extractObject(tuple, i));
			}
			return objectList;
		} finally {
			readObjects.close();
		}
	} 
	
	public static ArrayList<Item> getItems() throws IOException {
		ArrayList<Item> itemList = new ArrayList<Item>();
		ReadCSV readItems = new ReadCSV("items.csv");
		try {
			while (true) {
				List<String> tuple = readItems.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();				
				itemList.add(extractItem(i));
			}
			return itemList;
		} finally {
			readItems.close();
		}
	}
	
/*	public static ArrayList<Quest> getQuests() throws IOException {
		ArrayList<Quest> questList = new ArrayList<Quest>();
		ReadCSV readQuests = new ReadCSV("quests.csv");
		try {
			while (true) {
				List<String> tuple = readQuests.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				Quest quest = new Quest();
				quest.getRewardItems()
				
			}
				
			return questList;
		} finally {
			readQuests.close();
		}
	} */
	
/*	public static ArrayList<Character> getCharacters() throws IOException {
		ArrayList<Character> characterList = new ArrayList<Character>();
		ReadCSV readCharacters = new ReadCSV("characters.csv");
		try {
			List<String> tuple = readCharacters.next();
			if(tuple == null) {
				break;
			}
			while (true) {
				Iterator<String> i = tuple.iterator();
				
				Character character = new Character();
				character.set_race(i.next());
				character.set_name(i.next());
				character.set_gender(i.next());
				character.set_level(Integer.parseInt(i.next()));
				character.set_hit_points(Integer.parseInt(i.next()));
				character.set_magic_points(Integer.parseInt(i.next()));
				character.set_attack(Integer.parseInt(i.next()));
				character.set_defense(Integer.parseInt(i.next()));
				character.set_special_attack(Integer.parseInt(i.next()));
				character.set_special_defense(Integer.parseInt(i.next()));
				character.set_coins(Integer.parseInt(i.next()));
				character.set_location(Integer.parseInt(i.next()));
			
				
				// Temp Code
				ArrayList<Item> itemList = new ArrayList<Item>();
				itemList.add(extractItem(i));
				itemList.add(extractItem(i));
				
				Inventory inventory = new Inventory();
				inventory.set_weight(0);
				inventory.set_items(itemList);
				
				character.set_inventory(inventory);
			}
			return characterList;
		} finally {
			readCharacters.close();
		}
	} */
	
	public static Inventory getInventory() throws IOException {
		Inventory inventory = new Inventory();
		ReadCSV readInventory = new ReadCSV("inventory.csv");
		try {						
			int inventoryWeight = 0;
			ArrayList<Item> itemList = new ArrayList<Item>();

			while (true) {
				List<String> tuple = readInventory.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				Item item = extractItem(i);

				inventoryWeight += (int) item.getItemWeight();
				itemList.add(item);
			}
			inventory.set_weight(inventoryWeight);
			inventory.set_items(itemList);
			return inventory;	
			
		} finally {
			readInventory.close();
		}
	}
	
	/*public static Player getPlayer() throws IOException {
		Player player = new Player();
		ReadCSV readPlayer = new ReadCSV("player.csv");
		try {
			List<String> tuple = readPlayer.next();
			while(true) {		
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				player.set_experience(Integer.parseInt(i.next()));
				player.set_carry_weight(Integer.parseInt(i.next()));
				player.set_quest(i.next());
			}
			return player;
		} finally {
			readPlayer.close();
		}
	}*/	
	
	private static Item extractItem(Iterator<String> i) {		
		Item item = new Item();
		
		item.setID(Integer.parseInt(i.next()));
		
		item.setName(i.next());
		item.setLongDescription(i.next());
		item.setShortDescription(i.next());
		
		item.setItemWeight(Integer.parseInt(i.next()));
		
		String checkIfTrue = i.next();
		if(checkIfTrue.equals("false")) {
			item.setIsQuestItem(false);
		} else {
			item.setIsQuestItem(true);
		}
		
		return item;
	}
	
	private static Object extractObject(List<String> tuple, Iterator<String> i) {
		Object object = new Object();
		
		object.setID(1);//Integer.parseInt(i.next()));
		i.next();
		
		object.setName(i.next());
		object.setLongDescription(i.next());
		object.setShortDescription(i.next());
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		while(true) {
			if(tuple == null) {
				break;
			}
			itemList.add(extractItem(i));
		}
		object.setItems(itemList);
		return object;
	}
}
