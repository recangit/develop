package se.recan.app.vector;

import java.awt.Color;
import java.io.Serializable;

/**
 * Created: 2010-04-02 Last Modified: 2010-06-10
 *
 * @author Anders Recksén (anders[at]recksen[dot]se)
 */
public class Graphic implements Serializable {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public static final float[] ALPHA = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};

    private String canvasColor = "235,235,235";
    private int canvasWidth = 800;
    private int canvasHeight = 500;

    private boolean grid = false;
    
    private int pictX = 50;
    private int pictY = 50;
    private int pictWidth = 0;
    private int pictHeight = 0;
    private int pictCorner1 = 0;
    private int pictCorner2 = 0;
    private int pictCorner3 = 0;
    private int pictCorner4 = 0;
    private int pictOuterBorder = 0;
    private int pictInnerBorder = 0;

    private String pictOuterBorderColor = "29, 137, 224";
    private String pictInnerBorderColor = "224, 55, 29";
    private boolean gradient = false;
    private int gradientSteps = 0;
    private int gradientOrientation = VERTICAL;
    private String pictGradientStartColor = "0,0,0";
    private String pictGradientStopColor = "255,255,255";

    // ?
    private boolean transparent = false;

    private boolean emboss = false;
    private int horizontalEmboss = 5;
    private int verticalEmboss = 10;
    private int embossOpacity;
    private String embossColor1 = "29, 137, 224";
    private String embossColor2 = "29, 137, 224";

    private boolean shadow = false;
    private int shadowOffsetX = 5;
    private int shadowOffsetY = 5;
    private int shadowDarkColor = 75;
    private int shadowLightColor = 0;

    private boolean text = false;
    private String textValue = "";
    private int fontSize = 0;
    private String fontFamily = "";
    private String textColor = "";
    private int textOpacity = 0;
    private int textBorderSize = 0;
    private boolean textEmboss = false;
    private int textHorizontalEmboss = 5;
    private int textVerticalEmboss = 10;
    private String textEmbossColor1 = "0,0,0";
    private String textEmbossColor2 = "0,0,0";

    public boolean isGrid() {
        return grid;
    }

    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public int getPictX() {
        return pictX;
    }

    public void setPictX(int pictX) {
        this.pictX = pictX;
    }

    public int getPictY() {
        return pictY;
    }

    public void setPictY(int pictY) {
        this.pictY = pictY;
    }

    public int getPictWidth() {
        return pictWidth;
    }

    public void setPictWidth(int pictWidth) {
        this.pictWidth = pictWidth;
    }

    public int getPictHeight() {
        return pictHeight;
    }

    public void setPictHeight(int pictHeight) {
        this.pictHeight = pictHeight;
    }

    public int getPictCorner1() {
        return pictCorner1;
    }

    public void setPictCorner1(int pictCorner1) {
        this.pictCorner1 = pictCorner1;
    }

    public int getPictCorner2() {
        return pictCorner2;
    }

    public void setPictCorner2(int pictCorner2) {
        this.pictCorner2 = pictCorner2;
    }

    public int getPictCorner3() {
        return pictCorner3;
    }

    public void setPictCorner3(int pictCorner3) {
        this.pictCorner3 = pictCorner3;
    }

    public int getPictCorner4() {
        return pictCorner4;
    }

    public void setPictCorner4(int pictCorner4) {
        this.pictCorner4 = pictCorner4;
    }

    public int getPictOuterBorder() {
        return pictOuterBorder;
    }

    public void setPictOuterBorder(int pictOuterBorder) {
        this.pictOuterBorder = pictOuterBorder;
    }

    public int getPictInnerBorder() {
        return pictInnerBorder;
    }

    public void setPictInnerBorder(int pictInnerBorder) {
        this.pictInnerBorder = pictInnerBorder;
    }

    public void setCanvasColor(String canvasColor) {
        this.canvasColor = canvasColor;
    }

    public Color getCanvasColor() {
        try {
            String[] split = canvasColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getCanvasColorToString() {
        Color color = getCanvasColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasWidth() {
        if (canvasWidth > 0) {
            return canvasWidth;
        } else {
            if (pictOuterBorder > 0) {
                return pictX + (pictOuterBorder) + pictWidth;
            } else {
                return pictX + (pictInnerBorder) + pictWidth;
            }
        }
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public int getCanvasHeight() {
        if (canvasHeight > 0) {
            return canvasHeight;
        } else {
            if (pictOuterBorder > 0) {
                return pictY + (pictOuterBorder) + pictHeight;
            } else {
                return pictY + (pictInnerBorder) + pictHeight;
            }
        }
    }

//    public void setPictInnerColor(String innerColor) {
//        this.pictInnerColor = innerColor;
//    }
//
//    public Color getPictInnerColor() {
//        try {
//            String[] split = pictInnerColor.split(",");
//            int red = Integer.parseInt(split[0].trim());
//            int green = Integer.parseInt(split[1].trim());
//            int blue = Integer.parseInt(split[2].trim());
//            return new Color(red, green, blue);
//        } catch (NumberFormatException e) {
//            return new Color(255, 255, 255);
//        }
//    }

//    public String getPictInnerColorToString() {
//        Color color = getPictInnerColor();
//        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
//    }

    public void setPictOuterBorderColor(String pictOuterBorderColor) {
        this.pictOuterBorderColor = pictOuterBorderColor;
    }

    public Color getPictOuterBorderColor() {
        try {
            String[] split = pictOuterBorderColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getPictOuterBorderColorToString() {
        Color color = getPictOuterBorderColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setPictInnerBorderColor(String pictInnerBorderColor) {
        this.pictInnerBorderColor = pictInnerBorderColor;
    }

    public Color getPictInnerBorderColor() {
        try {
            String[] split = pictInnerBorderColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getPictInnerBorderColorToString() {
        Color color = getPictInnerBorderColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setPictGradientStartColor(String pictGradientStartColor) {
        this.pictGradientStartColor = pictGradientStartColor;
    }

    public Color getPictGradientStartColor() {
        try {
            String[] split = pictGradientStartColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getPictGradientStartColorToString() {
        Color color = getPictGradientStartColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setPictGradientStopColor(String pictGradientStopColor) {
        this.pictGradientStopColor = pictGradientStopColor;
    }

    public Color getPictGradientStopColor() {
        try {
            String[] split = pictGradientStopColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getPictGradientStopColorToString() {
        Color color = getPictGradientStopColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setGradient(boolean gradient) {
        this.gradient = gradient;
    }

    public boolean isGradient() {
        return gradient;
    }

    public int getGradientSteps() {
        return gradientSteps;
    }

    public void setGradientSteps(int gradientSteps) {
        this.gradientSteps = gradientSteps;
    }
    
    public void setGradientOrientation(int gradientOrientation) {
        this.gradientOrientation = gradientOrientation;
    }

    public int getGradientOrientation() {
        return gradientOrientation;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isEmboss() {
        return emboss;
    }

    public void setEmboss(boolean emboss) {
        this.emboss = emboss;
    }

    public void setHorizontalEmboss(int horizontalEmboss) {
        this.horizontalEmboss = horizontalEmboss;
    }

    public int getHorizontalEmboss() {
        return horizontalEmboss;
    }

    public void setVerticalEmboss(int verticalEmboss) {
        this.verticalEmboss = verticalEmboss;
    }

    public int getVerticalEmboss() {
        return verticalEmboss;
    }

    public void setEmbossOpacity(int embossOpacity) {
        this.embossOpacity = embossOpacity;
    }

    public int getEmbossOpacity() {
        return embossOpacity;
    }

    public void setEmbossColor1(String embossColor1) {
        this.embossColor1 = embossColor1;
    }

    public Color getEmbossColor1() {
        try {
            String[] split = embossColor1.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getEmbossColor1ToString() {
        Color color = getEmbossColor1();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setEmbossColor2(String embossColor2) {
        this.embossColor2 = embossColor2;
    }

    public Color getEmbossColor2() {
        try {
            String[] split = embossColor2.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getEmbossColor2ToString() {
        Color color = getEmbossColor2();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Color getTextColor() {
        try {
            String[] split = textColor.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }

    public String getTextColorToString() {
        Color color = getTextColor();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setTextOpacity(int textOpacity) {
        this.textOpacity = textOpacity;
    }

    public int getTextOpacity() {
        return textOpacity;
    }

    public void setTextBorderSize(int textBorderSize) {
        this.textBorderSize = textBorderSize;
    }

    public int getTextBorderSize() {
        return textBorderSize;
    }

    public void setTextEmboss(boolean textEmboss) {
        this.textEmboss = textEmboss;
    }

    public boolean isTextEmboss() {
        return textEmboss;
    }

    public int getTextHorizontalEmboss() {
        return textHorizontalEmboss;
    }

    public void setTextHorizontalEmboss(int textHorizontalEmboss) {
        this.textHorizontalEmboss = textHorizontalEmboss;
    }

    public int getTextVerticalEmboss() {
        return textVerticalEmboss;
    }

    public void setTextVerticalEmboss(int textVerticalEmboss) {
        this.textVerticalEmboss = textVerticalEmboss;
    }

    public Color getTextEmbossColor1() {
        try {
            String[] split = textEmbossColor1.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }
    
    public String getTextEmbossColor1ToString() {
        Color color = getTextEmbossColor1();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setTextEmbossColor1(String textEmbossColor1) {
        this.textEmbossColor1 = textEmbossColor1;
    }

    public Color getTextEmbossColor2() {
        try {
            String[] split = textEmbossColor2.split(",");
            int red = Integer.parseInt(split[0].trim());
            int green = Integer.parseInt(split[1].trim());
            int blue = Integer.parseInt(split[2].trim());
            return new Color(red, green, blue);
        } catch (NumberFormatException e) {
            return new Color(255, 255, 255);
        }
    }
    
    public String getTextEmbossColor2ToString() {
        Color color = getTextEmbossColor2();
        return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
    }

    public void setTextEmbossColor2(String textEmbossColor2) {
        this.textEmbossColor2 = textEmbossColor2;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public int getShadowOffsetY() {
        return shadowOffsetY;
    }

    public void setShadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
    }

    public int getShadowDarkColor() {
        return shadowDarkColor;
    }

    public void setShadowDarkColor(int shadowDarkColor) {
        this.shadowDarkColor = shadowDarkColor;
    }

    public int getShadowLightColor() {
        return shadowLightColor;
    }
    
    public void setShadowLightColor(int shadowLightColor) {
        this.shadowLightColor = shadowLightColor;
    }
}
