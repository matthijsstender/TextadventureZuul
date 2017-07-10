
public class Weapon extends Item{

	public Weapon(String itemName, float itemWeight, String itemDescription, Player player) {
		super(itemName, itemWeight, itemDescription, player);
	}

	public void use() {
		this.getPlayer().damage(10);
	}
	
	
}
