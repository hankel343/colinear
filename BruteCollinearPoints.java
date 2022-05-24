public class BruteCollinearPoints {
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points)  // finds all line segments containing 4 points
    {
        /* Exception handling */
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) { //Check for null elements
            if (points[i] == null)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length - 1; i++) { //Check for the same points
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        /* Algorithm */
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
