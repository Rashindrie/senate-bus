/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.Scanner;

public class Main {                   
    public static void main(String[] args) {
        System.out.println("\n ----------------- Press Enter to terminate program ----------------- \n\n");
        
        Halt halt = new Halt();                 //  Initialize a waiting bus halt
        final int simulationSpeedUp = 100;      //  Set a simulation speed to execute the program fast. Use simulationSpeedUp = 1 to execute the program normally
        
        // Initialize riders
        RidersInitializer ridersInitializer = new RidersInitializer(halt, simulationSpeedUp);
        
        // Run the rider Initializer thread
        (new Thread(ridersInitializer)).start();
        
        // Initialize the buses
        BusInitializer busInitializer = new BusInitializer(halt, simulationSpeedUp);
        
        // Run the bus Initializer thread
        (new Thread(busInitializer)).start();
        
        // To terminate the program user can press "Enter"
        Scanner in = new Scanner(System.in);
        String readString = in.nextLine();
        while(readString!= null) {
            if (readString.isEmpty()) 
                System.exit(0);
            readString = in.nextLine();
        }
    }   
}
