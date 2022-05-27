import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {

    private LinkedList<LineSegment> lines = new LinkedList<LineSegment>();

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

        for (int i = 0; i < points.length; i++) {
            NaturalSort(points);
            Point pivot = points[i];
            Arrays.sort(points, pivot.slopeOrder());

            for (int p = 0; p < points.length; p++) {
                for (int q = p + 1; q < points.length - 1; q++) {
                    if (Double
                            .compare(points[p].slopeTo(points[q]), points[q].slopeTo(points[q + 1]))
                            == 0) {
                        double slope = points[q].slopeTo(points[q + 1]);
                        int r = q + 1, cnt = 1;
                        while (r < points.length && points[q].slopeTo(points[r]) == slope) {
                            cnt++;
                            r++;
                        }

                        if (cnt >= 3) {
                            if (points[p].compareTo(points[q]) < 0)
                                lines.add(new LineSegment(points[p], points[r - 1]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {        // the number of line segments
        return lines.size();
    }

    public LineSegment[] segments() {        // the line segments
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            if (segment == null) break;
            segment.draw();
        }
        StdDraw.show();
    }
}
