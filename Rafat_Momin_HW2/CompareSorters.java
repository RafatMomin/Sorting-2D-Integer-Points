/**
 * @author Rafat Momin
 */

package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;


public class CompareSorters {
    /**
     * Repeatedly take integer sequences either randomly generated or read from files.
     * Use them as coordinates to construct points.  Scan these points with respect to their
     * median coordinate point four times, each time using a different sorting algorithm.
     **/
    public static void main(String[] args) throws FileNotFoundException {
        PointScanner[] scanners = new PointScanner[4];
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
        int option, trail = 1;
        while (true) {
            System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
            option = scanner.nextInt();
            if (option == 3) {
                break;
            } else if (option == 1) {
                System.out.println("Trial " + trail + ": " + option);
                System.out.println("Enter number of random points: ");
                int randNumPoints = scanner.nextInt();
                randPoint(randNumPoints, random, scanners);
                trail++;
            } else if (option == 2) {
                System.out.println("Trial " + trail + ": " + option);
                System.out.println("Points From a file");
                System.out.println("File Name: ");
                String fileString = scanner.next();
                filePoint(fileString, scanners);
                scanners[3].writeMCPToFile();
                trail++;
            }

        }


    }


    /**
     * This method generates a given number of random points.
     * The coordinates of these points are pseudo-random numbers within the range
     * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
     * <p>
     * Ought to be private. Made public for testing.
     *
     * @param numPts number of points
     * @param rand   Random object to allow seeding of the random number generator
     * @throws IllegalArgumentException if numPts < 1
     */
    public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {

        if (numPts < 1) {
            throw new IllegalArgumentException();
        }
        Point[] randomPoints = new Point[numPts];
        for (int i = 0; i < numPts; i++) {
            Point point = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
            randomPoints[i] = point;
        }
        return randomPoints;
    }

    /**
     * this method fills the 4 scanners with the PointScanner constructor for random points
     * then runs scan and prints the stats.
     */
    public static void randPoint(int numPoints, Random rand, PointScanner[] scanners) {
        scanners[0] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.QuickSort);
        scanners[1] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.MergeSort);
        scanners[2] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.InsertionSort);
        scanners[3] = new PointScanner(generateRandomPoints(numPoints, rand), Algorithm.SelectionSort);

        System.out.println("algorithm   size  time (ns)");
        System.out.println("----------------------------------");
        for (int i = 0; i < 4; i++) {
            scanners[i].scan();
            System.out.println(scanners[i].stats());
        }
    }

    /**
     * this method fills the 4 scanner objects with the PointScanner constructor with the file version
     * constructor and then runs scan and prints the stats
     */
    public static void filePoint(String fileName, PointScanner[] scanners) throws FileNotFoundException {


        scanners[0] = new PointScanner(fileName, Algorithm.QuickSort);
        scanners[1] = new PointScanner(fileName, Algorithm.MergeSort);
        scanners[2] = new PointScanner(fileName, Algorithm.InsertionSort);
        scanners[3] = new PointScanner(fileName, Algorithm.SelectionSort);

        System.out.println("algorithm   size  time (ns)");
        System.out.println("----------------------------------");
        for (int i = 0; i < 4; i++) {
            scanners[i].scan();
            System.out.println(scanners[i].stats());
        }
    }

}
