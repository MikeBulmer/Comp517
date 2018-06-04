// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// Player Class

import java.util.LinkedList;

class Player{

    private static final int CAPACITY = 50;
    private int x;
    private int y;
    private LinkedList<Item> backPack = new LinkedList<Item> ();

    // When the player is instatiated they must be given some coordinates
    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method to add an item to the backpack, takes an item.
    // Return true is item was added.
    public Boolean addBackPackItem(Item itemIn) {
        if(itemIn.getSize()< backPackSpace()) {
            backPack.addLast(itemIn);
            return true;
        }
        else {
            return false;
        }
    }


// Returns the current total value of all the items in the backpack.
    public int totalValue() {
        int totalValue = 0;
        for (Item item: backPack ) {
            totalValue += item.getValue();
        }
        return totalValue;
    }


    public int backPackSpace() {
        int freeSpace = CAPACITY;
        for (Item item: backPack) {
            freeSpace -= item.getSize();
        }
        return freeSpace;
    }

    // Remove an item of a given index the
    public Item removeBackPackItem(int position) {
        return backPack.remove(position);
    }

    public int itemsInBackPack() {
        return this.backPack.size();
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    // Move methods
    public void moveNorth() {
        y--;
    }

    public void moveSouth() {
        y++;
    }

    public void moveEast() {
        x++;
    }

    public void moveWest() {
        x--;
    }


    // Returned a string describing the contents of the backpack.
    public String describeBackPack() {
        String description = "";
        if (this.backPack.size()== 0) {
            description += "There are no items in you backpack \n";
        }else {
            description += "Here are the items in your backpack:\n";
        }

        for (int i = 0;i < this.backPack.size() ;i++ ) {
            description += i +1 + ": " + this.backPack.get(i) + "\n";
        }

        description += "The total value of your backpack is: " + totalValue() + "\n";
        description += "Space left in your backpack: " + this.backPackSpace() + "\n";
        return description;
    }

}
