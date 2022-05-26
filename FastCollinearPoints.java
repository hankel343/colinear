import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] lines;

    private void NaturalSort(Point[] points) {
        for (int i = 1; i < points.length; i++) {   //Sort according to natural order
            for (int j = i; j > 0 && points[j].compareTo(points[j - 1]) < 0; j--) {
                Point temp = points[j];
                points[j] = points[j - 1];
                points[j - 1] = temp;
            }
        }
    }

    private void Validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i + 1] == null
                    || points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    public FastCollinearPoints(Point[] points) {
        Validate(points);

        lines = new LineSegment[points.length / 4];

        int n = 0;  //Iterator for lines array
        for (int i = 0; i < points.length; i++) {
            NaturalSort(points);
            Point pivot = points[i];
            Arrays.sort(points, pivot.slopeOrder());

            for (int j = 1; j < points.length - 1; j++) {
                double prevSlope = points[j - 1].slopeTo(points[j]);
                int cnt = 1, k = j + 1;
                int min = j - 1;
                while (k < points.length) {
                    if (points[k].slopeTo(points[k - 1]) == prevSlope) {
                        prevSlope = points[k].slopeTo(points[k - 1]);
                        cnt++;
                        k++;
                    }
                    else break;
                }

                if (cnt >= 3) {
                    if (points[min].compareTo(points[min + 1]) < 0)
                        lines[n++] = new LineSegment(points[min], points[k - 1]);
                }
                j = k - 1;
            }
        }
    }

    public int numberOfSegments() {        // the number of line segments
        return lines.length;
    }

    public LineSegment[] segments() {        // the line segments
        return lines;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            if (segment == null) break;
            segment.draw();
        }
        StdDraw.show();
    }
}
