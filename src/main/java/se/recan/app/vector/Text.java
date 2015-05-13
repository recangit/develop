package se.recan.app.vector;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * Created: 2010-05-05
 * Last Modified: 2010-06-10
 * @author Anders Recksén (anders[at]recksen[dot]se)
 */

public class Text implements Serializable {

    private final Graphics2D g2;
    private final Graphic graphic;

    protected Text(Graphics2D g2, Graphic graphic) {
        this.g2 = g2;
        this.graphic = graphic;
    }

    protected void render(Area background) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font(graphic.getFontFamily(), Font.PLAIN, graphic.getFontSize());
        GlyphVector glyph = font.createGlyphVector(g2.getFontRenderContext(), graphic.getTextValue());

        // Convert the String to a Area.
        Shape shape = glyph.getOutline();
        Area textArea = new Area(shape);

        // Place text area at correct place.
        g2.translate(getLeft(background, textArea), getTop(background, textArea));
        
        // Set color and transparency.
        g2.setColor(graphic.getTextColor());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Graphic.ALPHA[graphic.getTextOpacity()]));
        g2.fill(textArea);
        
        if(graphic.isTextEmboss()) {
            addEmboss(textArea);
        }
        
        if(graphic.getTextBorderSize() > 0) {
            addBorder(textArea);
        }
    }
    
    private void addEmboss(Area textArea) {
        // Clone text area. This area is the shadow at the left top.
        Area upperLeft = (Area)textArea.clone();

        // Define a movement. As this should simulate a light from the left top the movement will be to the right and to the bottom.
//        AffineTransform af = AffineTransform.getTranslateInstance(graphic.getTextEmboss(), graphic.getTextEmboss());
        AffineTransform af = AffineTransform.getTranslateInstance(graphic.getTextHorizontalEmboss(), graphic.getTextVerticalEmboss());

        // Whith this method it's possibly to make the move defined above and create a copy.
        // The Area upperLeft is still at it's original place.
        Area temporary = upperLeft.createTransformedArea(af);

        // Subtract the temporary Area from Area upperLeft.
        upperLeft.subtract(temporary);

        // Clone the text Area again. This Area is the highlighted part at bottom right.
        Area bottomRight = (Area)textArea.clone();
//        af = AffineTransform.getTranslateInstance(-graphic.getTextEmboss(), -graphic.getTextEmboss());
        af = AffineTransform.getTranslateInstance(-graphic.getTextHorizontalEmboss(), -graphic.getTextVerticalEmboss());
        temporary = bottomRight.createTransformedArea(af);
        bottomRight.subtract(temporary);

        // Area upperLeft and bottomRight is now intersecting. Therefore clone the
        // upperLeft Area and subtract it from bottomRight.
        temporary = (Area)upperLeft.clone();
        bottomRight.subtract(temporary);

        // We now have three Areas, the textArea, upperLeft and bottomRight.

        // Now fill the shadow part.
        
        
//        g2.setColor(Color.BLACK);
        g2.setColor(graphic.getTextEmbossColor1());
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Graphic.ALPHA[graphic.getEmbossOpacity()]));
        g2.fill(upperLeft);

        // And now the highlight part.
        
        g2.setColor(graphic.getTextEmbossColor2());
//        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Graphic.ALPHA[graphic.getEmbossOpacity()]));
        g2.fill(bottomRight);
    }
    
    private void addBorder(Area textArea) {
        // I don't want any transparency in the border.
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(graphic.getTextBorderSize()));
        g2.setColor(Color.BLACK);
        g2.draw(textArea);
    }

    private float getLeft(Area area, Area textArea) {
        Rectangle2D rectBounds = area.getBounds2D();
        Rectangle2D textBounds = textArea.getBounds2D();
        return (float)rectBounds.getX() + ((float)rectBounds.getWidth()/2) - ((float)textBounds.getWidth()/2);
    }

    private float getTop(Area area, Area textArea) {
        Rectangle2D rectBounds = area.getBounds2D();
        Rectangle2D textBounds = textArea.getBounds2D();
        return (float)rectBounds.getY() + ((float)rectBounds.getHeight()/2)+((float)textBounds.getHeight()/2);
    }
}