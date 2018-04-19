package edu.ycp.cs320.middle_earth.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class CombatSituationTest{
	private Game game;
	private Player player;
	private CombatSituation battle;
	
	@Before
	public void setup(){
		// Create Game
		game = new Game();
		
		// Create Player
		player = new Player();
		player.set_hit_points(100);
		player.set_attack(40);
		player.set_defense(5);
		
		// Add Player to Game
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
		// Create CombatSituation
		battle = new CombatSituation(game);
	}
	
	@Test
	public void testConstructor(){
		// Check that 2 Characters are involved in the CombatSituation
		assertEquals(2, battle.getCharacters().size());
		
		// Check that Player is present
		assertEquals(player, battle.getCharacters().get(0));
		
		// Check that dialog was updated appropriately
		assertEquals(1, game.get_dialog().size());
		assertEquals("A Goblin appeared out of nowhere!", game.get_dialog().get(0));
	}
	
	@Test
	public void testCreateEnemy(){
		// Create the Enemy
		Enemy enemy = battle.createEnemy();
		
		// Check that Stats and Stuff are correct (based on current setup)
		assertEquals(15, enemy.get_attack());
		assertEquals(25, enemy.get_defense());
		assertEquals(100, enemy.get_hit_points());
		assertEquals(1, enemy.get_level());
		assertEquals("Goblin", enemy.get_name());
	}
	
	@Test
	public void testCalculateAttackEnemy(){
		int mins = 0;
		int minmids = 0;
		int mids = 0;
		int midmaxs = 0;
		int maxs = 0;
		
		for(int i = 0; i < 300; i++){
			int result = battle.calculateAttack(1);
			if(result == 13){
				mins++;
			}else if(result == 14){
				minmids++;
			}else if(result == 15){
				mids++;
			}else if(result == 16){
				midmaxs++;
			}else if(result == 17){
				maxs++;
			}else{
				// Should never get here!
				assertEquals(1, 0);
			}
		}
		
		System.out.println("CalculateAttack for Enemy Distribution");
		System.out.println("Min(13):  " + mins);
		System.out.println("Mmid(14): " + minmids);
		System.out.println("Mid(15):  " + mids);
		System.out.println("Mmax(16): " + midmaxs);
		System.out.println("Max(17):  " + maxs);
	}
	
	@Test
	public void testCalculateAttackPlayerNoArmor(){
		// Min and Max based off of 40 as base player attack
		int min = 36;
		int max = 44;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateDamagePlayerHelmet(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.set_attack_bonus(10);
		
		// Give the Player the Helmet
		player.set_helm(helmet);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
}
