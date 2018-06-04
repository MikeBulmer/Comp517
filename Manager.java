// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// Manager Class


import java.util.Arrays;
import java.util.*;

//Manager class of the warehouse assignment.  Creates the warehouse and
//then places the user into a room of there choosing.
class Manager {
    // While the current structure of commands is all single letters
    // I am using strings as this will give greater future flexablilty.
    private ArrayList<String> commands = new ArrayList<String>();
    private static String[] possibleDoors = {"s","n","e","w"};
    private final static int SIZE = 8;
    private Warehouse warehouse;
    private Player player;

    public static void main(String[] args) {
        Manager game = new Manager();
        // Setup up the game and enters the first room
        game.setup();
    }


    // Function that runs once a user enters a room.  Currently only describes
    // the room to the user.  Anticipated this function will grow.

    private void enterRoom() {
        Debug.dprint("x is :"+player.x()+" y is :" +player.y());

        System.out.println(warehouse.describeRoom(player.y(), player.x()));

        // On entry to a room the valid commands lists needs to be updated.
        // Rather the complicate the program looking at what should remain
        // and what should be added or removed, I clear the list and build
        // a list of valid commands for that room.  Perhaps these lists of
        // valid commands could be stored in the room object.  Howveer in the
        // future the game could be adpated so that the player state
        // influences this list.

        commands.clear();
        commands.add("i");
        commands.add("p");
        commands.add("d");
        commands.add("x");

        for (char door : warehouse.currentDoors(player.x(), player.y() )) {
            commands.add(Character.toString(door).toLowerCase());
        }

        for (String s : commands) {
            Debug.dprint(s);
        }
        commandPromt();
    }


    private void commandPromt() {

        String command = MIHC.getString("\nWhat would you like to do?: ");
        command = command.toLowerCase();

        // debugging walk through walls mode.
        // Not validated can go out of range and crash program.
        if (Debug.debugMode) {
            switch(command) {
                case "dn":
                    moveRoom("n");
                    break;
                case "ds":
                    moveRoom("s");
                    break;
                case "de":
                    moveRoom("e");
                    break;
                case "dw":
                    moveRoom("w");
                    break;
                }
            }

        if (commands.contains(command)) {
            System.out.println("OK");
            switch(command) {
                //Intentional fall through in switch.
                case "n":
                case "s":
                case "e":
                case "w":
                    moveRoom(command);
                    break;
                case "i":
                    inventory();
                    break;
                case "d":
                    dropItem();
                    break;
                case "p":
                    pickup();
                    break;
                // Not required by the problem spec but
                //  required or you can't leave the game.
                case "x":
                    System.out.println("bye");
                    System.exit(0);
                    break;
                default:
                    Debug.dprint("error shouldn't be here");
            }
        // If door command is made but the door doesn't exist
        } else if (Arrays.asList(possibleDoors).contains(command)) {
            System.out.println("The isn't a door there.");
            commandPromt();
        // Anything else isn't understood my the program
        } else {
            System.out.println("I don't understand that");
            commandPromt();
        }
    }


    private void setup() {
        warehouse = new Warehouse(SIZE, SIZE);
        // Create player with random coordinates
        player = new Player(
            (int)(Math.random()*SIZE+1),
            (int)(Math.random()*SIZE+1)
            );
        Debug.dprint(""+warehouse);

        // allocate items to rooms

        //Read file and get ArrayList of strings
        FileInput names = new FileInput("names.txt");
        ArrayList<String> itemNames = names.getInputWords();
        // If the input file doesn't have enough names then the program
        // stops.  If more names than required are passed then they
        // are ignored and only the first 50 used.
        if(itemNames.size() < 50) {
            System.out.println("error not enough names supplied");
            System.exit(1);
        }
        Debug.dprint("" +itemNames.size());

        // Adding items to random rooms
        for (int i = 0; i<50 ; i++) {
            int x = (int)(Math.random()*SIZE + 1);
            int y = (int)(Math.random()*SIZE + 1);
            Debug.dprint("\nadding item " + i +" to "+ x +":" + y);
            Debug.dprint("\n name is "+itemNames.get(i));

            Item addingItem = new Item(itemNames.get(i));
            warehouse.addItem(x,y,addingItem);

            Debug.dprint("" +addingItem);
        }

        // Debugging code, checks number of items added and outputs which
        // items have been added to which rooms.
        int total = 0;
        for (int height = 1  ; height <= SIZE ; height++)
        {
            for (int width = 1 ; width <= SIZE  ; width++)
            {
                Debug.dprint("room"+width+height);

                Debug.dprint("number of items: "+
                            warehouse.getRoom(width, height).getItems().size());
                total += warehouse.getRoom(width, height).getItems().size();
            }
        }
        Debug.dprint("number of items in rooms" + total);
        // After setting up the game we want to start.
        System.out.println("Starting the game...");
        enterRoom();

    }


    private void inventory() {
        System.out.println(player.describeBackPack());
        commandPromt();
    }


    private void pickup() {
        Room room = warehouse.getRoom(player.x(), player.y());
        System.out.println(room.describeRoomItems());
        //No items in room then should return to the main prompt.
        if (!room.itemsInRoom()) {
            commandPromt();
        }
        int itemNo = MIHC.getInt(
            "\nWhich item would you like to pickup? ",
            1,                  // Min value for validation
            room.numberOfItems() // Max value for validation
            )-1; // List is 0 indexed

        //Checks is there is space in the backpack
        if (player.backPackSpace() >= room.getItem(itemNo).getSize()) {
            player.addBackPackItem(room.removeItem(itemNo));
            System.out.println("Item added\n");
        }
        else {
            System.out.println("no space");
            commandPromt();
        }
        commandPromt();
    }


    private void dropItem()
    {
        Room room = warehouse.getRoom(player.x(), player.y());

        if (player.itemsInBackPack() == 0)
        {
            System.out.println("There is nothing in your backpack to drop.");
            commandPromt();
        }
        System.out.println(player.describeBackPack());
        int itemNo = MIHC.getInt("\nWhich item would you like to drop? ",
                                    1, player.itemsInBackPack())-1;

        room.addItem(player.removeBackPackItem(itemNo));
        commandPromt();

    }


    private void moveRoom(String command)
    {
        switch(command)
        {
            case "n":
                player.moveNorth();
                enterRoom();
                break;
            case "s":
                player.moveSouth();
                enterRoom();
                break;
            case "e":
                player.moveEast();
                enterRoom();
                break;
            case "w":
                player.moveWest();
                enterRoom();
                break;
            default:
                Debug.dprint("error in moveroom, manager, switch statement");
                break;
        }
    }
}
