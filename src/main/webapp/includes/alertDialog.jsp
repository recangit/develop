<%
    String bgrColor = request.getParameter("bgrColor")!=null ? request.getParameter("bgrColor") : "#ffffff";
    String borderColor = request.getParameter("borderColor")!=null ? request.getParameter("borderColor") : "#999999";

    String headline = request.getParameter("headline");
    String article = request.getParameter("article");
    String abortLink = request.getParameter("abortLink");
    String closeLink = request.getParameter("closeLink");
    String confirmLink = request.getParameter("confirmLink");
    String image = request.getParameter("image");
    int width = 300;
    if(request.getParameter("width")!=null) { width = Integer.parseInt(request.getParameter("width")); }
    boolean transparent = false;
    if(request.getParameter("transparent") != null) { transparent = Boolean.parseBoolean(request.getParameter("transparent")); }
%>
<%if(transparent) {%>
<div id="blur" style="position:absolute;margin:0;left:0;top:0;width:100%;height:102%;z-index:5;background-color:rgba(150, 150, 150, 0.6);"></div>
<%}%>

<div class="modal" style="width:<%=width%>px;position:absolute;margin-left:-<%=width/2%>px;left:50%;z-index:10;margin-top:150px;">
        <%if(closeLink!=null) {%>
            <div style='float:right;padding-right:10px;margin:0;'>
                <a title="<%= closeLink %>" href="<%= closeLink %>">x</a>
            </div>
        <%}%>
        <%if(headline!=null) {%>
            <div class="headline">
                <%= headline %>
            </div>
        <%}%>
        <%if(image!=null) {%>
            <img src="<%= image %>" />
        <%}%>
        <%if(article!=null) {%>
            <div class="article">
                <%= article %>
            </div>
        <%}%>
        <div class="link">
            <%if(abortLink!=null) {%>
            <a title="<%= abortLink %>" href="<%= abortLink %>">&#187;Avbryt&nbsp;</a>
            <%}%>

            <% if(confirmLink!=null) { %>
            <a title="<%= confirmLink %>" href="<%= confirmLink %>">&#187;OK</a>
            <%}%>
        </div>
    
    
</div>