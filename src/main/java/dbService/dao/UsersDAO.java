package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session)
    {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException
    {


        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String login) throws HibernateException
    {
        Criteria criteria = session.createCriteria(UsersDataSet.class);

        long userId = 0;

        try {
            userId = ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
        }
        catch (NullPointerException e)
        {

        }

        return userId;
    }

    public long insertUser(String login, String password) throws HibernateException
    {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}
