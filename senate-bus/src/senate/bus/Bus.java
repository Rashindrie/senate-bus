/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senate.bus;

import java.util.concurrent.Semaphore;

public class Bus extends Thread{
    private int busCapacity = 50;
    
    private Semaphore mutex;
    private Semaphore  boarded;
    private Semaphore busArrived;
    private BusHalt busHalt;
    
    public Bus(Semaphore mutex, Semaphore boarded, Semaphore busArrived, 
            BusHalt busHalt, Bus bus){
        this.mutex = mutex;
        this.boarded = boarded;
        this.busArrived = busArrived;
        this.busHalt = busHalt;
    }
    
    @Override
    public void run(){
        try{
            mutex.acquire();
            int n = Math.min(busHalt.getWaitingCount(), busCapacity);
            
            for (int i=0;i<n; i++){
                busArrived.release();
                boarded.acquire();
            }
            
            busHalt.setWaitingCount(Math.max(busHalt.getWaitingCount()-50,0));
            mutex.release();
            
            depart(1);            
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void board(int index){
        System.out.println("Thread " + index + " Boarded.");
    }
    
    public void depart(int index){
        System.out.println("Bus " + index + " Departed.");
    }
}
