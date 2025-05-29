package org.example;

import java.util.*;

public class MazeSolver {

    // מתודה ראשית
    public static List<Point> findPath(List<Point> points, int width, int height) {
        Set<String> visited = new HashSet<>(); // נקודות שנבדקו
        Map<String, Point> cameFrom = new HashMap<>(); // מעקב אחרי כיוון

        Queue<Point> queue = new LinkedList<>();
        Point start = findPoint(points, 0, 0); // נקודת התחלה
        Point end = findPoint(points, width - 1, height - 1); // נקודת סיום

        if (start == null || end == null || !start.isWhite() || !end.isWhite()) {
            return null;
        }

        queue.add(start); // מוסיפים התחלה לתור
        visited.add(key(start.getX(), start.getY())); // מסמנים כנבדק

        // ארבעת כיווני התנועה האפשריים
        List<Point> directions = List.of(
                new Point(0, 1, true),
                new Point(1, 0, true),
                new Point(0, -1, true),
                new Point(-1, 0, true)
        );

        while (!queue.isEmpty()) {
            Point current = queue.poll(); // שולפים נקודה מהתור
            int x = current.getX();
            int y = current.getY();

            if (x == end.getX() && y == end.getY()) {
                return buildPath(cameFrom, end); // מצאנו את הסיום
            }

            for (Point dir : directions) {
                int neighborX = x + dir.getX();
                int neighborY = y + dir.getY();
                String neighborKey = key(neighborX, neighborY);

                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    Point neighbor = findPoint(points, neighborX, neighborY); // מציאת שכן
                    if (neighbor != null && neighbor.isWhite() && !visited.contains(neighborKey)) {
                        visited.add(neighborKey); // מסמנים שנבדק
                        cameFrom.put(neighborKey, current); // שומרים מאיפה באנו
                        queue.add(neighbor); // מוסיפים לתור
                    }
                }
            }
        }

        return null; // אין פתרון
    }

    // מפתח ייחודי לנקודה
    private static String key(int x, int y) {
        return x + "," + y;
    }

    // מחזיר נקודה לפי קואורדינטות
    private static Point findPoint(List<Point> points, int x, int y) {
        for (Point p : points) {
            if (p.getX() == x && p.getY() == y) return p;
        }
        return null;
    }

    // בניית המסלול לאחור
    private static List<Point> buildPath(Map<String, Point> cameFrom, Point end) {
        List<Point> path = new ArrayList<>();
        Point current = end;
        while (current != null) {
            path.add(current);
            current = cameFrom.get(key(current.getX(), current.getY()));
        }
        Collections.reverse(path);
        return path;
    }
}




