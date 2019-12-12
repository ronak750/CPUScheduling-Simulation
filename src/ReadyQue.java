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
public class ReadyQue extends ArrayList<Process>{

    void  addByTime(Process p)
    {
        int i=0;
        while(i<size() )
        {
            
            if(p.cpuBrust>get(i).cpuBrust)
                  i++;
            else break;
        }

        add(i,p);
    }

    void addByRemainingTime(Process p)
    {
        int i=0;
        while(i<size() )
        {

            if(p.remainingTime>=get(i).remainingTime)
                  i++;
            else break;
        }

        add(i,p);
    }
    void addByPriority(Process p)
    {
        int i=0;
        while(i<size() )
        {

            if(p.priority>=get(i).priority)
                  i++;
            else break;
        }

        add(i,p);
    }
    

}
