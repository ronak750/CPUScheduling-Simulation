

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apar
 */
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

 class Gantt extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public Gantt(final String title, ReadyQue al) {

        super(title);

        final GanttCategoryDataset dataset = createDataset(al);
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    public static GanttCategoryDataset createDataset(ReadyQue al) {

        TaskSeriesCollection collection = new TaskSeriesCollection();
        Collections.sort(al,new SortById());
        for(Process p: al)
        {
            TaskSeries ts=new TaskSeries("Process"+p.processId);
            Task t=new Task("Process"+p.processId,new SimpleTimePeriod(p.start.get(0), p.end.get(p.end.size()-1)));
            for(int i=0;i<p.start.size();i++)
            {
                Task t1=new Task(""+(i+1),new SimpleTimePeriod(p.start.get(i), p.end.get(i)));
                t.addSubtask(t1);

            }
            ts.add(t);
            collection.add(ts);
        }
        /*
        final TaskSeries s1 = new TaskSeries("P0");

        final Task t4 = new Task("P0", new SimpleTimePeriod(0, 50));
        final Task st41 = new Task("1", new SimpleTimePeriod(0, 4));
        // st41.setPercentComplete(1.0);
        final Task st42 = new Task("2", new SimpleTimePeriod(8, 12));

        final Task st43 = new Task("3", new SimpleTimePeriod(16, 20));

        t4.addSubtask(st41);
        t4.addSubtask(st42);
        t4.addSubtask(st43);
        s1.add(t4);

        final TaskSeries s2 = new TaskSeries("P1");

        final Task t2 = new Task("P", new SimpleTimePeriod(0, 10));
        final Task st21 = new Task("11", new SimpleTimePeriod(4, 8));

        final Task st22 = new Task("21", new SimpleTimePeriod(12, 16));

        final Task st23 = new Task("31", new SimpleTimePeriod(20, 24));

        t2.addSubtask(st21);
        t2.addSubtask(st22);
        t2.addSubtask(st23);
        s2.add(t2);

        
        
        collection.add(s2);
*/
        return collection;
    }


    /*  private static Date date(final int day, final int month, final int year) {

     final Calendar calendar = Calendar.getInstance();
     calendar.set(year, month, day);
     final Date result = calendar.getTime();
     return result;

     */
    private JFreeChart createChart(final GanttCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt ", // chart title
                "PROCESS", // domain axis label
                "TIME", // range axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
        );

        CategoryPlot plot = chart.getCategoryPlot();

        DateAxis axis = (DateAxis) plot.getRangeAxis();

    axis.setTickUnit(new DateTickUnit(DateTickUnitType.MILLISECOND, 2));
        axis.setDateFormatOverride(new SimpleDateFormat("S"));
        return chart;
    }

    
}