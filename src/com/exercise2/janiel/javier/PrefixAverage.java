
package com.exercise2.janiel.javier;

import java.awt.BasicStroke;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class PrefixAverage {

	/** Returns an array a such that, for all j, a[j] equals 
	 * the average of x[0], ..., x[j]. 
	 * A[j] = (X[0] + X[1] + � + X[j])/(j+1)
	 * 
	 * ******************************************************/
	// inner loop size will be 1, 2, 3, ..., n  (based on j=0,1,2,...,n-1)
	// we know that 1+2+3+...+ n-1+n = n(n+1)/2
	// so, the running time os O(n^2)
	public static double[] prefixAverage1(double[] x) {
		int n = x.length;
		double[] a = new double[n];    // filled with zeros by default
		for (int j=0; j < n; j++) {
			double total = 0;            // begin computing x[0] + ... + x[j]
			for (int i=0; i <= j; i++)
				total += x[i];
			a[j] = total / (j+1);        // record the average
		}
		return a;
	}

	/** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
	// the running time is O(n)
	public static double[] prefixAverage2(double[] x) {
		int n = x.length;
		double[] a = new double[n];    // filled with zeros by default
		double total = 0;              // compute prefix sum as x[0] + x[1] + ...
		for (int j=0; j < n; j++) {
			total += x[j];               // update prefix sum to include x[j]
			a[j] = total / (j+1);        // compute average based on current sum
		}
		return a;
	}


	//main method
	//Student Number: 301379377
	//Student Name: Janiel Mark Javier
	public static void main(String[] args) {

		//array with different sizes for testing the method/function
		//   int[] sizes = {50000, 100000, 200000, 400000, 800000, 1600000};

		   int[] sizes = {70000, 100000, 200000, 400000};

		//For demo purposes (quick results)
	//	int[] sizes = {5000, 10000, 20000, 40000};

		//initialize a random number generator for giving array random values
		Random rand = new Random();

		//initialize series for plotting runtimes (using JFreeChart)
		XYSeries series1 = new XYSeries("prefixAverage1");
		XYSeries series2 = new XYSeries("prefixAverage2");

		//loop over the different sizes
		for (int n : sizes) {
			double[] x = new double[n];

			// fill the array with random values
			for (int i = 0; i < n; i++) {
				x[i] = rand.nextDouble();
			}

			//Measure and print the time taken by prefixAverage1
			long startTime=System.currentTimeMillis();
			prefixAverage1(x);
			long endTime=System.currentTimeMillis();
			long duration = endTime-startTime;
			System.out.println("\nTime taken by prefixAverage1 for n = " + n + ": " + duration + " ms");
			series1.add(n, duration); //Add point to series1 for the graph

			//Measure and print the time taken by prefixAverage1
			long startTime2=System.currentTimeMillis();
			prefixAverage2(x);
			long endTime2=System.currentTimeMillis();
			long duration2 =  endTime2-startTime2;
			System.out.println("Time taken by prefixAverage2 for n = " + n + ": " + duration2 + " ms");
			series2.add(n, duration2); //Add point to series2 for the graph
		}

		//Prepare/initialize dataset for plotting
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		//Create chart with the dataset
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Time Comparison", // chart title
				"Array Size",      // x axis label
				"Time (ms)",       // y axis label
				dataset,           // data
				PlotOrientation.VERTICAL,
				true,              // include legend
				true,              // tooltips
				false              // urls
				);

		// Create logarithmic axes
		LogarithmicAxis logAxisX = new LogarithmicAxis("Array Size");
		LogarithmicAxis logAxisY = new LogarithmicAxis("Time (ms)");

		// Set the plot to use logarithmic axes
		XYPlot plot = chart.getXYPlot();
		plot.setDomainAxis(logAxisX);
		plot.setRangeAxis(logAxisY);

		//make the lines thicker for clearer view
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f)); 

		//Display chart in a frame
		ChartPanel chartPanel = new ChartPanel(chart);
		ApplicationFrame frame = new ApplicationFrame("Prefix Averages");
		frame.setContentPane(chartPanel);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}

}

/*
 * Output when running the code (it might take some time). prefixAverage2 is better because it has lesser runtime.
 * 
 * Also: in a log-log plot, a straight line represents a power-law relationship between the variables. 
 * From the chart (under ../src/loglogchart.jpg), it appears that prefixAverage1 has a steeper slope than prefixAverage2, 
 * 	indicating that its runtime increases more quickly with the size of the input.

Time taken by prefixAverage1 for n = 50000: 587 ms
Time taken by prefixAverage2 for n = 50000: 1 ms

Time taken by prefixAverage1 for n = 100000: 2322 ms
Time taken by prefixAverage2 for n = 100000: 1 ms

Time taken by prefixAverage1 for n = 200000: 9676 ms
Time taken by prefixAverage2 for n = 200000: 1 ms

Time taken by prefixAverage1 for n = 400000: 40087 ms
Time taken by prefixAverage2 for n = 400000: 3 ms

Time taken by prefixAverage1 for n = 800000: 163580 ms
Time taken by prefixAverage2 for n = 800000: 4 ms

Time taken by prefixAverage1 for n = 1600000: 675408 ms
Time taken by prefixAverage2 for n = 1600000: 7 ms
 */
