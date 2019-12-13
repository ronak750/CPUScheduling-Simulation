
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apar
 */
public class Clock extends Thread{
    
    ReadyQue processQue;
    ReadyQue readyQue=new ReadyQue();
    int timer=0;
    int scale=500;
    int schedule;
    Transfer t_thread=new Transfer();
    Fcfs fcfs=new Fcfs();
    Clock(ReadyQue pq,int schedule)
    {
        processQue=pq;
        Collections.sort(processQue,new SortByArrivalTime());
        this.schedule=schedule;
        t_thread.start();
        fcfs.start();
    }
    
    public void run()
    {
        while(true){
            try {
                Thread.sleep(scale);
            } catch (InterruptedException ex) { }
            timer++;
        }
    }
    
    
    class Transfer extends Thread{
        public void run()
        {
            int pointer=0;
            for(;pointer<processQue.size();)
            {     
                Process p=processQue.get(pointer);
                if(timer>=p.arrivalTime)
                {
                    switch(schedule)
                    {
                        case 1:
                        case 5: readyQue.add(p);
                                break;
                        case 2: readyQue.addByTime(p); break;
                        case 3: readyQue.addByRemainingTime(p); break;
                        case 4: readyQue.addByPriority(p); break;
                    }
                    
                    pointer++;
                }
                else {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {}                        
                }
            }
        }

    }
    
    class Fcfs extends Thread{
        public void run()
        {
            int pointer=0;
            for(;pointer<processQue.size();)
            {
                if(!readyQue.isEmpty())
                {    Process p=readyQue.get(0);
                    p.remainingTime--;
                    p.servedTime++;
                    if(p.remainingTime==0)
                    {
                        p.complete(timer);
                        readyQue.remove(p);
                        pointer++;
                    }
                    
                    
                }
                else 
                    try {
                        Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
    }
    
    
    
}
