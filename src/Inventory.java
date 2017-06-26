import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Inventory {
	private HashMap<String, Item> items;
	
	Inventory() {
		items = new HashMap<String, Item>();
	}
	public HashMap<String, Item> getItems(){
		return items;
	}
	public void addItem (String itemName, Item item) {
		items.put(itemName, item);
	}
	/*public boolean inInventory(String itemName) {
		for (Item item : items) {
			if (itemName.equalsIgnoreCase(item.getName())) {
				return true;
			}
		}
		return false;
	}
	public Item getItem(String itemName) {
		for (Item item : items) {
			if (itemName.equalsIgnoreCase(item.getName())) {
				return item;
			}
		}
		return null;
	}
	public String getItemString(String allItemNames) {
		return allItemNames;
		
	}
	public void take(String itemName) {
		if (inInventory(itemName)) {
			Item item = getItem(itemName);
			this.items.add(item);
		}
	}
	public void drop() {
		
	}
	public List<Item> getArrayList() {
		return items;
	}*/
}
