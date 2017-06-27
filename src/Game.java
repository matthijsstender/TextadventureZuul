

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game
{
    private Parser parser;
    private Player player;
    private Room outside, theatre, pub, lab, office, pubBasement, pubRoof;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        player = new Player();
        parser = new Parser();
        createRooms();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            
            if(!player.isAlive()){
            	finished = true;
            	System.out.println("You have died!");
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }	
        else if (commandWord.equals("go")) {
            goToRoom(command);
        }
        else if (commandWord.equals("search")) {
        	search();
        }
        else if (commandWord.equals("take")) {
        	takeItem(command);
        }
        else if (commandWord.equals("drop")) {
        	dropItem(command);
        }
        else if (commandWord.equals("look")) {
            lookAround();
        }
        else if (commandWord.equals("inventory")) {
        	checkInv();
        }
        else if (commandWord.equals("heal")) {
        	player.heal(10);
        	System.out.println("You have been healed for 10 health");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    
    private void lookAround() {
    	System.out.println(player.getCurrentRoom());
    }
    private void dropItem(Command command) {
    	player.getInventory().drop(command.getSecondWord(), player.getCurrentRoom().getInventory());
    }
    private void takeItem(Command command) {
    	player.getInventory().take(command.getSecondWord(), player.getCurrentRoom().getInventory());
    }
    private void search() {
    	System.out.println("You search in a room and find:");
    	player.getCurrentRoom().getInventory().printItems();
    }
    private void checkInv() {
    	System.out.println("You are currently carrying:");
    	player.getInventory().printItems();
    }
    private void goToRoom(Command command) {
    	player.goRoom(command);
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
	
    /**
     * Create all the rooms and link their exits together.
     */
    	private void createRooms()
    	{
    	
    	    // create the rooms
    	    outside = new Room("outside the main entrance of the university");
    	    theatre = new Room("in a lecture theatre");
    	    pub = new Room("in the campus pub");
    	    lab = new Room("in a computing lab");
    	    office = new Room("in the computing admin office");
    	    pubBasement = new Room("inside of the pub's basement");
    	    pubRoof = new Room("on top of the pub's roof");
    	    
    	    Item rock = new Item("rock", 12, "this is a big rock");
    	    Item glass = new Item("glass", 1, "this is a piece of broken glass");
    	    
    	    outside.getInventory().addItem("rock", rock);
    	    lab.getInventory().addItem("glass", glass);
    	    
    	    
    	    // initialise room exits
    	    outside.setExit("east", theatre);
    	    outside.setExit("south", lab);
    	    outside.setExit("west", pub);
    	
    	    theatre.setExit("west", outside);
    	
    	    pub.setExit("east", outside);
    	    pub.setExit("down", pubBasement);
    	    pub.setExit("up", pubRoof);
    	    
    	    pubRoof.setExit("down", pub);
    	
    	    pubBasement.setExit("up", pub);
    	    
    	    lab.setExit("north", outside);
    	    lab.setExit("east", office);
    	
    	    office.setExit("west", lab);
    	
    	    player.setCurrentRoom(outside);  // start game outside
    	}
    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }


    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
