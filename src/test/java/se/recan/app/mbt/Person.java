// Generated by GraphWalker (http://www.graphwalker.org)
package se.recan.app.mbt;

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;

@Model(file = "mbt/person.graphml")
public interface Person {

    @Vertex()
    void s_Submit();

    @Vertex()
    void s_FirstName();

    @Vertex()
    void s_UserName();

    @Edge()
    void a_Submit();

    @Edge()
    void a_LastName();

    @Vertex()
    void s_InvalidFirstName();

    @Edge()
    void a_FirstName();

    @Vertex()
    void s_Gender();

    @Vertex()
    void s_invalidSocialSecurity();

    @Vertex()
    void s_LastName();

    @Vertex()
    void s_PassWord2();

    @Vertex()
    void s_ExitBrowser();

    @Edge()
    void a_Granska();

    @Edge()
    void a_SocialNumb();

    @Vertex()
    void s_StartBrowser();

    @Vertex()
    void s_SocialSecurity();

    @Vertex()
    void s_PassWord();

    @Edge()
    void a_StartBrowser();

    @Edge()
    void a_InvalidSocialNumb();

    @Edge()
    void a_Gender();

    @Edge()
    void a_PassWord();

    @Edge()
    void a_PassWord2();

    @Edge()
    void a_UserName();

    @Edge()
    void a_InvalidFirstName();
}