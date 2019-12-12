/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apar
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

interface Algos {

   public int scale=500;
   
    
}
class FCFS extends Thread implements Algos
{
    ReadyQue readyQue;
    
    FCFS(ReadyQue rq)
    {
        this.readyQue=rq;
    }
    
    public void run()
    {
        int timer=0;
        Collections.sort(readyQue,new SortByArrivalTime());
        int pointer=0;

        for(;pointer<readyQue.size();)
        {
            Process p=readyQue.get(pointer);
            if(timer>=p.arrivalTime)
            {    while(!p.isCompleted)
                {

                    p.remainingTime--;
                    p.servedTime++;
                    timer++;
                    //System.out.println(timer);
                    if(p.remainingTime==0)
                        p.complete(timer);
                        try {
                            Thread.sleep(scale);
                        } catch (InterruptedException ex) {
                           
                        }
                }
                pointer++;
            }
            else{
                try {
                            Thread.sleep(scale);
                        } catch (InterruptedException ex) {
                           
                        }
                timer++;

            }
        }        
    }
    
}


class SJF extends Thread implements Algos{
    
    ReadyQue processQue;
    int timer=0;
    ReadyQue readyQue=new ReadyQue();
    XYZ xy=new XYZ();
    SJF(ReadyQue pq)
    {
        processQue=pq;
        Collections.sort(processQue,new SortByArrivalTime());
        xy.start();
    }
    
    public void run()
    {         
        int count=0;
        for(;count<processQue.size();)
        {
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                readyQue.remove(0);
                while(!p.isCompleted)
                {
                    p.remainingTime--;
                    p.servedTime++;
                    timer++;
                    if(p.remainingTime==0)
                        p.complete(timer);
                    try {
                        Thread.sleep(scale);
                    } catch (InterruptedException ex) {System.out.println(ex); }
                }   
                count++;
            }
            else {
                timer++;
                try {
                    Thread.sleep(scale);
                } catch (InterruptedException ex) { System.out.println(ex); }         
            }
        }      
    }  
    
    class XYZ extends Thread{
        
        public void run()
        {
            int pointer=0;
            for(;pointer<processQue.size();)
            //for(Process p: processQue)
            {     
                Process p=processQue.get(pointer);
                if(timer>=p.arrivalTime)
                {
                    readyQue.addByTime(p);
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
    
        
}


class Priority extends Thread implements Algos{
    
    ReadyQue processQue;
    int timer=0;
    ReadyQue readyQue=new ReadyQue();
    ABC ab=new ABC();
    Priority(ReadyQue pq)
    {
        processQue=pq;
        Collections.sort(processQue,new SortByArrivalTime());
        ab.start();
    }
    
    public void run()
    {         
        int count=0;
        for(;count<processQue.size();)
        {
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                //readyQue.remove(0);                              
                p.remainingTime--;
                p.servedTime++;
                timer++;
                if(p.remainingTime==0)
                {
                    p.complete(timer);
                    count++;
                    readyQue.remove(p);  
                }
                try {
                    Thread.sleep(scale);
                } catch (InterruptedException ex) {System.out.println(ex); }
                   
                //count++;
            }
            else {
                timer++;
                try {
                    Thread.sleep(scale);
                } catch (InterruptedException ex) { System.out.println(ex); }         
            }
        }      
    }  
    
    class ABC extends Thread{
        
        public void run()
        {
            int pointer=0;
            for(;pointer<processQue.size();)
            //for(Process p: processQue)
            {     
                Process p=processQue.get(pointer);
                if(timer>=p.arrivalTime)
                {
                    readyQue.addByPriority(p);
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
    
        
}

class SRTF extends Thread implements Algos{
    
        ReadyQue processQue;
    int timer=0;
    ReadyQue readyQue=new ReadyQue();
    ABC ab=new ABC();
    SRTF(ReadyQue pq)
    {
        processQue=pq;
        Collections.sort(processQue,new SortByArrivalTime());
        ab.start();
    }
    
    public void run()
    {         
        int count=0;
        for(;count<processQue.size();)
        {
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                //readyQue.remove(0);                              
                p.remainingTime--;
                p.servedTime++;
                timer++;
                if(p.remainingTime==0)
                {
                    p.complete(timer);
                    count++;
                    readyQue.remove(p);  
                }
                try {
                    Thread.sleep(scale);
                } catch (InterruptedException ex) {System.out.println(ex); }
                   
                //count++;
            }
            else {
                timer++;
                try {
                    Thread.sleep(scale);
                } catch (InterruptedException ex) { System.out.println(ex); }         
            }
        }      
    }  
    
    class ABC extends Thread{
        
        public void run()
        {
            int pointer=0;
            for(;pointer<processQue.size();)
            //for(Process p: processQue)
            {     
                Process p=processQue.get(pointer);
                if(timer>=p.arrivalTime)
                {
                    readyQue.addByRemainingTime(p);
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
    
}


