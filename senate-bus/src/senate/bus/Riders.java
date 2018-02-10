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
public class Riders extends Thread{
    private final int myId;             //Thread Id

    private final Semaphore mutex;      //Semaphore to guard the access to the bus
    private final Semaphore  boarded;   //Semaphore to signal that a rider boarded the bus
    private final Semaphore busArrived; //Semaphore to signal that bus has arrived
    private final Halt busHalt;         //Denotes the waiting Area
    
    public Riders(Semaphore mutex, Semaphore boarded, Semaphore busArrived, Halt busHalt, int myId){
        this.mutex = mutex;
        this.boarded = boarded;
        this.busArrived = busArrived;
        this.busHalt = busHalt;
        this.myId = myId;
    }
    
    @Override
    public void run(){
        try {
            mutex.acquire();                    //acquire mutex - a rider can only acquire this if a bus is not already at the halt
            busHalt.incrementWaitingCount();    //Increment the waiting count
            mutex.release();                    //release the mutex
          
            busArrived.acquire();               //Signal that the arrived rider is waiting for the bus
            
            board(myId);                        //Once the bus arrives, board the bus
            
            boarded.release();                  //Indicate (signal) that the rider boarded the bus after receiving the busArrived signal
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void board(int index){
        //board bus
        //System.out.println("Thread " + index + " Boarded bus");
    }
}
