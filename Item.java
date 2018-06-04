// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// Item Class

class Item {
    private String itemName;
    private int size;
    private int value;

    // Items are generated with a value between 1-20
    // size is between 5-15
    // The constructor needs to be provided with the item name.
    Item(String inName) {

        this(inName,(int)(Math.random()*20+1), (int)(Math.random()*11+5));
    }

    // Future developement - add validation of the int values.
    Item(String inName,int value, int size) {
        this.value = value;
        this.size = size;
        this.itemName = inName;
    }

    public String toString() {
        String article = "a ";
        String first = this.itemName.substring(0,1).toLowerCase();

        // Changes the article to an if the item name starts with a vowel.
        if (first.equals("a")||
            first.equals("e")||
            first.equals("i")||
            first.equals("o")||
            first.equals("u") ) {
                article = "an ";
            }

        return article + itemName + " (size: " +size +" value: " + value +")";
    }

    public int getSize() {
        return this.size;
    }
    public int getValue() {
        return this.value;
    }
    public String getName() {
        return this.itemName;
    }

    public static void main(String[] args) {

        // // **Testing that random method provides numbers in range
        // // 5 -15, with 11000 cycles each position should be hit
        // // around 1000 times
        // int[] hits = new int[20];
        // int hit;
        // for (int i=1;i<11000 ;i++ ) {
        //     hit=(int)(Math.random()*11+5);
        //     System.out.println(hit);
        //     hits[hit]++;
        //
        // }
        // for (int a =0; a< hits.length ;a++ ) {
        //     System.out.println("position " + a);
        //     System.out.println("count" + hits[a]);
        // }
        //
        // // **Testing that random method provides numbers in range
        // //  1-20, with 11000 cycles each position should be hit
        // // around 1000 times
        // int[] hits = new int[22];
        // int hit;
        // for (int i=1;i<20000 ;i++ ) {
        //     hit=(int)(Math.random()*20+1);
        //     System.out.println(hit);
        //     hits[hit]++;
        //
        // }
        // for (int a =0; a< hits.length ;a++ ) {
        //     System.out.println("position " + a);
        //     System.out.println("count" + hits[a]);
        // }
    }
}
