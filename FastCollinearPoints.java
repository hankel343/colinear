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
        
    }

    public int numberOfSegments() {        // the number of line segments

    }

    public LineSegment[] segments() {        // the line segments

    }

    public static void main(String[] args) {

    }
}
