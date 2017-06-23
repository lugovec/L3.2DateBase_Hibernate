package servlets;


import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alugovets on 20.06.17.
 */
public class SignInServlet extends HttpServlet
{

    private final DBService dbService;

    public SignInServlet(DBService dbService)
    {
        this.dbService = dbService;
    }

    //sign in
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");

        try {

            long userId = dbService.getUserId(login);

            if (userId != 0)
            {

                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + login);
                response.setStatus(200);

                return;
            }

            else
            {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                response.setStatus(401);

                return;
            }

        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
