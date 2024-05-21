/**
 * @author Rafat Momin
 */

package edu.iastate.cs228.hw2;


/**
 * This class implements selection sort.
 */

public class SelectionSorter extends AbstractSorter {
    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     *
     */
    public SelectionSorter(Point[] pts) {
        super(pts);
        super.algorithm = "selection sort";
    }

    /**
     * Apply selection sort on the array points[] of the parent class AbstractSorter.
     */
    @Override
    public void sort() {
        for (int i = 0; i < points.length; i++) {
            int minimum = i;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].compareTo(points[minimum]) < 0) {
                    minimum = j;
                }
            }
            swap(i, minimum);
        }
    }
}
