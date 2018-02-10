/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.Scanner;

public class Main {                   
    public static void main(String[] args) {
        
        Halt busHalt = new Halt();    
        final int simulationSpeedUp = 100;      
        
        RidersInitializer ridersGenerator = new RidersInitializer(busHalt, simulationSpeedUp);
        (new Thread(ridersGenerator)).start();
        
        BusInitializer busGenerator = new BusInitializer(busHalt, simulationSpeedUp);
        (new Thread(busGenerator)).start();
        
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
            if(userInput != null)
                System.exit(0);
    }
 
    
}
