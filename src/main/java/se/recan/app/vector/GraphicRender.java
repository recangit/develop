package se.recan.app.vector;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import org.apache.log4j.Logger;

/**
 * Created: 2010-04-02 Last Modified: 2014-08-15
 *
 * @author Anders Recksén (anders[at]recksen[dot]se)
 */
public class GraphicRender {

    private Graphics2D g2;
    private final Graphic graphic;

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public GraphicRender(Graphic graphic) {
        this.graphic = graphic;
    }

    public BufferedImage render() throws Exception {
    
        BufferedImage bufferedImage = new BufferedImage(graphic.getCanvasWidth(), graphic.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
        this.g2 = bufferedImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background.
        g2.setColor(graphic.getCanvasColor());
        g2.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        
        if (graphic.isGrid()) {
            drawGrid(bufferedImage, new Color(220, 220, 220), 50);
            drawGrid(bufferedImage, new Color(180, 180, 180), 100);
        }
        
        // Måste ha en Area att jobba med.
        // Bygg upp bilden men rendera den inte.
        Picture picture = new Picture(g2, graphic);
        Area area = picture.createArea();

        // Rendera först skuggan...
        if (graphic.isShadow()) {
            Shadow shadow = new Shadow(g2, graphic);
            shadow.render(area);
        }

        // ...sedan bilden
        picture.render(area);

        if (graphic.isEmboss()) {
            Emboss emboss = new Emboss(g2, graphic);
            emboss.render(area);
        }

        if (graphic.isText()) {
            Text txt = new Text(g2, graphic);
            txt.render(area);
        }

        return bufferedImage;
    }
    
    public void drawGrid(BufferedImage bufferedImage, Color color, int grid) {
        g2.setColor(color);

        for (int i = grid; i <= bufferedImage.getHeight(); i += grid) {
            g2.drawLine(0, i, bufferedImage.getWidth(), i);
        }

        for (int i = grid; i <= bufferedImage.getWidth(); i += grid) {
            g2.drawLine(i, 0, i, bufferedImage.getHeight());
        }
    }
}
