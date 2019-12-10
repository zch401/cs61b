package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    public Node root;
    private class Node {
        private Point p;
        private Node left;
        private Node right;
        private int depth; //indicates which component to compare, even number compare x, odd number compare y

        Node(Point p0, int i) {
            p = p0;
            depth = i;
        }
    }

    /**
     *
     add points to the node
     */
    public Node add(Point p, Node n, int d) {
        int depth = d;
        if (n == null) {
            return new Node(p, depth);
        }
        if (p.equals(n.p)) {
            return n;
        }

        if (depth%2 == 0) { //if depth is even number then compare x
            if (p.getX() < n.p.getX()) {
                n.left = add(p, n.left, depth + 1);
            } else {
                n.right = add(p, n.right, depth + 1);
            }
        }

        if (depth%2 == 1) { //if depth is odd number then compare y
            if (p.getY() < n.p.getY()) {
                n.left = add(p, n.left, depth + 1);
            } else {
                n.right = add(p, n.right, depth + 1);
            }
        }
        return n;
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, 0);
        }
    }

    @Override
    /**return the nearest point in the list to the target*/
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearest(root, goal, root.p);
    }

    /**helper to calculate nearest*/
    private Point nearest(Node n, Point goal, Point best) {
        if (n == null) {
            return best;
        }

        if (Point.distance(goal, n.p) < Point.distance(goal, best)) {
            best = n.p;
        }
        Node goodSide;
        Node badSide;


        /**define badSide and goodSide by comparing the corresponding component for that depth*/
        if (n.depth%2 == 0) { //check good and bad side by comparing x component
            if (n.p.getX() < goal.getX()) {
                goodSide = n.right;
                badSide = n.left;
            } else {
                goodSide = n.left;
                badSide = n.right;
            }
        } else {
            if (n.p.getY() < goal.getY()) { //check good and bad side by comparing y component
                goodSide = n.right;
                badSide = n.left;
            } else {
                goodSide = n.left;
                badSide = n.right;
            }
        }
        best = nearest(goodSide, goal, best);
        Point bestPossible;

        /**pruning process, find the bestPossible point on the badSide, if the distance between
         * bestPossible and goal is smaller than the current best, there is no need to calculate
         * the badSide*/
        if (badSide != null) { //if there is a badSide
            if (n.depth%2 == 1) {
                bestPossible = new Point(goal.getX(), n.p.getY());
            } else {
                bestPossible = new Point(n.p.getX(), goal.getY());
            }
            if (Point.distance(bestPossible, goal) < Point.distance(best, goal)) {
                best = nearest(badSide, goal, best);
            }
        }
        //best = nearest(badSide, goal, best);
        return best;
    }


}
