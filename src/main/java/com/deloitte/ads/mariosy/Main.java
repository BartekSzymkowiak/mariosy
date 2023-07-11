package com.deloitte.ads.mariosy;

import java.util.HashSet;

public class Main {

    public static void main( String[] args )
    {
        App app = new App(new HashSet<User>());
        ConsoleApp consoleApp = new ConsoleApp(app);
        consoleApp.run();

    }

}
