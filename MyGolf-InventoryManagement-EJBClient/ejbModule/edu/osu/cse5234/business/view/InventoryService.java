package edu.osu.cse5234.business.view;

import java.util.List;

import javax.ejb.Remote;

import edu.osu.cse5234.model.Item;

@Remote
public interface InventoryService {
	public Inventory getAvailableInventory();
	public boolean validateQuantity(List<Item> items);
	public boolean updateInventory(List<Item> items);

}
