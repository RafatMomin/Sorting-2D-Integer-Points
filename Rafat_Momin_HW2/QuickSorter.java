/**
 * @author Rafat Momin
 */

package edu.iastate.cs228.hw2;


/**
 *
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter {

    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     *
     * @param pts   input array of integers
     */
    public QuickSorter(Point[] pts) {
        super(pts);
        super.algorithm = "quicksort";

    }


    /**
     * Carry out quicksort on the array points[] of the AbstractSorter class.
     *
     */
    @Override
    public void sort() {
        quickSortRec(0, points.length - 1);
    }


    /**
     * Operates on the subarray of points[] with indices between first and last.
     *
     * @param first  starting index of the subarray
     * @param last   ending index of the subarray
     */
    private void quickSortRec(int first, int last) {
        if (first >= last) {
            return;
        }
        int partition = partition(first, last);
        quickSortRec(first, partition - 1);
        quickSortRec(partition + 1, last);


    }

    /**
     * Operates on the subarray of points[] with indices between first and last.
     */
    private int partition(int first, int last) {
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (points[j].compareTo(points[last]) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, last);
        return i + 1;
    }
}
