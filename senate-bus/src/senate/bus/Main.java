/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.concurrent.Semaphore;

public class Main {
                        
    public static void main(String[] args) {
        
        Halt busHalt = new Halt();    
                
        RidersInitializer ridersGenerator = new RidersInitializer(0, busHalt);
        (new Thread(ridersGenerator)).start();
        
        BusInitializer busGenerator = new BusInitializer(0, busHalt);
        (new Thread(busGenerator)).start();
    }
 
    
}
