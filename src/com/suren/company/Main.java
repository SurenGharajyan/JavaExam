package com.suren.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    PathListener listener;
    JTextField[] fields;
    int[] fieldValues;
    boolean showHideTriangle = true;
    boolean successButtonClicked = false;
    public static void main(String[] args) {
        Main shapes = new Main();

        JFrame frame = new JFrame("Qnnutyun");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(shapes);
        frame.pack();
        frame.setVisible(true);

        shapes.addFields();
    }

    private void addFields() {
        fields = new JTextField[6];
        fieldValues = new int[] {0,0,0,0,0,0};
        for (int i = 1; i <= 6; i++) {
            JTextField jTextField = new JTextField(5);
            Dimension dimension = jTextField.getPreferredSize();
            jTextField.setBounds(
                    ((i > 3 ? i - 3 : i) * dimension.width),
                    ((i > 3 ? 2 : 1) * dimension.height),
                    dimension.width,
                    dimension.height);
            fields[i-1] = jTextField;
            add(jTextField);

            infoLabels(i);
        }
        addButtons();
        repaint();
    }

    private void addButtons() {
        JButton btnSuccess = new JButton("Success");
        btnSuccess.addActionListener(e -> {
            successButtonClicked = true;
            int i = 0;
            for (JTextField tField: fields) {
                int val = tField.getText().isEmpty() ? 0 :Integer.parseInt(tField.getText());
                fieldValues[i++] = val;
            }
            repaint();
        });
        JButton btnShowHide = new JButton("Show/Hide");
        btnShowHide.addActionListener(e -> {
            showHideTriangle = !showHideTriangle;
            repaint();
        });
        btnSuccess.setBounds(240,7,120,30);
        btnShowHide.setBounds(240, 42, 120, 30);
        add(btnSuccess);
        add(btnShowHide);
    }

    private void infoLabels(int i) {
        if (i % 3 == 0) {
            JLabel label = new JLabel(i == 3 ? "x:" : "y:");
            label.setBounds(35, i * 6, 10, 20);
            this.add(label);
        }
    }

    Main() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(1280, 700));
        listener = new PathListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        Graphics2D g2 = (Graphics2D) gc;
        // triangle
        if (showHideTriangle) {
            g2.drawPolygon(
                    new int[]{fieldValues[0], fieldValues[1], fieldValues[2]},
                    new int[]{fieldValues[3], fieldValues[4], fieldValues[5]},
                    3
            );
        }
        // triangle
        if (listener.getStart() != null && listener.getStop() != null) {
            BasicStroke stroke = new BasicStroke(1);
            Shape strokedShape = stroke.createStrokedShape(listener.getShape());
            g2.draw(strokedShape);
            g2.fill(strokedShape);
        }
    }
}