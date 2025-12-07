package gui.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Custom panel dengan rounded corners dan shadow
 */
public class ModernPanel extends JPanel {
    private int cornerRadius = 20;
    private int shadowSize = 5;
    private Color shadowColor = new Color(0, 0, 0, 50);

    public ModernPanel(Color bgColor, int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setBackground(bgColor);
        setOpaque(false);
    }

    public ModernPanel(Color bgColor) {
        setBackground(bgColor);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2.setColor(shadowColor);
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, 
                        getHeight() - shadowSize * 2, cornerRadius, cornerRadius);

        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, 
                        cornerRadius, cornerRadius);

        // Subtle border
        g2.setColor(new Color(200, 200, 200, 50));
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawRoundRect(0, 0, getWidth() - shadowSize - 1, getHeight() - shadowSize - 1, 
                        cornerRadius, cornerRadius);

        super.paintComponent(g);
    }
}
