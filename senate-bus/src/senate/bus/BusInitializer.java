/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class BusInitializer extends Thread{
    
    private final Halt busHalt;
    private final Random random = new Random();
    private final float busMeanArrivalTime = 20 * 60F * 1000 ;

    public BusInitializer(Halt busHalt) {
        this.busHalt = busHalt;
    }

    @Override
    public void run() {

        int myID = 1;

        while (true) {
            try {          
                Bus bus = new Bus(busHalt.getMutex(), busHalt.getSemaphoreToSignalBoarded(), busHalt.getSemaphoreToSignalBusArrival(), 
                    busHalt, myID);
                myID+=1;
                (new Thread(bus)).start();                
                Thread.sleep(getBusArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getBusArrivalTime() {
        return (Math.round(Math.log(1-random.nextDouble()) * (-busMeanArrivalTime) ));
    }    
}
