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


abstract class Algos extends Thread{
     
   private int scale=400;
   protected int timeQuantam=3;
   protected int timer=0;
   protected int pointer=0;
   protected int completedCount=0;
   public ReadyQue processQue;
   protected ReadyQue readyQue=new ReadyQue(); 
   
   Algos(ReadyQue pq)
   {
       this.processQue=pq;
       Collections.sort(processQue,new SortByArrivalTime());
   }
   
   //methods
    abstract void addProcess();
    
    void incTimer()
    {
       timer++;
       try {
        Thread.sleep(scale);
        } catch (InterruptedException ex) {
            System.err.println(""+ex.getMessage());
        }
    }
}

class FCFS extends Algos
{
    
    public FCFS(ReadyQue pq)
    {
        super(pq);   
    }
    synchronized public void run()
    {
        timer=0;
        while(completedCount<processQue.size())
        {
            addProcess();      
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);  
                while(!p.isCompleted)
                {
                    p.execute(timer);
                    incTimer();
                    if(p.remainingTime==0)
                    {
                        p.complete(timer);
                        readyQue.remove(p);
                        completedCount++;
                    }       
                }                    
            }
            else incTimer();         
        }
    }
    void addProcess(){
        while(pointer<processQue.size())
        {
            Process p=processQue.get(pointer);
            if(timer>=p.arrivalTime)
            {
                readyQue.add(processQue.get(pointer));
                pointer++;
            }
            else return;
        }
    }
}


class SJF extends Algos
{ 
    SJF(ReadyQue pq)
    {
        super(pq);      
    }
    
    public void run()
    {    
        timer=0;
        while(completedCount<processQue.size())
        {
            addProcess();      
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                while(!p.isCompleted)
                {
                    p.execute(timer);
                    incTimer();
                    if(p.remainingTime==0)
                    {
                        p.complete(timer);
                        readyQue.remove(p);
                        completedCount++;
                    }       
                }  
            }         
            else incTimer();          
        }
    }
        
    void addProcess()
    {
        while(pointer<processQue.size())
        {
            Process p=processQue.get(pointer);
            if(timer>=p.arrivalTime)
            {
                readyQue.addByTime(processQue.get(pointer));
                pointer++;
            }
            else return;
        }
    }
    
}

class Priority extends Algos{

    private int pointer=0;
    private int completedCount=0;
    
    public Priority(ReadyQue pq) {
        super(pq);
    }

    public void run()
    {
        timer=0;
        while(completedCount<processQue.size())
        {
            addProcess();      
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                p.execute(timer);
                incTimer();
                if(p.remainingTime==0)
                {
                    p.complete(timer);
                    readyQue.remove(p);
                    completedCount++;
                }                     
            }         
            else incTimer();           
        }
    }
    
    @Override
    void addProcess() {
       while(pointer<processQue.size())
        {
            Process p=processQue.get(pointer);
            if(timer>=p.arrivalTime)
            {
                readyQue.addByPriority(processQue.get(pointer));
                pointer++;
            }
            else return;
        }
    }
    
}

class SRTF extends Algos {

    public SRTF(ReadyQue pq) {
        super(pq);
    }
    
    @Override
    public void run()
    {
        timer=0;
        while(completedCount<processQue.size())
        {
            addProcess();      
            if(!readyQue.isEmpty())
            {
                Process p=readyQue.get(0);
                p.execute(timer);
                incTimer();
                if(p.remainingTime==0)
                {
                    p.complete(timer);
                    readyQue.remove(p);
                    completedCount++;
                }                     
            }         
            else incTimer();           
        }
    }
    
    @Override
    void addProcess() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while(pointer<processQue.size())
        {
            Process p=processQue.get(pointer);
            if(timer>=p.arrivalTime)
            {
                readyQue.addByRemainingTime(processQue.get(pointer));
                pointer++;
            }
            else return;
        }
    }
    
}

class RR extends Algos{

    private int timerServed=0;
    private int cur=0;
    
    public RR(ReadyQue pq) {
        super(pq);
    }

    @Override
    public void run()
    {
        timer=0;
        while(completedCount<processQue.size())
        {
            addProcess();          
            if(!readyQue.isEmpty())
            {
                if(cur>=readyQue.size())               
                    cur=0;               
                Process p=readyQue.get(cur++);
                timerServed=0;
                while(timerServed<timeQuantam && !p.isCompleted)
                {
                    p.execute(timer);
                    incTimer();
                    if(p.remainingTime==0)
                    {
                        p.complete(timer);
                        readyQue.remove(p);
                        completedCount++;
                    }
                    timerServed++;
                }
            }         
            else incTimer();          
        }
    }
    
    @Override
    void addProcess() {
        while(pointer<processQue.size())
        {
            Process p=processQue.get(pointer);
            if(timer>=p.arrivalTime)
            {
                readyQue.add(processQue.get(pointer));
                pointer++;
            }
            else return;
        }
    }
    
}

