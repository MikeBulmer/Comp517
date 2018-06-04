//Class of debugging
//If Debug mode is true then
//debug print statements in the program with
//output to terminal/shell
//Michael Bulmer 24/11/17


class Debug {
    static Boolean debugMode = false;

    public static void dprint(String debugMessage) {

        if(debugMode) {
            System.out.println(debugMessage);
        }
    }

    public static void debugOn() {
        debugMode = true;
    }
}
