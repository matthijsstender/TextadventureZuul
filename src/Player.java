import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Player {
	private Room currentRoom;
	private int maxHealth;
	private int currentHealth;
	private Inventory inventory;

	public Player(){
		maxHealth = 100;
		currentHealth = maxHealth;
		inventory = new Inventory(100);
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
        else if (nextRoom.isOpen() == false) {
        	
        	// check if door is locked with key code (nextroom.haskeycode())
        	if(nextRoom.hasKeyCode()){
        		System.out.println("This door is locked with a key code, do you know the code?");
        		System.out.print("keycode >");

                String inputLine = "";   // will hold the full input line
        		BufferedReader reader =
    	        new BufferedReader(new InputStreamReader(System.in));
    	        try {
    	            inputLine = reader.readLine();
    	        }
    	        catch(java.io.IOException exc) {
    	            System.out.println ("There was an error during reading: "
    	                                + exc.getMessage());
    	        }
    	        
    	        if(inputLine.compareTo(nextRoom.getKeyCode()) == 0){
    	        	
    	        	// in string
    	        	System.out.println("The keycode was right!");
    	            currentRoom = nextRoom;
    	            //System.out.println(getCurrentRoom());
    	            System.out.println(currentRoom.getLongDescription());
    	            currentRoom.openRoom();
    	            //damage(10);
    	        }else{
    	        	// not in string
    	        	System.out.println("The keycode was wrong!");
    	        	damage(10);
    	        	System.out.println("You got shocked and took: 10 damage");
    	        }
    	        
        	}else{
        		System.out.println("The door is locked, you need a key.");
        	}
        	
        	// if has key code ask code if good open door
        	// if not true say message and do nothing
        	
        	// if door not locked with key code dont open door display message
        	
        	//System.out.println("The door is locked, you need a key.");
        } else {
            currentRoom = nextRoom;
            //System.out.println(getCurrentRoom());
            System.out.println(currentRoom.getLongDescription());
        	//damage(10);
        }
    }
    
    // get this inv
    public Inventory getInventory() {
    	return this.inventory;
    }
    
    // get current room
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	// set current room
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
}