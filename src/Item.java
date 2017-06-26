

public class Item {
	
	private String name;
	private String weight;
	private String description;
	
	public Item(String itemName, String itemWeight, String itemDescription) {
		this.name = itemName;
		this.weight = itemWeight;
		this.description = itemDescription;
	}
	public void getInfo() {
		getName();
		getWeight();
		getDescription();
	}
	public String getName() {
		return this.name;
	}
	public String getWeight() {
		return this.weight;
	}
	public String getDescription() {
		return this.description;
	}
}
