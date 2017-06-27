import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class Inventory {
	private HashMap<String, Item> items;
	private float maxWeight;
	
	Inventory(float weight) {
		items = new HashMap<String, Item>();
		maxWeight = weight;
	}
	
	// take item
	public void take (String itemName, Inventory inventory) {
		if (inventory.containsItem(itemName) && this.canTake(inventory.getItem(itemName))) {
			this.addItem(itemName, inventory.getItem(itemName));
			inventory.removeItem(itemName);
			System.out.println("You took the " + itemName + "!");
			return;
		}
		// if can't take
		if (this.canTake(inventory.getItem(itemName))) {
			System.out.println("You're too fat, you can't take " + itemName);
			return;
		}
		
		System.out.println("There is no item called " + itemName + "..");
	}
	
	// drop item
	public void drop (String itemName, Inventory inventory) {
		if (this.containsItem(itemName) && inventory.canTake(this.getItem(itemName))) {
			inventory.addItem(itemName, this.getItem(itemName));
			this.removeItem(itemName);
			System.out.println("You dropped the " + itemName + "!");
			return;
		}
		
		// TODO add message for too much weight
		
		System.out.println("You have no item called " + itemName + "..");
	}
	
	// get an item from inv
	public Item getItem (String itemName) {
		if(containsItem(itemName)){
			return items.get(itemName);
		}
		return null;
	}
	
	// remove item from inv
	public void removeItem(String itemName){
		items.remove(itemName);
	}
	
	// add item to inv
	public void addItem (String itemName, Item item) {
		items.put(itemName, item);
	}
	
	// check if inv has item
	public boolean containsItem(String itemName) {
		if (items.containsKey(itemName)) {
			return true;
		}
		return false;
	}
	
	// get all items in inv
	public HashMap<String, Item> getAllItems() {
		return items;
	}
	
	// check if can take
	public boolean canTake(Item item) {
		if (item == null) {
			return false;
		}
		
		// if max weight is 0
		if (this.maxWeight == 0) {
			return true;
		}
		
		float weight = item.getWeight();
		
		// create iterator
		Iterator<Entry<String, Item>> it = items.entrySet().iterator();
		
		// while it has a next
		while (it.hasNext()) {
			Entry<String, Item> itm = it.next();
			weight += itm.getValue().getWeight();
		}
		
		// if weight is higher or same as max weight
		if (weight <= maxWeight) {
			return true;
		}
		
		
		return false;
	}
	// print item names in inv
	public void printItems(){
		
		// create iterator
		Iterator<Entry<String, Item>> it = items.entrySet().iterator();
		
		// while it has a next
		while (it.hasNext()) {
			// it get next
			Entry<String, Item> pairs = it.next();
			
			// items = {key, value}
			// items = {name, item}
			
			// get value get name and print it
			System.out.println(pairs.getKey() + ", " + pairs.getValue().getDescription() + ".");
		}
		
		if(items.isEmpty()){
			System.out.println("Nothing special");
		}
		
		System.out.println("");
	}
}
