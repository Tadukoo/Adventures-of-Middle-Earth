package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

/**
 * These Tests are meant to solely test handle_command(String command). The methods called by handle_command are 
 * tested in other Test classes.
 * 
 * Movement Commands are in a separate class due to there being a lot of different ones.
 * 
 * TODO: JUNIT: Get Matt's commit of Game, then test get_dialog() for every command tested.
 */
public class GameHandleCommandTest{
	private Game game;
	private Player player;
	private Item sword;
	private Item helmet;
	private Item key;
	private Item wood;
	private Object tree;
	private Object ladder;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private String invalidMode;
	private String noComprend;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		player.set_location(0);
		
		// In case not already in game mode
		game.set_mode("game");
		
		// Populate Player's inventory
		ArrayList<Item> playerItems = new ArrayList<Item>();
		sword = new Item();
		sword.setName("Sword");
		sword.setLongDescription("A Long sword. Probably stolen from a giant golem or something.");
		sword.setItemWeight((float) 9.6);
		sword.setIsQuestItem(false);
		helmet = new Item();
		helmet.setName("Helmet");
		helmet.setLongDescription("A helmet forged in the hot, hot fires of Mordor.");
		helmet.setItemWeight((float) 29.3);
		helmet.setIsQuestItem(false);
		key = new Item();
		key.setName("Key");
		key.setLongDescription("A key to treasure too expensive to buy with Bill Gates' salary. (Believe it)");
		key.setItemWeight((float) 93.1);
		key.setIsQuestItem(true);
		playerItems.add(sword);
		playerItems.add(helmet);
		playerItems.add(key);
		Inventory inventory = new Inventory();
		inventory.set_items(playerItems);
		player.set_inventory(inventory);
		
		// Add Player to Game
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
		// MapTiles		8 1 2
		//				7 0 3
		//				6 5 4
		starting = new MapTile();
		starting.setID(0);
		starting.setName("The Starting Tile");
		starting.setLongDescription("It's not that exciting...");
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northOfStarting.setName("Forest");
		northOfStarting.setLongDescription("You arrive in a lush forest, complete with birds and crickets chirping.");
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		northEastOfStarting.setLongDescription("You arrive in a barren wasteland, complete with radiation poisoning.");
		
		Map map = new Map();
		map.addMapTile(starting);
		map.addMapTile(northOfStarting);
		map.addMapTile(northEastOfStarting);
		game.set_map(map);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(tree);
		starting.setObjects(objs);
		
		ladder = new Object();
		ladder.setName("Ladder");
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses2.put("climb", "It's not so high up here...");
		ladder.setCommandResponses(responses2);
		ArrayList<Object> objs2 = new ArrayList<Object>();
		objs2.add(ladder);
		northOfStarting.setObjects(objs2);
		
		invalidMode = "You can't use that command here.";
		
		wood = new Item();
		wood.setName("wood");
		// TODO: JUNIT: Set wood location to 0 (starting).
		
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(wood);
		game.set_items(items);
		
		noComprend = "Sorry, I didn't understand that.";
	}
	
	@Test
	public void testInvalidCommand(){
		game.handle_command("blofjerf");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(noComprend, game.get_dialog().get(0));
	}
	
	@Test
	public void testTooManyArgumentsInCommand(){
		String response = game.handle_command("test command long");
		
		assertEquals("Too many arguments in your command", response);
	}
	
	@Test
	public void testNullCommandInModeGame(){
		String response = game.handle_command("");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(noComprend, game.get_dialog().get(0));
		assertEquals(null, response);
	}
	
	@Test
	public void testNullCommandInModeInventory(){
		game.set_mode("inventory");
		
		String response = game.handle_command("");
		
		assertEquals(noComprend, response);
	}
	
	@Test
	public void testSpaceCommandInModeGame(){
		String response = game.handle_command(" ");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(noComprend, game.get_dialog().get(0));
		assertEquals(null, response);
	}
	
	@Test
	public void testSpaceCommandInModeInventory(){
		game.set_mode("inventory");
		
		String response = game.handle_command(" ");
		
		assertEquals(noComprend, response);
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * TODO: JUNIT: Save Command
	 */
	
	/*
	 * Inventory Mode Commands
	 * Item Command
	 */
	@Test
	public void testItemCommandNoNumber(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item");
		
		// Check that response is correct
		assertEquals("Please designate the item # you want to view more details of.", response);
	}
	
	@Test
	public void testItemCommandNotInInventoryMode(){
		game.handle_command("item");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	@Test
	public void testItemCommandNotANumber(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item derpykinsmcgee");
		
		// Check that response is correct
		assertEquals("Invalid item selection. Example: 'item 1' to see the item at position 1", response);
	}
	
	@Test
	public void testItemCommandInvalidNumber0(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 0");
		
		// Check that response is correct
		assertEquals("Sorry you dont have an item at that index", response);
	}
	
	@Test
	public void testItemCommandInvalidNumberAboveRange(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 4");
		
		// Check that response is correct
		assertEquals("Sorry you dont have an item at that index", response);
	}
	
	@Test
	public void testItemCommandLowEndOf1(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 1");
		
		// Set item to sword (for copy-pasting)
		Item item = sword;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	@Test
	public void testItemCommandMidRangeOf2(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 2");
		
		// Set item to helmet (for copy-pasting)
		Item item = helmet;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	@Test
	public void testItemCommandHighEndOf3(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 3");
		
		// Set item to key (for copy-pasting)
		Item item = key;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: JUNIT: Open(Object) Tests
	 * TODO: JUNIT: Close(Object) Tests
	 */
	
	/* 
	 * Player-Specific Commands
	 * Climb(Object) Commands
	 */
	@Test
	public void testClimbTreeCommand(){
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandNoClimbablePresent(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandOtherClimbablePresent(){
		game.get_characters().get(0).set_location(1);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommand(){
		game.get_characters().get(0).set_location(1);
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(ladder.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandNoClimbable(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandOtherClimbablePresent(){
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	/*
	 * Player-Specific Commands
	 * Take(Item) Command
	 */
	@Test
	public void testTakeCommand(){
		assertEquals(3, player.get_inventory().get_items().size());
		assertEquals(1, game.get_items().size());
		assertEquals(wood, game.get_items().get(0));
		
		game.handle_command("take wood");
		
		assertEquals(0, game.get_items().size());
		assertEquals(4, player.get_inventory().get_items().size());
		assertEquals(wood, player.get_inventory().get_items().get(0));
		// TODO: JUNIT: Check dialog here.
	}
	
	@Test
	public void testTakeCommandItemNotOnTile(){
		// TODO: JUNIT: Set location of wood to a different tile than starting.
		assertEquals(3, player.get_inventory().get_items().size());
		assertEquals(1, game.get_items().size());
		assertEquals(wood, game.get_items().get(0));
		
		game.handle_command("take wood");
		
		assertEquals(0, game.get_items().size());
		assertEquals(4, player.get_inventory().get_items().size());
		assertEquals(wood, player.get_inventory().get_items().get(0));
		throw new UnsupportedOperationException("Cannot set location of wood to do this test yet.");
		// TODO: JUNIT: Check dialog here
	}
	
	@Test
	public void testTakeCommandItemDoesntExist(){
		assertEquals(3, player.get_inventory().get_items().size());
		assertEquals(1, game.get_items().size());
		assertEquals(wood, game.get_items().get(0));
		
		game.handle_command("take cheese");
		
		assertEquals(1, game.get_items().size());
		assertEquals(3, player.get_inventory().get_items().size());
		// TODO: JUNIT: Figure out what the message would be for not having item there. (Also for previous test).
		//assertEquals(1, game.get_dialog().size());
		//assertEquals("", game.get_dialog().get(0));
		throw new UnsupportedOperationException("Don't know error message yet.");
	}
	
	@Test
	public void testTakeCommandNotInModeGame(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Check that stuff is like this before command run
		assertEquals(3, player.get_inventory().get_items().size());
		assertEquals(1, game.get_items().size());
		assertEquals(wood, game.get_items().get(0));
		
		// Run command and get response
		String response = game.handle_command("take wood");
		
		// Make sure stuff wasn't changed
		assertEquals(1, game.get_items().size());
		assertEquals(3, player.get_inventory().get_items().size());
		
		// Check that response is correct
		assertEquals("Sorry, I didn't understand that.", response);
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: JUNIT: Take(Object, Item) Tests
	 */
	
	/*
	 * Player-Specific Commands
	 * Look Command
	 */
	@Test
	public void testLookCommandAtStarting(){
		game.handle_command("look");
		
		assertEquals(2, game.get_dialog().size());
		assertEquals(starting.getName(), game.get_dialog().get(0));
		assertEquals(starting.getLongDescription(), game.get_dialog().get(1));
	}
	
	@Test
	public void testLookCommandAtNorthOfStarting(){
		game.get_characters().get(0).set_location(1);
		
		game.handle_command("look");
		
		assertEquals(2, game.get_dialog().size());
		assertEquals(northOfStarting.getName(), game.get_dialog().get(0));
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(1));
	}
	
	@Test
	public void testLookCommandNotInModeGame(){
		// Set mode to inventory
		game.set_mode("inventory");
		
		// Run command and get response
		String response = game.handle_command("look");
		
		// Check that response is correct
		assertEquals("Sorry, I didn't understand that.", response);
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: JUNIT: Fast Travel Tests
	 * TODO: JUNIT: Buy(Item) Tests
	 * TODO: JUNIT: Sell(Item) Tests
	 * TODO: JUNIT: Talk(NPC) Tests
	 */
	
	/*
	 * Character-Specific Actions
	 * Attack Command
	 * TODO: JUNIT: Attack command tests
	 */
	
	/*
	 * Character-Specific Actions
	 * Loot Command
	 * TODO: JUNIT: Loot command tests
	 */
}
