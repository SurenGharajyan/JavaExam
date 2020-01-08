package com.suren.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

class PathListener extends MouseAdapter {
    Path2D path;
    private Point start;
    private Point stop;
    private Shape shape;
    private Main main;
    PathListener(Main main) {
        this.main = main;
        this.path = new Path2D.Double();
    }

    public Point getStop() {
        return stop;
    }

    public Point getStart() {
        return start;
    }

    public Shape getShape() {
        return shape;
    }

    public void mousePressed(MouseEvent event) {
        this.start = event.getPoint();
        this.shape = path;
    }

    public void mouseDragged(MouseEvent event) {
        stop = event.getPoint();
        path.moveTo(start.x, start.y);
        path.lineTo(stop.x, stop.y);
        shape = path;
        start = stop;
        main.repaint();
    }

    public void mouseReleased(MouseEvent event) {
        Path2D path = (Path2D) shape;
        System.out.println();
        shape = path;
        main.repaint();
    }
}