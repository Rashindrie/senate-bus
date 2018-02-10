/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.concurrent.Semaphore;

public class Bus extends Thread{
    private final int busCapacity = 50;
    private final int myId;

    private final Semaphore mutex;
    private final Semaphore  boarded;
    private final Semaphore busArrived;
    private final Halt busHalt;
    
    public Bus(Semaphore mutex, Semaphore boarded, Semaphore busArrived, 
            Halt busHalt, int myId){
        this.mutex = mutex;
        this.boarded = boarded;
        this.busArrived = busArrived;
        this.busHalt = busHalt;
        this.myId = myId;
    }
    
    @Override
    public void run(){
        try{
            mutex.acquire();
            System.out.println("Bus arrived: " + myId);
            System.out.println("Wating count Before riders get in Bus: " + busHalt.getWaitingCount());
            int n = Math.min(busHalt.getWaitingCount(), busCapacity);
            
            for (int i=0;i<n; i++){
                busArrived.release();
                boarded.acquire();
            }
            
            busHalt.setWaitingCount(Math.max(busHalt.getWaitingCount()-50,0));
            System.out.println("Wating count After riders get in Bus: " + busHalt.getWaitingCount() + " \n");
            mutex.release();
            
            depart(myId);            
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void depart(int index){
        //bus departs
        //System.out.println("Bus Departed: " + index + " \n");
    }
}
