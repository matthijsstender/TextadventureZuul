import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
	private Inventory inventory;
	public Item item;
	
	private boolean isOpen = true;
	private String keyCode = "";
	private List<Key> keys;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        keys = new ArrayList<Key>();
        inventory = new Inventory(0);
    }
    
    // get this inv
    public Inventory getInventory() {
    	return this.inventory;
    }
    // check if room is open
    public boolean isOpen() {
    	return isOpen;
    }
    // close room
    public void closeRoom() {
    	this.isOpen = false;
    }
    // open room
    public void openRoom() {
    	this.isOpen = true;
    }
    // add keys that open room
    public void addOpenKey(Key key) {
    	keys.add(key);
    }
    
    public List<Key> getKeys() {
    	return this.keys;
    }
    //check if can open
    public void canOpen(Key key) {
    	
    	Room room = this.getExit("down");
    	if (room == null)return;
    	List<Key> _keys = room.getKeys();
		Iterator it = _keys.iterator();
		while(it.hasNext()){
			Key _key = (Key) it.next();
			if (key.getName().equalsIgnoreCase(_key.getName())){
				System.out.println("You used your key to open the door");
				room.openRoom();
				return;
			}
		}
    }
    // get closed rooms
    public ArrayList<Room> getClosedRooms() {
    	ArrayList<Room> rooms = new ArrayList<Room>();
    	for(int i = 0; i < exits.size(); i++){
    		if (this.getExit(getExitString()).isOpen == false) {
    			rooms.add(this.getExit(getExitString()));
    		}
    	}
    	return rooms;
    }
    public void addKeyCode(String _keyCode) {
    	this.isOpen = false;
    	keyCode = _keyCode;
    }
    public boolean hasKeyCode() {
    	if(keyCode.compareTo("") == 0){
    		return false;
    	}
    	return true;
    }
    
    public String getKeyCode(){
    	return keyCode;
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }
}
