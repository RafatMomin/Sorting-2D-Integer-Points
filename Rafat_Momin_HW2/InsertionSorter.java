/**
 * @author Rafat Momin
 */
package edu.iastate.cs228.hw2;


/**
 * This class implements insertion sort.
 */

public class InsertionSorter extends AbstractSorter {

    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     */
    public InsertionSorter(Point[] pts) {
        super(pts);
        super.algorithm = "insertion sort";
    }


    /**
     * Perform insertion sort on the array points[] of the parent class AbstractSorter.
     */
    @Override
    public void sort() {
        for (int i = 1; i < points.length; i++) {
            int j = i;
            while ((j > 0) && (points[j - 1].compareTo(points[j]) < 0)) {
                swap(j, j - 1);
                j--;
            }
        }
    }
}
