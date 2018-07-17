package repository;

import junit.framework.TestCase;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.freamwork.configuration.ConfigException;
import org.junit.Test;

import java.util.List;

/**
 * @author Fancy
 * @date 2018/7/17
 */
public class TestB1Company extends TestCase {

    @Test
    public void testGetCompanyInfo() throws ConfigException{
        CompanyManager manager = new CompanyManager();
        List<IB1Connection> b1Connections = manager.getB1Connection();
        assertEquals(2,b1Connections.size());
        for (IB1Connection conn:b1Connections) {
            System.out.println(conn.getCompanyDB());
            System.out.println(conn.getDBServerType());
            System.out.println(conn.getServer());
            System.out.println(conn.getDBUserName());
            System.out.println(conn.getDBPassword());
            System.out.println(conn.getIsUserTrusted());
            System.out.println(conn.getLanguage());
            System.out.println(conn.getLicenseServer());
            System.out.println(conn.getSLDServer());
            System.out.println(conn.getUserName());
            System.out.println(conn.getPassword());
        }
    }
}
