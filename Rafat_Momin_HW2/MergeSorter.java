/**
 * @author Rafat Momin
 */

package edu.iastate.cs228.hw2;

/**
 * This class implements the mergesort algorithm.
 */

public class MergeSorter extends AbstractSorter {

    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     *
     * @param pts input array of integers
     */
    public MergeSorter(Point[] pts) {
        super(pts);
        super.algorithm = "mergesort";

    }

    /**
     * Perform mergesort on the array points[] of the parent class AbstractSorter.
     */
    @Override
    public void sort() {
        mergeSortRec(points);
    }


    /**
     * This is a recursive method that carries out mergesort on an array pts[] of points. One
     * way is to make copies of the two halves of pts[], recursively call mergeSort on them,
     * and merge the two sorted arrays into pts[].
     *
     * @param pts point array
     */
    private void mergeSortRec(Point[] pts) {
        int n = pts.length;
        if (n <= 1) {
            return;
        }
        int m = n / 2;
        Point[] ptsLeft = new Point[m];
        Point[] ptsRight = new Point[n - m];
        int i = 0;
        for (int j = 0; j < ptsLeft.length; j++) {
            ptsLeft[j] = pts[i];
            i++;
        }
        for (int j = 0; j < ptsRight.length; j++) {
            ptsRight[j] = pts[i];
            i++;
        }
        mergeSortRec(ptsLeft);
        mergeSortRec(ptsRight);
        merge(pts, ptsLeft, ptsRight);
    }


    /**
     * @param points      points
     * @param leftPoints  left points
     * @param rightPoints merges arrays together from sudo code pdf
     */

    private void merge(Point[] points, Point[] leftPoints, Point[] rightPoints) {
        int i = 0, j = 0, k = 0, leftPointsArrayLength = leftPoints.length, rightPointsArrayLength = rightPoints.length;
        while (i < leftPointsArrayLength && j < rightPointsArrayLength) {
            if (leftPoints[i].compareTo(rightPoints[j]) < 0) {
                points[k] = leftPoints[i];
                i++;
            } else {
                points[k] = rightPoints[j];
                j++;
            }
            k++;
        }
        while (j < rightPointsArrayLength) {
            points[k] = rightPoints[j];
            j++;
            k++;
        }
        while (i < leftPointsArrayLength) {
            points[k] = leftPoints[i];
            i++;
            k++;
        }
    }
}
