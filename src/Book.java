
public class Book extends Item {

	public Book(String itemName, float itemWeight, String itemDescription, Player player) {
		super(itemName, itemWeight, itemDescription, player);
	}

	public void use() {
		System.out.println("The code is: 420");
	}

}
