package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

/**
 * These Tests are meant to solely test handle_command(String command). The methods called by handle_command are 
 * tested in other Test classes.
 * 
 * TODO: Get Matt's commit of Game, then test get_dialog() for every command tested.
 */
public class GameHandleCommandTest{
	private Game game;
	private Player player;
	private Object tree;
	private Object ladder;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private MapTile eastOfStarting;
	private MapTile southEastOfStarting;
	private MapTile southOfStarting;
	private MapTile southWestOfStarting;
	private MapTile westOfStarting;
	private MapTile northWestOfStarting;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		player.set_location(0);
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		//TODO: Set Tree location to 0 (starting MapTile).
		
		ladder = new Object();
		ladder.setName("Ladder");
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses.put("climb", "It's not so high up here...");
		ladder.setCommandResponses(responses2);
		//TODO: Set Ladder location to 1 (northOfStarting MapTile).
		
		// MapTiles		8 1 2
		//				7 0 3
		//				6 5 4
		starting = new MapTile();
		starting.setID(0);
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northOfStarting.setLongDescription("You arrive in a lush forest, complete with birds and crickets chirping.");
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		northEastOfStarting.setLongDescription("You arrive in a barren wasteland, complete with radiation poisoning.");
		eastOfStarting = new MapTile();
		eastOfStarting.setID(3);
		eastOfStarting.setLongDescription("You arrive in candyland, where I don't know any of the character names.");
		southEastOfStarting = new MapTile();
		southEastOfStarting.setID(4);
		southEastOfStarting.setLongDescription("You arrive in L.A., just to get a flight to leave.");
		southOfStarting = new MapTile();
		southOfStarting.setID(5);
		southOfStarting.setLongDescription("You arrive in CS320 in 2016, where Logan is failing to make a 2D Platformer in Erlang.");
		southWestOfStarting = new MapTile();
		southWestOfStarting.setID(6);
		southWestOfStarting.setLongDescription("You arrive in I don't know, just give it up already.");
		westOfStarting = new MapTile();
		westOfStarting.setID(7);
		westOfStarting.setLongDescription("You arrive in CS320 a week early for the milestone to realize no one has worked on it "
				+ "yet.");
		northWestOfStarting = new MapTile();
		northWestOfStarting.setID(8);
		northWestOfStarting.setLongDescription("You arrive in... The narrator died of boredom, so we're waiting on a new one.");
		
		// TODO: When MapTile has connections enabled, do this.
		//starting.addConnection("north", 1);
		//starting.addConnection("northeast", 2);
		//starting.addConnection("east", 3);
		//starting.addConnection("southeast", 4);
		//starting.addConnection("south", 5);
		//starting.addConnection("southwest", 6);
		//starting.addConnection("west", 7);
		//starting.addConnection("northwest", 8);
	}
	
	@Test
	public void testInvalidCommand(){
		game.handle_command("blofjerf");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * TODO: Check Character Sheet Tests
	 * TODO: Check Inventory Tests
	 * TODO: Check Map Tests
	 * TODO: Save Tests
	 */
	
	/*
	 * Player-Specific Commands
	 * TODO: Open(Object) Tests
	 * TODO: Close(Object) Tests
	 */
	
	/* 
	 * Player-Specific Commands
	 * Climb(Object) Commands
	 */
	@Test
	public void testClimbTree(){
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeNoClimbable(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no tree", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeOtherClimbablePresent(){
		game.get_characters().get(0).set_location(1);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no tree", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadder(){
		game.get_characters().get(0).set_location(1);
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderNoClimbable(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no ladder", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderOtherClimbablePresent(){
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no ladder", game.get_dialog().get(0));
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: Take(Item) Tests
	 * TODO: Take(Object, Item) Tests
	 * TODO: Look Tests
	 * TODO: Fast Travel Tests
	 * TODO: Buy(Item) Tests
	 * TODO: Sell(Item) Tests
	 * TODO: Talk(NPC) Tests
	 */
	
	
	/*
	 * Character-Specific Commands
	 * Move Commands
	 */
	
	@Test
	public void testMoveNorthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	/*
	 * Character-Specific Actions
	 * Attack Command
	 * TODO: Attack command tests
	 */
	
	/*
	 * Character-Specific Actions
	 * Loot Command
	 * TODO: Loot command tests
	 */
}
