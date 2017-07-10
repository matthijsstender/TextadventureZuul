public class Key extends Item{

	public Key(String itemName, float itemWeight, String itemDescription, Player player) {
		super(itemName, itemWeight, itemDescription, player);
	}

	public void use() {
    		this.getPlayer().getCurrentRoom().canOpen(this);
	}

}
