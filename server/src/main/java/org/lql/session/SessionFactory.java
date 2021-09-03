package org.lql.session;

/**
 * Title: SessionFactory <br>
 * ProjectName: coldchain <br>
 * description: session单例工厂 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/2 9:10 <br>
 */
public abstract class SessionFactory {

    private static class SingletonHolder {
        private static final Session SESSION = new SessionMemoryImpl();
    }

    public static Session getSession() {
        return SingletonHolder.SESSION;
    }
}
