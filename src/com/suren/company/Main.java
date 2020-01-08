package com.suren.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    PathListener listener;

    public static void main(String[] args) {
        Main shapes = new Main();

        JFrame frame = new JFrame("Qnnutyun");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(shapes);
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(600, 600));
        listener = new PathListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        Graphics2D g2 = (Graphics2D) gc;
        // triangle
        g2.drawPolygon(new int[] {100, 80, 50}, new int[] {100, 400, 10}, 3);
        // triangle
        if (listener.getStart() != null && listener.getStop() != null) {
            BasicStroke stroke = new BasicStroke(1);
            Shape strokedShape = stroke.createStrokedShape(listener.getShape());
            g2.draw(strokedShape);
            g2.fill(strokedShape);
        }
    }
}