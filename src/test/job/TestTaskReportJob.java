package job;

import junit.framework.TestCase;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.job.TaskReportJobHandler;
import org.edi.freamwork.configuration.ConfigException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Fancy
 * @date 2018/7/29
 */
public class TestTaskReportJob  extends TestCase {

    @Test
    public void testTaskReportJob()  throws Exception{
        TaskReportJobHandler jobHandler = new TaskReportJobHandler();
        jobHandler.execute("");
    }
}
