package se.recan.app.vector;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import org.apache.log4j.Logger;

/**
 *
 * 2014-aug-14
 * @author Anders Recksén (recan)
 */
public class Picture {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    private final Graphics2D g2;
    private final Graphic graphic;
    
    protected Picture(Graphics2D g2, Graphic graphic) {
        this.g2 = g2;
        this.graphic = graphic;
    }
    
    /**
     * Rita ut en Area i ordningen: yttre ram, Area, inre ram
     *
     * @param area
     * @throws Exception
     */
    protected void render(Area area) throws Exception {
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        if (graphic.getPictOuterBorder() > 0) {
            g2.setStroke(new BasicStroke(graphic.getPictOuterBorder()));
            g2.setPaint(graphic.getPictOuterBorderColor());

            g2.draw(area);
        }

        if (graphic.isGradient()) {
            GradientPaint grad = null;
            
            // Vi tillåter inte graphic.getGradientSteps() att vara 0 eftersom division med 0 kastar ett exception.
            int steps = graphic.getGradientSteps() > 0? graphic.getGradientSteps(): 1;
            if (graphic.getGradientOrientation() == Graphic.VERTICAL) {
                grad = new GradientPaint(0, 0, graphic.getPictGradientStartColor(), 0, graphic.getPictHeight()/steps, graphic.getPictGradientStopColor(), true);
            } else if (graphic.getGradientOrientation() == Graphic.HORIZONTAL) {
                grad = new GradientPaint(0, 0, graphic.getPictGradientStartColor(), graphic.getPictWidth()/steps, 0, graphic.getPictGradientStopColor(), true);
            }
            g2.setPaint(grad);
        } else {
            g2.setPaint(graphic.getPictGradientStartColor());
        }

        g2.fill(area);

        if (graphic.getPictInnerBorder() > 0) {
            g2.setStroke(new BasicStroke(graphic.getPictInnerBorder()));
            g2.setPaint(graphic.getPictInnerBorderColor());

            g2.draw(area);
        }
    }

    /**
     * Skapa den Area som innehåller bilden och dess placering. Lägg märke till
     * att denna metod inte ritar ut bilden.
     *
     * @return 
     */
    public Area createArea() {
        Area area = new Area();
        
        // Rita cirklar
        RoundRectangle2D left_top = new RoundRectangle2D.Double(
                0,
                0,
                graphic.getPictCorner1(), graphic.getPictCorner1(), graphic.getPictCorner1(), graphic.getPictCorner1());

        RoundRectangle2D right_top = new RoundRectangle2D.Double(
                graphic.getPictWidth() - graphic.getPictCorner2(),
                0,
                graphic.getPictCorner2(), graphic.getPictCorner2(), graphic.getPictCorner2(), graphic.getPictCorner2());

        RoundRectangle2D right_bottom = new RoundRectangle2D.Double(
                graphic.getPictWidth() - graphic.getPictCorner3(),
                graphic.getPictHeight() - graphic.getPictCorner3(),
                graphic.getPictCorner3(), graphic.getPictCorner3(), graphic.getPictCorner3(), graphic.getPictCorner3());

        RoundRectangle2D left_bottom = new RoundRectangle2D.Double(
                0,
                graphic.getPictHeight() - graphic.getPictCorner4(),
                graphic.getPictCorner4(), graphic.getPictCorner4(), graphic.getPictCorner4(), graphic.getPictCorner4());

        // Streck som knyter ihop cirklarna
        GeneralPath path = new GeneralPath();
        path.moveTo((int) left_top.getX() + ((int) left_top.getWidth() / 2), 0);
        path.lineTo((int) right_top.getX() + ((int) right_top.getWidth() / 2), 0);
        path.lineTo((int) right_top.getX() + ((int) right_top.getWidth()), (int) right_top.getY() + ((int) right_top.getHeight() / 2));
        path.lineTo((int) right_bottom.getX() + ((int) right_bottom.getWidth()), (int) right_bottom.getY() + ((int) right_bottom.getHeight() / 2));
        path.lineTo((int) right_bottom.getX() + ((int) right_bottom.getWidth() / 2), (int) right_bottom.getY() + ((int) right_bottom.getHeight()));
        path.lineTo((int) left_bottom.getX() + ((int) left_bottom.getWidth() / 2), (int) left_bottom.getY() + ((int) left_bottom.getHeight()));
        path.lineTo((int) left_bottom.getX(), (int) left_bottom.getY() + ((int) left_bottom.getHeight() / 2));
        path.lineTo((int) left_top.getX(), (int) left_top.getY() + ((int) left_top.getHeight() / 2));
        path.closePath();

        area.add(new Area(left_top));
        area.add(new Area(right_top));
        area.add(new Area(right_bottom));
        area.add(new Area(left_bottom));
        area.add(new Area(path));

        // Placering av bild, dvs flytta Area
        int left = graphic.getPictX();
        int top = graphic.getPictY();

        if (graphic.getPictOuterBorder() > 0) {
            left = graphic.getPictX() + (graphic.getPictOuterBorder() / 2);
            top = graphic.getPictY() + (graphic.getPictOuterBorder() / 2);
        } else if (graphic.getPictInnerBorder() > 0) {
            left = graphic.getPictX() + (graphic.getPictInnerBorder() / 2);
            top = graphic.getPictY() + (graphic.getPictInnerBorder() / 2);
        }

        AffineTransform af = AffineTransform.getTranslateInstance(left, top);
        area.transform(af);
        
        return area;
    }
}
