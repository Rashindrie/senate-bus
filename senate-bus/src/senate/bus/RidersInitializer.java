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
    
    private float arrivalMeanTime;
    private Halt busHalt;
    private Bus bus;
    private Random random = new Random();

    public RidersInitializer(float arrivalMeanTime, Halt busHalt) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.busHalt = busHalt;
//        this.bus = bus;
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

                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getExponentiallyDistributedBusInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
    
}
