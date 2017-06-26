

public class Player {
	private Room currentRoom;
	private int maxHealth;
	private int currentHealth;
	private Inventory inventory;

	public Player(){
		maxHealth = 100;
		currentHealth = maxHealth;
		inventory = new Inventory();
	}
	public void showCurrentHealth() {
		System.out.println(currentHealth);
	}
	public void damage(int dmg) {
		System.out.println("Oh snap you took " + dmg + " damage!");
		if (currentHealth - dmg <= 0) {
			currentHealth = 0;
		} else {
			currentHealth -= dmg;
		}
	}
	public void heal(int healAmount) {
		if (currentHealth + healAmount >= maxHealth) {
			currentHealth = maxHealth;
		} else {
			currentHealth += healAmount;
		}
	}
	public boolean isAlive() {
		if (currentHealth > 0) {
			return true;
		} else {
			return false;
		}
	}
	
    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    public void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            //System.out.println(getCurrentRoom());
            System.out.println(currentRoom.getLongDescription());
        	//damage(10);
        }
    }
    public Inventory getInventory() {
    	return this.inventory;
    }
    public void setInventory(Inventory inventory) {
    	this.inventory = inventory;
    }
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
}