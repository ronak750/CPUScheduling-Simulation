/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apar
 */
import java.util.*;
public class SortByArrivalTime implements Comparator<Process>{
    public int compare(Process a,Process b)
    {
        return a.arrivalTime-b.arrivalTime;
    }

}
