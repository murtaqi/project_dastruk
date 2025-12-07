package gui.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Custom text field dengan rounded style
 */
public class ModernTextField extends JTextField {
    private int cornerRadius = 10;
    private Color focusColor = new Color(65, 0, 155);
    private Color normalColor = new Color(153, 51, 255);
    private boolean isFocused = false;

    public ModernTextField(int columns) {
        super(columns);
        setOpaque(false);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setForeground(new Color(50, 50, 50));
        setCaretColor(new Color(65, 0, 155));
        setMargin(new Insets(8, 12, 8, 12));
        
        // Focus listener
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                isFocused = true;
                repaint();
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                isFocused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Border dengan focus effect
        Color borderColor = isFocused ? focusColor : normalColor;
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Border sudah di-paint di paintComponent
    }
}
