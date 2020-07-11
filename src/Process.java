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

public class Process {

    int processId;
    int arrivalTime;
    int cpuBrust;
    int priority=5;
    int completionTime;
    int tat=0;
    int wt=0;
    int remainingTime;
    int servedTime;
    boolean isCompleted=false;
    ArrayList<Integer> start =new ArrayList<Integer>();
    ArrayList<Integer> end =new ArrayList<Integer>();
    //ArrayList<Segment> seg= new ArrayList<Segment>();

    void complete(int time)
    {
        completionTime=time;
        remainingTime=0;
        tat=completionTime-arrivalTime;
        wt=tat-cpuBrust;
        isCompleted=true;
        System.out.println("P"+processId+"\t"+arrivalTime+"\t"+cpuBrust+"\t"+priority+"\t"+tat+"\t"+wt);
    }


    /*
     * @gui based
     * 
     */

    //including priority
    void add(int processId,int arrivalTime,int cpuBrust,int priority)
    {
        this.processId=processId;
        this.arrivalTime=arrivalTime;
        this.cpuBrust=cpuBrust;
        this.priority=priority;
        remainingTime=cpuBrust;
    }
    //without priority
    void add(int processId,int arrivalTime,int cpuBrust)
    {
        this.processId=processId;
        this.arrivalTime=arrivalTime;
        this.cpuBrust=cpuBrust;
        remainingTime=cpuBrust;
    }

    void execute(int timer)
    {
        remainingTime--;
        servedTime++;
        start.add(timer);
        end.add(timer+1);
    }
    
    void reset()
    {
        remainingTime=cpuBrust;
        isCompleted=false;
    }
}
