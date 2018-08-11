package repository;

import com.sap.smb.sbo.api.Company;
import com.sap.smb.sbo.api.ICompany;
import com.xxl.job.core.log.XxlJobLogger;
import junit.framework.TestCase;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.junit.Test;

/**
 * @author Fancy
 * @date 2018/6/21
 */
public class TestBoRepository extends TestCase{
    private final CompanyManager companyManager = new CompanyManager();


    @Test
    public void testCompanyConnect() throws Exception{

        XxlJobLogger.log(String.format(B1OpResultDescription.SBO_CREATE_ORDER_SUCCESS_INFO,1,2));
        IB1Connection connection = companyManager.getB1ConnInstance("AVA");
        //ICompany company = BORepositoryBusinessOne.getInstance(connection).connect();
    }
}
