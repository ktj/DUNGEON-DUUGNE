package kayttoliittyma;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Ruutu extends JPanel {

    private char merkki;

    public Ruutu(char merkki) {
        this.merkki = merkki;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        super.paintComponents(g2);

        g2.setStroke(new BasicStroke(2));

        if (merkki == 'o') {
            g2.drawOval(0, 0, this.getWidth(), this.getHeight());
        } else if (merkki == 'x') {
            g2.drawLine(0, 0, this.getWidth(), this.getHeight());
            g2.drawLine(this.getWidth(), 0, 0, this.getHeight());
        } else {
            this.addMouseListener(null);
        }
    }
}
