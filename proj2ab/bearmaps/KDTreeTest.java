package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class KDTreeTest {
    @Test
    /**test code with a given point list used in the lecture*/
    public void testNearest() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point target = new Point(0, 7);
        Point best = nn.nearest(target.getX(), target.getY());
        assertTrue(best.equals(p5));
    }

    /**set up random number generator*/
    private static Random r = new Random(500);

    /**create a single random point*/
    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    /**create N random points*/
    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }


    @Test
    public void testRandomNearest() {
        List<Point> points = randomPoints(1000);
        NaivePointSet nps  = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(200);
        for (Point p : queries) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }

    }
}
