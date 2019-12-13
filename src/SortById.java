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
public class SortById implements Comparator<Process>{
    public int compare(Process a,Process b)
    {
        return a.processId-b.processId;
    }
}
