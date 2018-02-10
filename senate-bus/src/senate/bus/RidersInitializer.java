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
public class RidersInitializer extends Thread{
    
    private final Halt busHalt;
    private final Random random = new Random();
    private final float ridersMeanArrivalTime = 30F * 1000;
    
    public RidersInitializer(Halt busHalt) {
        this.busHalt = busHalt;
    }

    @Override
    public void run() {

        int myID = 1;

        while (true) {

            try {
                Riders rider = new Riders(busHalt.getMutex(), busHalt.getSemaphoreToSignalBoarded(), busHalt.getSemaphoreToSignalBusArrival(), 
                    busHalt, myID);
                myID+=1;
                (new Thread(rider)).start();

                Thread.sleep(getRidersArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getRidersArrivalTime() {
        float lambda = 1 / ridersMeanArrivalTime;
        return Math.round(lambda * Math.exp(-1 * random.nextDouble()  * lambda));
    }
    
}
