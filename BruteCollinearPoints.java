public class BruteCollinearPoints {
    private LineSegment[] lines;

    private void Validate(Point[] points) { //Exception handling
        if (points == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i + 1] == null
                    || points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    public BruteCollinearPoints(Point[] points)  // finds all line segments containing 4 points
    {
        Validate(points);

        lines = new LineSegment[points.length / 4]; //Create line array
        int i = 0; //Iterator for lines array
        for (int p = 0; p < points.length; p++) { //p
            for (int q = p + 1; q < points.length; q++) { //q
                for (int r = q + 1; r < points.length; r++) { //r
                    for (int s = r + 1; s < points.length; s++) { //s
                        if (points[p].slopeTo(points[q]) == points[q].slopeTo(points[r]) &&
                                points[q].slopeTo(points[r]) == points[r].slopeTo(points[s])) {
                            lines[i++] = new LineSegment(points[p], points[s]);
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments()   // the number of line segments
    {
        return lines.length;
    }

    public LineSegment[] segments() // the line segments
    {
        return lines;
    }

    public static void main(String[] args) {

    }
}
