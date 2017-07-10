

public abstract class Item {
	
	private String name;
	private float weight;
	private String description;
	private Player player;
	
	public Item(String itemName, float itemWeight, String itemDescription, Player player) {
		this.name = itemName;
		this.weight = itemWeight;
		this.description = itemDescription;
		this.player = player;
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
	// get player
	public Player getPlayer() {
		return this.player;
	}
	// use
	public abstract void use();
}
