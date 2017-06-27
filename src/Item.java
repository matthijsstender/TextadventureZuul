

public class Item {
	
	private String name;
	private float weight;
	private String description;
	
	public Item(String itemName, float itemWeight, String itemDescription) {
		this.name = itemName;
		this.weight = itemWeight;
		this.description = itemDescription;
	}
	// get name
	public String getName() {
		return this.name;
	}
	// get weight
	public float getWeight() {
		return this.weight;
	}
	// get description
	public String getDescription() {
		return this.description;
	}
}
