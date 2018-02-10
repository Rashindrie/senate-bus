/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.Scanner;

public class Main {                   
    public static void main(String[] args) {
        System.out.println("----------------- Press Enter to terminate program ----------------- \n");
        Halt busHalt = new Halt();    
        final int simulationSpeedUp = 100;      
        
        RidersInitializer ridersGenerator = new RidersInitializer(busHalt, simulationSpeedUp);
        (new Thread(ridersGenerator)).start();
        
        BusInitializer busGenerator = new BusInitializer(busHalt, simulationSpeedUp);
        (new Thread(busGenerator)).start();
        
        //To terminate the program user can press enter
        Scanner in = new Scanner(System.in);
        String readString = in.nextLine();
        while(readString!= null) {
            if (readString.isEmpty()) 
                System.exit(0);
            readString = in.nextLine();
        }
    }   
}
