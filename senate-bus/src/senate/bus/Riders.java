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
    private Semaphore mutex;
    private Semaphore  boarded;
    private Semaphore busArrived;
    private Halt busHalt;    
    private int myId;
    
    public Riders(Semaphore mutex, Semaphore boarded, Semaphore busArrived, 
        Halt busHalt, int myId){
        this.mutex = mutex;
        this.boarded = boarded;
        this.busArrived = busArrived;
        this.busHalt = busHalt;
        this.myId = myId;
    }
    
    @Override
    public void run(){
        try {
            mutex.acquire();
            busHalt.incrementWaitingCount();
            mutex.release();
          
            busArrived.acquire();
            
            board(myId);
            
            boarded.release();
            
        }catch(InterruptedException ex){
            System.out.println(ex);
        }
    }
    
    public void board(int index){
        //System.out.println("Thread " + index + " Boarded bus");
    }
}
