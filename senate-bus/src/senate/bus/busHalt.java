/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Admin
 */
public class busHalt {
    private int waiting = 0;
    private int busCapacity = 50;
    
    private final Semaphore mutex = new Semaphore(1);    // Semaphore used to handle access to the waiting variable    
    private final Semaphore enterQueue = new Semaphore(busCapacity);    // Semaphore to enter queue (multiplex)
    private final Semaphore busArrived = new Semaphore(0);    // Semaphore to check whether bus has arrived
    private final Semaphore busDepart = new Semaphore(0);    // Semaphore to check whether bus can depart
    
    
    //Return semaphore needed to enter waiting area
    public Semaphore getSemaphoreToEnterQueue() {
        return enterQueue;
    }

    //Return semaphore needed for signalling arrival of bus
    public Semaphore getSemaphoreToSignalBusArrival() {
        return busArrived;
    }
    
    //Return semaphore needed for bus to depart
    public Semaphore getSemaphoreToSignalBusDeparture() {
        return busDepart;
    }

    //Return semaphore needed for updating the 'waiting' vairable
    public Semaphore getMutex() {
        return mutex;
    }
    
    //Get the waiting count
    public int getWaitingCount() {
        return waiting;
    }

    //Increment the waiting count
    public void incrementWaitingCount() {
        waiting+=1;
    }

    //Decrement the waiting count
    public void decrementWaitingCount() {
        waiting-=1;
    }  
    
}
