package se.recan.app.graphic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.log4j.Logger;
import org.junit.Test;
import se.recan.app.Helper;
import se.recan.app.vector.Graphic;
import se.recan.app.vector.GraphicRender;
import se.recan.utils.EntityUtil;

/**
 * @date 2014-maj-18
 * @author Anders Recksén (recan)
 */
public class GraphicTest extends JFrame {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    @Test
    public void testGraphic1() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("canvasWidth:800");
        list.add("canvasHeight:500");
        list.add("canvasColor:255, 255, 255");

        list.add("grid:true");
   
        list.add("pictX:50");
        list.add("pictY:50");
        list.add("pictWidth:700");
        list.add("pictHeight:200");

        list.add("pictCorner1:50");
        list.add("pictCorner2:50");
        list.add("pictCorner3:50");
        list.add("pictCorner4:50");

        list.add("pictOuterBorder:4");
        list.add("pictInnerBorder:2");

        list.add("pictOuterBorderColor:51, 153, 51");
        list.add("pictInnerBorderColor:10, 10, 10");

        list.add("gradient:true");
        list.add("gradientSteps:3");
        list.add("gradientOrientation:0");
        
        list.add("pictGradientStartColor:0,0,0");
        list.add("pictGradientStopColor:255,255,255");

        list.add("emboss:true");
        list.add("embossOpacity:7");
        list.add("horizontalEmboss:3");
        list.add("verticalEmboss:3");
        list.add("embossColor1:255, 255, 255"); // Left-top
        list.add("embossColor2:0, 0, 0");
        
        list.add("shadow:true");
        list.add("shadowOffsetX:5");
        list.add("shadowOffsetY:5");
        list.add("shadowDarkColor:75");
        list.add("shadowLightColor:200");

        list.add("text:on");
        list.add("textValue:graphiQ");
        list.add("fontSize:200");
        list.add("fontFamily:Nimbus Sans L Bold");
        list.add("textColor:51, 153, 51");
        list.add("textOpacity:7");
        list.add("textBorderSize:0");
        list.add("textEmboss:true");
        list.add("textHorizontalEmboss:2");
        list.add("textVerticalEmboss:1");
        list.add("textEmbossColor1:100, 100, 100"); // Left-top
        list.add("textEmbossColor2:200, 200, 200");

        render(list);
    }

//    @Test
    public void testGraphic2() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("canvasWidth:600");
        list.add("canvasHeight:600");
        list.add("canvasColor:255, 255, 255");

        list.add("grid:true");
   
        list.add("pictX:100");
        list.add("pictY:100");
        list.add("pictWidth:400");
        list.add("pictHeight:400");

        list.add("pictCorner1:400");
        list.add("pictCorner2:400");
        list.add("pictCorner3:400");
        list.add("pictCorner4:400");

        list.add("pictOuterBorder:10");
        list.add("pictInnerBorder:4");

        list.add("pictOuterBorderColor:51, 153, 51");
        list.add("pictInnerBorderColor:10, 10, 10");

        list.add("pictGradientStartColor:0,0,0");
        list.add("pictGradientColor:255,255,255");

        list.add("gradient:true");
        list.add("gradientSteps:2");
        list.add("gradientOrientation:1");

        list.add("emboss:true");
        list.add("embossOpacity:7");
        list.add("horizontalEmboss:3");
        list.add("verticalEmboss:3");
        list.add("embossColor1:255, 255, 255"); // Left-top
        list.add("embossColor2:0, 0, 0");

        list.add("text:off");
        list.add("textEmboss:true");
        list.add("textHorizontalEmboss:2");
        list.add("textVerticalEmboss:1");
        list.add("textEmbossColor1:100, 100, 100"); // Left-top
        list.add("textEmbossColor2:200, 200, 200");
        list.add("textOpacity:7");
        list.add("textBorderSize:0");
        list.add("fontFamily:Nimbus Sans L Bold");
        list.add("textColor:51, 153, 51");
        list.add("fontSize:200");
        list.add("textValue:graphiQ");
        
        list.add("shadow:true");
        list.add("shadowOffsetX:5");
        list.add("shadowOffsetY:5");
        list.add("shadowDarkColor:75");
        list.add("shadowLightColor:200");

        render(list);
    }

//    @Test
    public void testGraphic3() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("canvasWidth:1200");
        list.add("canvasHeight:800");
        list.add("canvasColor:251, 253, 251");

        list.add("grid:false");
   
        list.add("pictX:100");
        list.add("pictY:100");
        list.add("pictWidth:900");
        list.add("pictHeight:300");

        list.add("pictCorner1:150");
        list.add("pictCorner2:150");
        list.add("pictCorner3:150");
        list.add("pictCorner4:150");

        list.add("pictOuterBorder:4");
        list.add("pictInnerBorder:2");

        list.add("pictOuterBorderColor:51, 153, 51");
        list.add("pictInnerBorderColor:10, 10, 10");

        list.add("pictGradientStartColor:0,0,0");
        list.add("pictGradientColor:255,255,255");

        list.add("gradient:true");
        list.add("gradientSteps:3");
        list.add("gradientOrientation:0");

        list.add("emboss:true");
        list.add("embossOpacity:7");
        list.add("horizontalEmboss:3");
        list.add("verticalEmboss:3");
        list.add("embossColor1:255, 255, 255"); // Left-top
        list.add("embossColor2:0, 0, 0");

        list.add("text:on");
        list.add("textEmboss:true");
        list.add("textHorizontalEmboss:1");
        list.add("textVerticalEmboss:1");
        list.add("textEmbossColor1:100, 100, 100"); // Left-top
        list.add("textEmbossColor2:200, 200, 200");
        list.add("textOpacity:5");
        list.add("textBorderSize:0");
        list.add("fontFamily:Times New Roman");
        list.add("textColor:51, 153, 51");
        list.add("fontSize:280");
        list.add("textValue:Rör och dö!");
        
        list.add("shadow:true");
        list.add("shadowOffsetX:8");
        list.add("shadowOffsetY:8");
        list.add("shadowDarkColor:75");
        list.add("shadowLightColor:200");
        
        render(list);
    }
    
    private void render(List<String> list) throws Exception {
        EntityUtil entity = EntityUtil.getInstance();
        Graphic graphic = (Graphic) entity.process(list, "se.recan.app.vector.Graphic");
        
        LOGGER.debug(entity.feedback());
        LOGGER.debug(entity.getUri());
        
        GraphicRender renderer = new GraphicRender(graphic);

        BufferedImage image = renderer.render();
        this.getContentPane().add(new JLabel(new ImageIcon(image)));

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPosition(graphic.getCanvasWidth(), graphic.getCanvasHeight());
        
        Helper.sleep(4);
        
        setVisible(false);
        dispose();
    }

    private void setPosition(int width, int height) {
        int left, top;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        left = screenSize.width - ((screenSize.width / 4) * 3);
        top = screenSize.height - ((screenSize.height / 4) * 3);
        width = screenSize.width - (screenSize.width / 2);
        height = screenSize.height - (screenSize.height / 2);

        setBounds(left, top, width, height);
    }
}
