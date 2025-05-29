package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PointPanel extends JPanel {
    private List<Point> points;
    private List<Point> path = new ArrayList<>();
    private BufferedImage mazeImage;

    private final int GRID_WIDTH = 30;
    private final int GRID_HEIGHT = 30;
    private final int RECT_SIZE = 20;
    private boolean pathNotFound = false;

    public PointPanel(List<Point> points) {
        this.points = points;
        setPreferredSize(new Dimension(GRID_WIDTH * RECT_SIZE, GRID_HEIGHT * RECT_SIZE));
        createImage();

        JButton checkButton = new JButton("בדוק פתרון");
        checkButton.addActionListener((ActionEvent e) -> {
            path = MazeSolver.findPath(points, GRID_WIDTH, GRID_HEIGHT);
            pathNotFound = (path == null);
            if (path == null) path = new ArrayList<>();
            repaint();
        });
        this.add(checkButton);
    }

    private void createImage() {
        this.mazeImage = new BufferedImage(GRID_WIDTH * RECT_SIZE, GRID_HEIGHT * RECT_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = mazeImage.createGraphics();

        for (Point p : this.points) {
            int x = p.getX() * RECT_SIZE;
            int y = p.getY() * RECT_SIZE;

            if (p.isWhite()) {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(x, y, RECT_SIZE, RECT_SIZE);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawRect(x, y, RECT_SIZE, RECT_SIZE);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(x, y, RECT_SIZE, RECT_SIZE);
            }
        }
        g2d.dispose();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.mazeImage, 0, 0, null);

        g.setColor(Color.GREEN);
        for (Point p : path) {
            int x = p.getX() * RECT_SIZE + RECT_SIZE / 2;
            int y = p.getY() * RECT_SIZE + RECT_SIZE / 2;
            g.fillOval(x - 3, y - 3, 6, 6);
        }

        if (pathNotFound) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("NO PATH FOUND", 20, 30);
        }
    }
}



