package gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom rounded button dengan hover effect
 */
public class RoundedButton extends JButton {
    private int radius = 15;
    private Color hoverColor;
    private Color defaultColor;
    private boolean isHovered = false;

    public RoundedButton(String text, Color defaultColor, Color hoverColor) {
        super(text);
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        setDefaultColor(defaultColor);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
        });
    }

    public void setDefaultColor(Color color) {
        this.defaultColor = color;
        setBackground(color);
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Shadow effect
        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        
        // Background dengan hover
        Color bgColor = isHovered ? hoverColor : defaultColor;
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        
        // Border
        g2.setColor(new Color(255, 255, 255, 50));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Custom border sudah di paintComponent
    }
}
