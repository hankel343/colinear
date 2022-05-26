import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] lines;

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

        Point p = points[0]; //Reference point (i.e. origin)
        lines = new LineSegment[points.length / 4];
        Arrays.sort(points, p.slopeOrder()); // Sort according to slope

        int k = 0; //k = lines iterator
        for (int i = 0; i < points.length - 1; i++) {
            int cnt = 1, j = i;
            while (j < points.length && p.slopeTo(points[j]) == p.slopeTo(points[j + 1])) {
                cnt++;
                j++;
            }

            if (cnt >= 3) { //Line of 4 points or greater
                lines[k++] = new LineSegment(points[i], points[j]);
            }
            i = j; //disqualifies duplicate lines
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
