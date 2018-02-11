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
    
    private final Halt busHalt;                                 //waiting area
    private final Random random = new Random();                 //Generate random number
    private final float busMeanArrivalTime = 20 * 60F * 1000 ;  //mean arrival time - given in problem definition
    private final int speed;                                    //simulation speed up added
    
    public BusInitializer(Halt busHalt, int speed) {
        this.busHalt = busHalt;
        this.speed = speed;
    }

    @Override
    public void run() {

        int myID = 1;

        while (true) {
            try {
                /*
                 * create a bus object passing - mutex, SemaphoreToSignalBoarded, SemaphoreToSignalBusArrival, busHalt, and myID
                */
                Bus bus = new Bus(busHalt.getMutex(), busHalt.getSemaphoreToSignalBoarded(), busHalt.getSemaphoreToSignalBusArrival(), busHalt, myID);
                
                //increment the thread Id count
                myID+=1;
                
                //start running the created bus thread
                (new Thread(bus)).start();                
                
                //denotes the desired timing gap between two consecutive buses
                long busArrivalTime = Math.round(Math.log(1-random.nextDouble()) * (-busMeanArrivalTime))/speed;
                
                //set a gap between two consecutive buses using the busArrivalTime value
                Thread.sleep(busArrivalTime);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }    
}
