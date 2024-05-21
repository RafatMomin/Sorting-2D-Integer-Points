/**
 * @author Rafat Momin
 */

package edu.iastate.cs228.hw2;

import java.lang.IllegalArgumentException;


/**
 *
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter {

    protected Point[] points;
    protected String algorithm = null;

    /**
     * This constructor accepts an array of points as input. Copy the points into the array points[].
     *
     * @param  pts  input array of points
     * @throws IllegalArgumentException if pts == null or pts.length == 0.
     */
    protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException();
        } else {
            getPoints(pts);
        }

    }


    /**
     * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
     * if order == 1. Assign the
     * comparator to the variable pointComparator. 
     *
     *
     * @param order  0   by x-coordinate
     * 				 1   by y-coordinate
     *
     *
     * @throws IllegalArgumentException if order is less than 0 or greater than 1
     *
     */
    public void setComparator(int order) throws IllegalArgumentException {
        if (order < 0 || order > 1) {
            throw new IllegalArgumentException();
        } else {
            Point.setXorY(order == 0);
        }
    }


    /**
     * Use the created pointComparator to conduct sorting.
     * Should be protected. Made public for testing.
     */
    public abstract void sort();


    /**
     * Obtain the point in the array points[] that has median index
     *
     * @return median point
     */
    public Point getMedian() {
        return points[points.length / 2];
    }


    /**
     * Copys the array points[] onto the array pts[].
     *
     */
    public void getPoints(Point[] pts) {
        points = pts;
    }


    /**
     * Swaps the two elements indexed at i and j respectively in the array points[].
     *
     */
    protected void swap(int i, int j) {
        Point tempPoint = points[i];
        points[i] = points[j];
        points[j] = tempPoint;
    }
}
