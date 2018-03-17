package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryFakeDatabaseForItems {

	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		List<Item> itemList = db.getAllItems();
		
		// check if anything was returned and output the list
		if (itemList.isEmpty()) {
			System.out.println("No items found");
		}
		else {
			for (Item item : itemList) {
				System.out.println("item_id = " + item.getID() + "\n" 
						+ item.getName() + "\n" + item.getLongDescription() + "\n" 
						+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
						+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
			}
		}
	}
}

