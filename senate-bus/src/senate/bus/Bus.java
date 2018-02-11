/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.concurrent.Semaphore;

public class Bus extends Thread{
    private final int busCapacity = 50; //Bus capacity
    private final int myId;             //Thread Id

    private final Semaphore mutex;      //Semaphore to guard the access 'waiting' variable
    private final Semaphore  boarded;   //Semaphore to signal that a rider boarded the bus
    private final Semaphore busArrived; //Semaphore to signal that bus has arrived
    private final Halt busHalt;         //Denotes the waiting Area
    
    public Bus(Semaphore mutex, Semaphore boarded, Semaphore busArrived, Halt busHalt, int myId){
        this.mutex = mutex;
        this.boarded = boarded;
        this.busArrived = busArrived;
        this.busHalt = busHalt;
        this.myId = myId;
    }
    
    @Override
    public void run(){
        try{
            //acquire mutex
            mutex.acquire();
            
            System.out.println("Bus number: " + myId);
            System.out.println("Riders waiting to get in to Bus: " + busHalt.getWaitingCount());
            
            /*
             * Get the number of riders who can board the bus.
             * If waiting count >= 50, only 50 is allowed to board the bus
             * If waiting count < 50, all waiting to board the bus can board it
            */
            int n = Math.min(busHalt.getWaitingCount(), busCapacity);
            
            for (int i=0;i<n; i++){
                busArrived.release();   //Signal n times, that the bus has arrived so that n threads can board it
                boarded.acquire();      //Semaphore to check whether the n thread(s) ackowledged the busArrived signal and boarded the bus
            }
            
            // Set waiting count to represent the number of threads who were waiting to board the bus but could not board due to bus filling upto capacity
            busHalt.setWaitingCount(Math.max(busHalt.getWaitingCount()-50,0));
            
            System.out.println("Riders remaining after bus leaves: " + busHalt.getWaitingCount() + " \n");
            
            //release the mutex allowing other riders who were blocked to carry on its work
            mutex.release();
            
            //depart from the halt
            depart(myId);            
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void depart(int index){
        //bus departs
        //System.out.println("Bus Departed: " + index + " \n");
    }
}
