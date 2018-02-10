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
    
    private float arrivalMeanTime;
    private BusHalt busHalt;
    private Random random = new Random();

    public BusInitializer(float arrivalMeanTime, BusHalt busHalt) {
        this.arrivalMeanTime = arrivalMeanTime;
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
                
                Thread.sleep(5000);
                
                (new Thread(bus)).start();                

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
