// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// Room Class


import java.util.LinkedList;

class Room {

    private static final char space= '\u2573';
    private char horizontal;
    private char vertical;
    private Boolean northDoor = false;
    private Boolean southDoor = false;
    private Boolean eastDoor  = false;
    private Boolean westDoor  = false;
    private LinkedList<Item> roomsItems;

    public void addItem(Item itemin) {
        roomsItems.push(itemin);
    }

    public Item removeItem(int position) {
        return roomsItems.remove(position);
    }

    String middle = space+"" +space+"" +space;

    Room(char horizontal, char vertical) {

        this.horizontal = horizontal;
        this.vertical = vertical;
        this.roomsItems = new LinkedList<Item> ();
    }

    //returns a string representation of the entire room.

    public String toString() {

        System.out.println("toStringcalled");
        return  strip1() +"\n"+ strip24() +"\n"+ strip3()
                +"\n"+ strip24() +"\n"+ strip5();
    }

    //Returns the top line of the room with a north door if it exists.

    public String strip1() {

        char northDoorSpace = (this.northDoor)? space: horizontal;
        return  ""+ horizontal + horizontal + northDoorSpace
                + horizontal + horizontal+"";
    }

    //returns the second or fourth line of the room.
    //The second and fouth row are always the same.

    public String strip24() {

       return vertical + middle + vertical+"";
    }

    //Returns the middle strip of a room with appropriate doors.
    public String strip3() {

        char eastDoorSpace = (this.eastDoor)? space: vertical;
        char westDoorSpace = (this.westDoor)? space: vertical;
        return ""+westDoorSpace + middle + eastDoorSpace;
    }

    //Returns the bottom strip of a room with south door if it exists.
    public String strip5() {

        char southDoorSpace = (this.southDoor)? space: horizontal;
        return  "" +horizontal + horizontal + southDoorSpace
                + horizontal + horizontal;
    }

    //Setter for the door props.

    void addDoor(char newDoor) {
        switch (newDoor)
        {
            case 'N':
                northDoor = true;
                break;
            case 'S':
                southDoor = true;
                break;
            case 'E':
                eastDoor = true;
                break;
            case 'W':
                westDoor = true;
                break;
            default:
                //ERROR HANDLING NEEDED
                System.out.println("Throw new error");
                throw new java.lang.Error("Door not valid");
        }
    }

    //Returns an array of the rooms doors.

    //DONT assume value in slot number means a door.
    // IE doors[0] != " "
    //The test is that N exists in the array to provide future flexablilty.
    //IE the rooms may be adapted to have cellers and lofts.
    //or the rooms may take different sizes and have multiple doors on
    //the same wall.

    char[] getDoors() {


        char[] doors = new char[4];
        doors[0] = (this.northDoor) ? 'N' : ' ';
        doors[1] = (this.southDoor) ? 'S' : ' ';
        doors[2] = (this.eastDoor)  ? 'E' : ' ';
        doors[3] = (this.westDoor)  ? 'W' : ' ';
        return doors;
    }

    //Returns the total number of doors the room has.

    int getNumberOfDoors() {

        int numberOfDoors =0;
        numberOfDoors += (northDoor) ?  1 : 0 ;
        numberOfDoors += (southDoor) ?  1 : 0 ;
        numberOfDoors += (eastDoor)  ?  1 : 0 ;
        numberOfDoors += (westDoor)  ?  1 : 0 ;
        return numberOfDoors;
    }

    public LinkedList<Item> getItems() {
        return roomsItems;
    }

    //Returns a random door.

    static public char getRandomDoor() {

        //Generates a random number 0,1,2 or 3.
        int door = (int)(Math.random()*4);

        //Uses the random number to return N,E,S or W.
        //Due to use of return break not needed.
        switch (door) {
            case 0:
                return 'N';
            case 1:
                return 'S';
            case 2:
                return 'E';
            case 3:
                return 'W';
            default:
                //error handling needs adding
                //If here something have gone  wrong
                System.out.println("error from getRandomDoor\n");
                System.out.println("f here something have gone  wrong");
                return 'x';
        }

    }
    public Boolean itemsInRoom() {
        return this.roomsItems.size() > 0;
    }

    public int numberOfItems() {
        return this.roomsItems.size();
    }

    public String describeRoomItems() {
        String description = "";
        if (this.roomsItems.size()== 0) {
            description += "There are no items to pickup \n";

        }else{
            description += "Here are the items you can pickup:\n";
        }

        for (int i = 0;i < this.roomsItems.size() ;i++ ) {
            description += i+1 + ": " + this.roomsItems.get(i) + "\n";
        }
        return description;
    }

    public Item getItem(int i) {
        return this.roomsItems.get(i);
    }
}
