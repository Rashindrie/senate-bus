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
        
        Halt busHalt = new Halt();              //  Initialize a waiting bus halt
        final int simulationSpeedUp = 100;      //  Set a simulation speed to execute the program fast. Use simulationSpeedUp = 1 to execute the program normally
        
        // Initialize riders passing the halt and the speedUp parameters
        RidersInitializer ridersGenerator = new RidersInitializer(busHalt, simulationSpeedUp);
        
        //run the rider Generator thread
        (new Thread(ridersGenerator)).start();
        
        // Initialize the buses passing the halt and the speedUp parameters
        BusInitializer busGenerator = new BusInitializer(busHalt, simulationSpeedUp);
        
        //run the bus Generator thread
        (new Thread(busGenerator)).start();
        
        //To terminate the program user can press "Enter"
        Scanner in = new Scanner(System.in);
        String readString = in.nextLine();
        while(readString!= null) {
            if (readString.isEmpty()) 
                System.exit(0);
            readString = in.nextLine();
        }
    }   
}
