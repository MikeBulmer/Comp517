//Created by Michael Bulmer 13/10/17
//Mike's Input Helper Class - MIHC
//This can be used to support command line input into a program.

import java.util.Scanner;

class MIHC {
    //Asks user for a double, requires a message to the user.
    public static double getDouble(String message) {

        Scanner read = new Scanner(System.in);

        System.out.print(message);

        return read.nextDouble();
    }


    // Get an int from the user with a given range,
    // if input is out of boundary the function with call itself
    public static int getInt(String message,  int minValue, int maxValue) {
        Scanner read = new Scanner(System.in);
        //input set to 0 as otherwise the complier complains that
        //it might not have a value.
        int input = 0;
        Boolean validinput = false;

        while (!validinput) {
            System.out.print(message);
            try {
             input = read.nextInt();
            }
            catch (Exception e) {
                System.out.println("Please enter an integer");

                read = new Scanner(System.in);
                continue;
            }
            if (!(input >= minValue&&input <= maxValue)) {
                    System.out.println("Input not with range. Input should be between " + minValue +" and " +maxValue);

                    System.out.println("Try again");
                }
                else {
                    validinput = true;
                }
            }

        return input;
}

    //Get a string from the user.  Can't be empty.
    public static String getString(String message) {

        Scanner read = new Scanner(System.in);
        System.out.print(message);
        String input = read.nextLine();

        if (input.length() == 0 ) {
            System.out.println("input can't be empty");
            getString(message);
        }

        return input;
    }
}
