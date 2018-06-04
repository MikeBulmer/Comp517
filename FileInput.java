// Comp 517 Assignment 10
// Michael Bulmer
// 11/12/2017
// File Input Class
// Given a file on instantiation
// Method exists to retrive strings in that file as an arraylist.



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

class FileInput {

    private ArrayList<String> words = new ArrayList<String> ();
    private String filename;
    private Scanner inputfile;

    // Input class is given the filename that it will be using
    // when it is created.  If the file isn't present a error will
    // be raises and the program will end.
    FileInput(String filename) {
        this.filename = filename;
        try {
            inputfile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("file not found\n filename passed was :" + filename);
            System.exit(1);
            return;
        }
    }


// Return an arraylist of the strings in the file.  Words are divided by
// white space as defined by the hasNext() method.
    public ArrayList<String> getInputWords() {

        Debug.dprint("geting items stings");
        Debug.dprint("input file is " + inputfile);
        while (inputfile.hasNext()) {
            String next = inputfile.next();
            Debug.dprint("adding " + next);
            words.add(next);

        }
        inputfile.close();
        return words;

    }

}
