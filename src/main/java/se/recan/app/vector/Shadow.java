package se.recan.app.vector;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * Created: 2010-05-05 Last Modified: 2010-06-10
 *
 * @author Anders Recksén (recan)
 */
public class Shadow {

    private final Graphics2D g2;
    private final Graphic graphic;


    protected Shadow(Graphics2D g2, Graphic graphic) {
        this.g2 = g2;
        this.graphic = graphic;
    }
        
    protected void render(Area area) {
        int step = (graphic.getShadowLightColor() - graphic.getShadowDarkColor()) / 4;

        int horizontalStep = graphic.getShadowOffsetX() / 5;
        int verticalStep = graphic.getShadowOffsetY() / 5;

        AffineTransform af = AffineTransform.getTranslateInstance(graphic.getShadowOffsetX(), graphic.getShadowOffsetY());
        g2.transform(af);
        g2.setColor(new Color(graphic.getShadowLightColor(), graphic.getShadowLightColor(), graphic.getShadowLightColor()));
        g2.fill(area);

        af = AffineTransform.getTranslateInstance(-horizontalStep, -verticalStep);
        g2.transform(af);
        g2.setColor(new Color(graphic.getShadowLightColor() - (1 * step), graphic.getShadowLightColor() - (1 * step), graphic.getShadowLightColor() - (1 * step)));
        g2.fill(area);

        af = AffineTransform.getTranslateInstance(-horizontalStep, -verticalStep);
        g2.transform(af);
        g2.setColor(new Color(graphic.getShadowLightColor() - (2 * step), graphic.getShadowLightColor() - (2 * step), graphic.getShadowLightColor() - (2 * step)));
        g2.fill(area);

        af = AffineTransform.getTranslateInstance(-horizontalStep, -verticalStep);
        g2.transform(af);
        g2.setColor(new Color(graphic.getShadowLightColor() - (3 * step), graphic.getShadowLightColor() - (3 * step), graphic.getShadowLightColor() - (3 * step)));
        g2.fill(area);

        af = AffineTransform.getTranslateInstance(-horizontalStep, -verticalStep);
        g2.transform(af);
        g2.setColor(new Color(graphic.getShadowLightColor() - (4 * step), graphic.getShadowLightColor() - (4 * step), graphic.getShadowLightColor() - (4 * step)));
        g2.fill(area);

        // Reset position to it's origin.
        af = AffineTransform.getTranslateInstance(-horizontalStep, -verticalStep);
        g2.transform(af);
    }
}
