package kayttoliittyma;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Ruutu extends JPanel {

    private char merkki;
    public final int x;
    public final int y;

    public Ruutu(char merkki, int x, int y) {
        this.merkki = merkki;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;


        super.paintComponents(g2);
        g2.setStroke(new BasicStroke(2));

        if (merkki == 'o') {
            g2.drawOval(3, 3, this.getWidth() - 6, this.getHeight() - 6);
        } else if (merkki == 'x') {
            g2.drawLine(3, 3, this.getWidth() - 3, this.getHeight() - 3);
            g2.drawLine(this.getWidth() - 3, 3, 3, this.getHeight() - 3);
        } else {
            this.addMouseListener(null);
        }
    }
}
