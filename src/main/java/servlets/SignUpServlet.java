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
 * Created by alex on 20.06.17.
 */
public class SignUpServlet extends HttpServlet
{

    private final DBService dbService;

    public SignUpServlet(DBService dbService)
    {
        this.dbService = dbService;
    }


    //sign up
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        //UserProfile userProfile = new UserProfile(login, password, "email");

        //Возможно нужна какая-то проверка на уникальность
        try {
            long userId = dbService.addUser(login, password);
            System.out.println("Added user id: " + userId);

            /*UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);*/

        } catch (DBException e) {
            e.printStackTrace();
        }






        //accountService.addNewUser(userProfile);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
