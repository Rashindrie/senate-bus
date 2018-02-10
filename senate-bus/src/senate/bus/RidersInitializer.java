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
    
    private final Halt busHalt;                                 //waiting area
    private final Random random = new Random();                 //Generate random number
    private final float ridersMeanArrivalTime = 30F * 1000;     //mean arrival time - given in problem definition
    private final int speed;                                    //simulation speed up added
    
    public RidersInitializer(Halt busHalt, int speed) {
        this.busHalt = busHalt;
        this.speed = speed;
    }

    @Override
    public void run() {

        int myID = 1;

        while (true) {

            try {
                /*
                 * create a rider object passing - mutex, SemaphoreToSignalBoarded, SemaphoreToSignalBusArrival, busHalt, and myID
                */
                Riders rider = new Riders(busHalt.getMutex(), busHalt.getSemaphoreToSignalBoarded(), busHalt.getSemaphoreToSignalBusArrival(), busHalt, myID);
                
                //increment the thread Id count
                myID+=1;
                
                //start running the created rider thread
                (new Thread(rider)).start();

                //set a gap between two consecutive riders using the getRidersArrivalTime() function
                Thread.sleep(getRidersArrivalTime());
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //returns the desired timing gap between two consecutive riders
    public long getRidersArrivalTime() {
        return (Math.round(Math.log(1-random.nextDouble()) * (-ridersMeanArrivalTime) )/speed);
    }    
}
