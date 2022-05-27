import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class BruteCollinearPoints {
    private LinkedList<LineSegment> lines = new LinkedList<LineSegment>();
    private Point[] copy;

    private void copy(Point[] points) {
        copy = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            copy[i] = points[i];
    }

    private void NaturalOrderSort(Point[] points) {
        for (int i = 1; i < points.length; i++) {   //Sort according to natural order
            for (int j = i; j > 0 && points[j].compareTo(points[j - 1]) < 0; j--) {
                Point temp = points[j];
                points[j] = points[j - 1];
                points[j - 1] = temp;
            }
        }
    }

    private void Validate(Point[] points) { //Exception handling
        if (points == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    public BruteCollinearPoints(Point[] points)  // finds all line segments containing 4 points
    {
        Validate(points);

        copy(points);

        NaturalOrderSort(copy);

        for (int p = 0; p < copy.length; p++) { //p
            for (int q = p + 1; q < copy.length; q++) { //q
                for (int r = q + 1; r < copy.length; r++) { //r
                    for (int s = r + 1; s < copy.length; s++) { //s
                        if (copy[p].slopeTo(copy[q]) == copy[q].slopeTo(copy[r]) &&
                                copy[q].slopeTo(copy[r]) == copy[r].slopeTo(copy[s])) {
                            lines.add(new LineSegment(copy[p], copy[s]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments()   // the number of line segments
    {
        return lines.size();
    }

    public LineSegment[] segments() // the line segments
    {
        return lines.toArray(new LineSegment[lines.size()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
