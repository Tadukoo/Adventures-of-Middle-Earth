package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

/**
 * These tests are solely for the Character-Specific (excluding Player) action methods in Game.
 * Other methods in Game are tested elsewhere.
 * 
 * TODO: Get Matt's Game changes (or more) to test move(Character character, String direction)?
 * TODO: Get Matt's Game changes for get_dialog for checking move responses
 */
public class GameCharacterActionsTest{
	private Game game;
	private Player player;
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
	
	
	/*
	 * Move Player
	 */
	
	@Test
	public void testMoveNorth(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move("southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouth(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move("south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move("southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move("west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move("northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		// TODO: Game.move doesn't require specifying a Character currently.
		// It should, because Characters other than Player may move.
		//game.move(player, "northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	/*
	 * Attack
	 * TODO: Attack tests
	 */
	
	/*
	 * Loot
	 * TODO: Loot tests
	 */
}
