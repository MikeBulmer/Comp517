// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// Warehouse Class


class Warehouse {
    //size of the warehouse
    private int vertical;
    private int horizontal;
    //2D array that represents the warehouse
    private Room[][] arrayOfRooms;


    //Constructor of Warehouse takes the width and height and fill the
    //warehouse with rooms.  Uses default chars for walls and space.
    Warehouse(int vertical, int horizontal) {
        this(vertical, horizontal, '\u25ac', '\u258c');

    }

    //Overloaded Constructor where the calling program can specify the
    //chars to use for walls if the default is not wanted.
    Warehouse(int vertical, int horizontal,char hchar, char vchar) {

        if(vertical > 25 || horizontal > 25) {

            throw new java.lang.Error("invalid warehouse size");
        }

        this.arrayOfRooms = new Room[vertical][horizontal];

        for (int height = 0  ; height < arrayOfRooms.length  ; height++) {

            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                arrayOfRooms[height][width]= new Room(hchar, vchar);
            }
        }

        Debug.dprint("warehouse before doors:\n\n" + this);
        addDoors();
        Debug.dprint("warehouse after adding doors:\n\n" + this);
        neighbourKnockThrough();
        Debug.dprint("warehouse after adding neightbours doors:\n\n" + this);

    }


    //Puts together a string of the warehouse by using the rooms functions
    //supplies itself as a string on line at a time.
    public String toString()
    {
        //Design decision take to not use a string builder in order to keep
        //the code simple.  If performance issues are encountered then this
        //method could be the cause.

        String output = "";

        for (int height = 0  ; height < arrayOfRooms.length  ; height++) {
            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                output += this.arrayOfRooms[height][width].strip1();
            }
            output += "\n";
            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                output += this.arrayOfRooms[height][width].strip24();
            }
            output += "\n";
            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                output += this.arrayOfRooms[height][width].strip3();
            }
            output += "\n";
            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                output += this.arrayOfRooms[height][width].strip24();
            }
            output += "\n";
            for (int width = 0 ; width < arrayOfRooms[height].length  ; width++) {
                output += this.arrayOfRooms[height][width].strip5();
            }
            output += "\n";
        }
        return output;
    }


    //Method that looks a rooms doors and adds matching neightbours doors.

    private void neighbourKnockThrough()
    {
        for (int height = 0  ; height < this.arrayOfRooms.length  ; height++)
        {
            for (int width = 0 ; width < this.arrayOfRooms[height].length  ; width++)
            {
                char[] doors = this.arrayOfRooms[height][width].getDoors();

                for (int a = 0 ; a < doors.length ; a++ )
                {
                    if (doors[a] == 'E')
                    {
                        this.arrayOfRooms[height][width + 1].addDoor('W');
                    }
                    else if (doors[a] == 'W')
                    {
                        this.arrayOfRooms[height][width - 1].addDoor('E');
                    }
                    else if (doors[a] == 'S' )
                    {
                        this.arrayOfRooms[height + 1][width].addDoor('N');
                    }
                    else if (doors[a] == 'N')
                    {
                        this.arrayOfRooms[height - 1][width].addDoor('S');
                    }
                }
            }
        }
    }

    private void addDoors() {
        for (int height = 0  ; height < this.arrayOfRooms.length  ; height++)
        {
            for (int width = 0 ; width < this.arrayOfRooms[height].length  ; width++)
            {
                // The number of required doors has been increase to 3 so that
                // there are more connections in the warehouse.  I was getting
                // too many small sections unconnected to other sections
                if (this.arrayOfRooms[height][width].getNumberOfDoors() < 3) {
                    char[] currentDoors = this.arrayOfRooms[height][width].getDoors();
                    Boolean doorExists = false;
                    Boolean onEdge;
                    char newDoor;
                    do {
                        newDoor = Room.getRandomDoor();
                        onEdge = false;

                        //Logic tests if the random door already exists

                        for (int i = 0; i < currentDoors.length  ; i++ )
                        {
                            if (currentDoors[i] != newDoor) {
                                doorExists = false;
                            }
                            else {
                                doorExists = true;
                            }
                        }
                        //Logic to test is random door would be on the edge of
                        //the warehouse are therefore not allowed.
                        if (newDoor == 'N' && height == 0) {
                            onEdge = true;
                        }
                        else if (newDoor == 'W' && width == 0) {
                            onEdge = true;
                        }
                        else if (newDoor == 'E' && width == arrayOfRooms[height].length -1) {
                            onEdge = true;
                        }
                        else if (newDoor == 'S' && height == arrayOfRooms.length -1) {
                            onEdge = true;
                        }
                        else{
                            onEdge = false;
                        }

                    //Door not allowed if it is on the edge or already exists.
                    } while (doorExists || onEdge);

                    this.arrayOfRooms[height][width].addDoor(newDoor);
                }
            }
        }
    }

    // Method that returned a string describing a given room.

    // It might seem more natural for this method to be part of the room
    // class, however in the future the description might be
    // influenced by the neighbouring rooms so its need to be here.
    // For example "you can hear a monster in an adjoining room"

    public String describeRoom(int row, int column) {

        if(vertical > column-1  ||  horizontal > row-1) {

            throw new java.lang.Error("invalid input to describe room method");
        }

        //minus one as row and columns start from 1 and the array
        //is zero indexed.
        char[] roomDoors = this.arrayOfRooms[row-1][column-1].getDoors();
        String description = "\nYou are in room("+ column+", " + row + ")";

        for (int i = 0; i < roomDoors.length; i++) {

            //While currently N always appears in position 0
            //I check all the slots as this will make the program more robust
            // in the future.
            if (roomDoors[i] == 'N') {
                description += "\nThere is a door to the North";
            }
            if (roomDoors[i] == 'S') {
                description += "\nThere is a door to the South";
            }
            if (roomDoors[i] == 'E') {
                description += "\nThere is a door to the East";
            }
            if (roomDoors[i] == 'W') {
                description += "\nThere is a door to the West";
            }

        }
        //// Extra information in description for testing
        // description += "\n there are "+
        // this.arrayOfRooms[row-1][column-1].getItems().size()+ "items in this room;";

        for (int a = 0; a < this.arrayOfRooms[row-1][column-1].getItems().size(); a++ ) {

            description += "\nThere is " + this.arrayOfRooms[row-1][column-1].getItems().get(a);
        }

        return description;
    }

    // Returns a char array of the doors in a given room in the warehouse
    public char[] currentDoors(int column, int row) {
        return this.arrayOfRooms[row-1][column-1].getDoors();
    }

    //Passes a room so methods of the room can be accessed.
    public Room getRoom(int column, int row) {
        return this.arrayOfRooms[row-1][column-1];
    }

    public void addItem(int x,int y,Item item) {
        this.arrayOfRooms[y-1][x-1].addItem(item);
    }


    public static void main(String[] args) {
        //add tests here
        //As these methods are so heavily used in the creation of a class
        //currently I haven't found a requirement to test them with code.
        // The methods are best checked using the visual output from the
        //debug methods.
    }

}
