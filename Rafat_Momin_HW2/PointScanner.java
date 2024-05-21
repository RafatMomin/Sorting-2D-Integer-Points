/**
 * @author Rafat Momin
 */
package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y
 * coordinates are respectively the medians of the x and y coordinates of the original points.
 * <p>
 * It records the employed sorting algorithm as well as the sorting time for comparison.
 */
public class PointScanner {
    private final Point[] points;
    private Point medianCoordinatePoint;
    private final Algorithm sortingAlgorithm;
    String fileName;
    protected long scanTime;

    /**
     * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy
     * the points into the array points[].
     *
     * @param pts input array of points
     * @throws IllegalArgumentException if pts == null or pts. Length == 0.
     */
    public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException();
        } else {
            points = pts;
            sortingAlgorithm = algo;
        }


    }


    /**
     * This constructor reads points from a file.
     *
     * @throws InputMismatchException if the input file contains an odd number of integers
     */
    protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
    {
        // TODO
        sortingAlgorithm = algo;
        fileName = inputFileName;
        int count = 0;

        try {
            File file = new File(inputFileName);
            Scanner scanner = new Scanner(file);


            int t;
            while (scanner.hasNext()) {
                t = scanner.nextInt();
                count++;
            }
            scanner.close();
            if (count % 2 != 0) {
                throw new InputMismatchException();
            }
            points = new Point[count / 2];
            Scanner scanner2 = new Scanner(file);

            int i = 0;
            while(scanner2.hasNext())
            {
                int x = scanner2.nextInt();
                int y = scanner2.nextInt();
                points[i] = new Point(x, y);
                i++;
            }
            scanner2.close();


        }


        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }




    }



    /**
     * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:
     * <p>
     * a) Sort points[] by the x-coordinate to get the median x-coordinate.
     * b) Sort points[] again by the y-coordinate to get the median y-coordinate.
     * c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.
     * <p>
     * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
     * or QuickSorter to carry out sorting.
     *
     */
    public void scan() {
        AbstractSorter aSorter = null;
        long startTime;
        long endTime;

        if (sortingAlgorithm == Algorithm.MergeSort) {
            aSorter = new MergeSorter(points);
        }
        if (sortingAlgorithm == Algorithm.InsertionSort) {
            aSorter = new InsertionSorter(points);
        }
        if (sortingAlgorithm == Algorithm.SelectionSort) {
            aSorter = new SelectionSorter(points);
        }
        if (sortingAlgorithm == Algorithm.QuickSort) {
            aSorter = new QuickSorter(points);
        }


        assert aSorter != null;
        aSorter.setComparator(0);
        startTime = System.nanoTime();
        aSorter.sort();
        endTime = System.nanoTime();
        long xTime = endTime - startTime;
        int x = aSorter.getMedian().getX();
        aSorter.setComparator(1);
        startTime = System.nanoTime();
        aSorter.sort();
        endTime = System.nanoTime();
        long yTime = endTime - startTime;
        scanTime = xTime + yTime;
        int y = aSorter.getMedian().getY();
        medianCoordinatePoint = new Point(x, y);
    }


    /**
     * Outputs performance statistics in the format:
     * <p>
     * <sorting algorithm> <size>  <time>
     * <p>
     * For instance,
     * <p>
     * selection sort   1000	  9200867
     * <p>
     * Use the spacing in the sample run in Section 2 of the project description.
     */
    public String stats() {
        String stats = null;


        if (sortingAlgorithm == Algorithm.MergeSort) {
            stats = "<merge sort>" + "         " + points.length + "  " + scanTime;
        }
        if (sortingAlgorithm == Algorithm.InsertionSort) {
            stats = "<insertion sort>" + "     " + points.length + "  " + scanTime;
        }
        if (sortingAlgorithm == Algorithm.SelectionSort) {
            stats = "<selection sort>" + "     " + points.length + "  " + scanTime;
        }
        if (sortingAlgorithm == Algorithm.QuickSort) {
            stats = "<quick sort>" + "         " + points.length + "  " + scanTime;
        }

        return stats;
    }


    /**
     * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space
     * in between.
     */
    @Override
    public String toString() {
        return medianCoordinatePoint.toString();
    }


    /**
     * This method, called after scanning, writes point data into a file by outputFileName. The format
     * of data in the file is the same as printed out from toString().  The file can help you verify
     * the full correctness of a sorting result and debug the underlying algorithm.
     *
     */
    public void writeMCPToFile() {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(toString());
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
