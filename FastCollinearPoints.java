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

        lines = new LineSegment[points.length / 4];
        Arrays.sort(points, points[0].slopeOrder()); // Sort according to slope.

        int k = 0; //k = lines iterator
        for (int i = 0; i < points.length; i++) {
            int cnt = 1, j = i;
            while (j < points.length && points[j].slopeTo(points[j + 1])
                    == 0) { //Find collinear points in the Points array
                cnt++;
                j++;
            }

            i = j; //disqualifies duplicate lines
            if (cnt > 3) { //Line of 4 points or greater
                lines[k++] = new LineSegment(points[i], points[j]);
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

    }
}
