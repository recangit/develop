package se.recan.app.vector;

import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.geom.Area;
import java.awt.geom.AffineTransform;

/**
 *
 * Created: 2010-06-05 Last Modified: 2010-06-10
 *
 * @author Anders Recksén (recan)
 */
public class Emboss {

    private final Graphics2D g2;
    private final Graphic graphic;

    protected Emboss(Graphics2D g2, Graphic graphic) {
        this.g2 = g2;
        this.graphic = graphic;
    }

    protected void render(Area area) {
        render1(area);
        render2(area);
    }
    
    private void render1(Area area) {
        Area area1 = (Area) area.clone();

        AffineTransform af = AffineTransform.getTranslateInstance(graphic.getHorizontalEmboss(), graphic.getVerticalEmboss());
        Area area2 = area1.createTransformedArea(af);
        area1.subtract(area2);

        g2.setColor(graphic.getEmbossColor1());

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Graphic.ALPHA[graphic.getEmbossOpacity()]));
        g2.fill(area1);
        
        // Reset Alpha to it's origin.
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
    
    private void render2(Area area) {
        Area area1 = (Area) area.clone();

        AffineTransform af = AffineTransform.getTranslateInstance(-graphic.getHorizontalEmboss(), -graphic.getVerticalEmboss());
        Area area2 = area1.createTransformedArea(af);
        area1.subtract(area2);

        g2.setColor(graphic.getEmbossColor2());

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Graphic.ALPHA[graphic.getEmbossOpacity()]));
        g2.fill(area1);
        
        // Reset Alpha to it's origin.
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
