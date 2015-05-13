<%@ page import="se.recan.app.vector.Graphic" %>
<%@ page import="se.recan.utils.CacheUtil" %>
<%@ page import="java.awt.Font"%>
<%@ page import="java.awt.GraphicsEnvironment"%>

<%
    Graphic graphic = (Graphic) request.getSession().getAttribute("graphic");
    GraphicsEnvironment fonts = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    boolean modal = request.getAttribute("modal") != null;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Graphic</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <%if(modal) {%>
        <jsp:include page="includes/alertDialog.jsp">
            <jsp:param name="headline" value="Rubrik"/>
            <jsp:param name="article" value="Text"/>
            <jsp:param name="abortLink" value="controller?template=vector&modal=false"/>
            <jsp:param name="confirmLink" value="Link"/>
            <jsp:param name="width" value="800"/>        
            <jsp:param name="image" value="graphic"/>
        </jsp:include>
        <%}%>
        
        <form action="controller?template=vector" method="post">

            <table class="mainCanvas">
                <tr>
                    <td style="padding:0; width:30%;">
                        <table class="graphicCanvas">
                            <tr>
                                <td colspan="4"><h3>Canvas</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Grid:</label>
                                    <input<%=graphic.isGrid() ? " checked=\"checked\"" : ""%> type="checkbox" name="grid" />
                                </td>
                                <td>
                                    <label>Färg:</label>
                                    <input class="large" type="text" name="canvasColor" value="<%=graphic.getCanvasColorToString()%>" />
                                </td>
                                <td>
                                    <label>Width:</label>
                                    <input type="text" name="canvasWidth" value="<%=graphic.getCanvasWidth()%>" />
                                </td>
                                <td>
                                    <label>Height:</label>
                                    <input type="text" name="canvasHeight" value="<%=graphic.getCanvasHeight()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Bild</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>X:</label>
                                    <input class="fourthSize" type="text" name="pictX" value="<%=graphic.getPictX()%>" />
                                </td>
                                <td>
                                    <label>Y:</label>
                                    <input class="fourthSize" type="text" name="pictY" value="<%=graphic.getPictY()%>" />
                                </td>
                                <td>
                                    <label>Width:</label>
                                    <input class="fourthSize" type="text" name="pictWidth" value="<%=graphic.getPictWidth()%>" />
                                </td>
                                <td>
                                    <label>Height:</label>
                                    <input class="fourthSize" type="text" name="pictHeight" value="<%=graphic.getPictHeight()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Hörn</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>VT:</label>
                                    <input type="text" name="pictCorner1" value="<%=graphic.getPictCorner1()%>" />
                                </td>
                                <td>
                                    <label>HT:</label>
                                    <input type="text" name="pictCorner2" value="<%=graphic.getPictCorner2()%>" />
                                </td>
                                <td>
                                    <label>HB:</label>
                                    <input type="text" name="pictCorner3" value="<%=graphic.getPictCorner3()%>" />
                                </td>
                                <td>
                                    <label>VB:</label>
                                    <input type="text" name="pictCorner4" value="<%=graphic.getPictCorner4()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Ramar</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Undre  bredd:</label>
                                    <input type="text" name="pictOuterBorder" value="<%=graphic.getPictOuterBorder()%>" />
                                </td>
                                <td>
                                    <label>Undre färg:</label>
                                    <input class="large" type="text" name="pictOuterBorderColor" value="<%=graphic.getPictOuterBorderColorToString()%>" />
                                </td>
                                <td>
                                    <label>Övre bredd:</label>
                                    <input type="text" name="pictInnerBorder" value="<%=graphic.getPictInnerBorder()%>" />
                                </td>
                                <td>
                                    <label>Övre färg:</label>
                                    <input class="large" type="text" name="pictInnerBorderColor" value="<%=graphic.getPictInnerBorderColorToString()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Inre</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Gradient:</label>
                                    <input<%=graphic.isGradient() ? " checked=\"checked\"" : ""%> type="checkbox" name="gradient" /><br />
                                    <label>Vertical:</label>
                                    <input<%=graphic.getGradientOrientation() == Graphic.VERTICAL ? " checked=\"checked\"" : ""%> type="radio" name="gradientOrientation" value="<%=Graphic.VERTICAL%>" /><br />
                                    <label>Horizontal:</label>
                                    <input<%=graphic.getGradientOrientation() == Graphic.HORIZONTAL ? " checked=\"checked\"" : ""%> type="radio" name="gradientOrientation" value="<%=Graphic.HORIZONTAL%>" /><br />
                                </td>
                                <td>
                                    <label>Antal steg</label>
                                    <select name="gradientSteps">
                                        <% for (int i = 1; i < 6; i++) {%>
                                        <option value="<%=i%>"<%=graphic.getGradientSteps() == i ? " selected=\"selected\"" : ""%>><%=i%></option>
                                        <% }%>
                                    </select>
                                </td>
                                <td>
                                    <label>Gradient start eller Inre färg:</label>
                                    <input class="small" type="text" name="pictGradientStartColor" value="<%=graphic.getPictGradientStartColorToString()%>" />
                                </td>
                                <td>
                                    <label>Gradient stop:</label>
                                    <input<%=!graphic.isGradient() ? " disabled=\"disabled\"" : ""%> class="large" type="text" name="pictGradientStopColor" value="<%=graphic.getPictGradientStopColorToString()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Emboss?</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Emboss:</label>
                                    <input<%=graphic.isEmboss() ? " checked=\"checked\"" : ""%> type="checkbox" name="emboss" /><br />


                                    <label>Horizontal:</label>
                                    <input class="small" type="text" name="horizontalEmboss" value="<%=graphic.getHorizontalEmboss()%>" />
                                </td>
                                <td>
                                    <label>Vertical:</label>
                                    <input class="small" type="text" name="verticalEmboss" value="<%=graphic.getVerticalEmboss()%>" />
                                </td>
                                <td>
                                    <label>Transparans:</label>
                                    <select class="medium" name="embossOpacity">
                                        <option value="0"<%=graphic.getEmbossOpacity() == 0 ? " selected=\"selected\"" : ""%>>Full</option>
                                        <% for (int i = 1; i < 10; i++) {%>
                                        <option value="<%=i%>"<%=graphic.getEmbossOpacity() == i ? " selected=\"selected\"" : ""%>><%=i%></option>
                                        <% }%>
                                        <option value="10"<%=graphic.getEmbossOpacity() == 10 ? " selected=\"selected\"" : ""%>>Ingen</option>
                                    </select>
                                </td>
                                <td>
                                    <label>Färg 1: (Emboss)</label>
                                    <input class="small" type="text" name="embossColor1" value="<%=graphic.getEmbossColor1ToString()%>" />
                                    <br />
                                    <label>Färg 2: (Emboss)</label>
                                    <input class="small" type="text" name="embossColor2" value="<%=graphic.getEmbossColor2ToString()%>" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Skugga:</label>
                                    <input<%=graphic.isShadow() ? " checked=\"checked\"" : ""%> type="checkbox" name="shadow" /><br />


                                </td>
                                <td>
                                    <label>Horizontal:</label>
                                    <input class="small" type="text" name="shadowOffsetX" value="<%=graphic.getShadowOffsetX()%>" />
                                    <br />
                                    <label>Vertical:</label>
                                    <input class="small" type="text" name="shadowOffsetY" value="<%=graphic.getShadowOffsetY()%>" />
                                </td>


                                <td>Färg på skugga start
                                    <label>Färg 1: (Emboss)</label>
                                    <input class="small" type="text" name="shadowDarkColor" value="<%=graphic.getShadowDarkColor()%>" />

                                </td>
                                <td>Färg på skugga stopp
                                    <label>Färg 2: (Emboss)</label>
                                    <input class="small" type="text" name="shadowLightColor" value="<%=graphic.getShadowLightColor()%>" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4"><h3>Text</h3></td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Text:</label>
                                    <input<%=graphic.isText() ? " checked=\"checked\"" : ""%> type="checkbox" name="text" /><br />

                                    <label>Text:</label>
                                    <input type="text" name="textValue" value="<%=graphic.getTextValue()%>" />
                                </td>
                                <td>
                                    <label>Familj:</label>
                                    <select name="fontFamily">
                                        <%for (Font f : fonts.getAllFonts()) {%>
                                        <option<%=graphic.getFontFamily().equals(f.getFontName()) ? " selected=\"selected\"" : ""%> value="<%=f.getFontName()%>"><%=f.getFontName()%></option>
                                        <%}%>
                                    </select>
                                </td>
                                <td>
                                    <label>Storlek:</label>
                                    <input type="text" name="fontSize" value="<%=graphic.getFontSize()%>" />
                                </td>
                                <td>
                                    <label>Färg:</label>
                                    <input type="text" name="textColor" value="<%=graphic.getTextColorToString()%>" />
                                    <br />
                                    <label>Transparans på text:</label>
                                    <select name="textOpacity">
                                        <option<%=graphic.getTextOpacity() == 0 ? " selected=\"selected\"" : ""%> value="0">Full</option>
                                        <% for (int i = 1; i < 10; i++) {%>
                                        <option<%=graphic.getTextOpacity() == i ? " selected=\"selected\"" : ""%> value="<%=i%>"><%=i%></option>
                                        <% }%>
                                        <option<%=graphic.getTextOpacity() == 10 ? " selected=\"selected\"" : ""%> value="10">Ingen</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="4">
                                    <label>Textram:</label>
                                    <input type="text" name="textBorderSize" value="<%=graphic.getTextBorderSize()%>" />
                                    <br />


                                    <label>Emboss:</label>
                                    <input<%=graphic.isTextEmboss() ? " checked=\"checked\"" : ""%> type="checkbox" name="textEmboss" /><br />

                                    <br />


                                    <label>Horizontal:</label>
                                    <input class="small" type="text" name="textHorizontalEmboss" value="<%=graphic.getTextHorizontalEmboss()%>" />
                                    <br />
                                    <label>Vertical:</label>
                                    <input class="small" type="text" name="textVerticalEmboss" value="<%=graphic.getTextVerticalEmboss()%>" />
                                    <br />



                                    <label>Färg 1: (text Emboss)</label>
                                    <input class="small" type="text" name="textEmbossColor1" value="<%=graphic.getTextEmbossColor1ToString()%>" />
                                    <br />
                                    <label>Färg 2: (textEmboss)</label>
                                    <input class="small" type="text" name="textEmbossColor2" value="<%=graphic.getTextEmbossColor2ToString()%>" />

                                </td>
                            </tr>

                            <tr>
                                <td colspan="4">

                                </td>
                            </tr>

                            <tr>    
                                <td colspan="4">
                                    <input type="submit" name="save" value="save" />
                                    <input type="reset" name="clean" value="clean" />
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td style="position:relative;"><img style="position:absolute;top:0;left:0;" src="graphic" /></td>
                </tr>
            </table>
        </form>
        <a href="controller?template=vector&pictOuterBorderColor=51, 153, 51&text=on&fontFamily=Nimbus Sans L Bold&pictGradientStartColor=110,0,0&grid=on&fontSize=200&textColor=51, 153, 51&textValue=graphiQ&textOpacity=4">No:1</a>
        <a href="controller?template=vector&canvasColor=200,200,200&textValue=text&fontSize=100&corner1=0&corner2=0&corner3=0&corner4=0&x=0&y=0">No:2</a>
        <a href="controller?template=vector&outerBorder=0&innerBorder=1&corner1=50&corner2=50&corner3=50&corner4=500&textOpacity=2&canvasColor=235, 235, 235&height=200&textEmboss=1&verticalEmboss=10&opacity=10&gradientStopColor=255, 255, 255&fontFamily=Nimbus Sans L Bold&template=vector&orientation=0&textColor=51, 153, 51&innerBorderColor=0, 0, 0&width=700&horizontalEmboss=5&outerBorderColor=255, 255, 255&fontSize=200&gradientStartColor=0, 0, 0&canvasWidth=800&canvasHeight=500&textBorderSize=0&cyclic=on&y=50&textValue=graphiQ&gradient=on&x=50">No:3</a>
    </body>
</html>