import java.awt.*;

public class Plot {
    Graphics g;
    int x, y;
    Color color = Color.white;

    Plot(Graphics g,int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.g = g;
        g.setColor(color);
        g.fillRect(x, y, 55, 55);
    }

    void setColor(Color c) {
        g.setColor(c);
    }


}
