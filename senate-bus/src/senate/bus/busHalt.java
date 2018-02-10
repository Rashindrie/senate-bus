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
    private final Semaphore mutex = new Semaphore(1);    // Semaphore used to handle access to the waiting variable    
    private final Semaphore boarded = new Semaphore(0);    // Semaphore to enter queue (multiplex)
    private final Semaphore busArrived = new Semaphore(0);    // Semaphore to check whether bus has arrived 
   
    //Return semaphore needed to enter waiting area
    public Semaphore getSemaphoreToSignalBoarded() {
        return boarded;
    }

    //Return semaphore needed for signalling arrival of bus
    public Semaphore getSemaphoreToSignalBusArrival() {
        return busArrived;
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
    public void setWaitingCount(int newVal) {
        waiting = newVal;
    }  
    
}
