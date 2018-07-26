package repository;

import junit.framework.TestCase;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.freamwork.configuration.ConfigException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Fancy
 * @date 2018/7/17
 */
public class TestB1Company extends TestCase {

    @Test
    public void testGetCompanyInfo() throws ConfigException {
        CompanyManager manager = new CompanyManager();
        IB1Connection conn = manager.getB1ConnInstance("AVA");
        Assert.assertEquals("AVA",conn.getCompanyName());
    }
}
