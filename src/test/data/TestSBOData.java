package data;

import junit.framework.TestCase;
import org.edi.businessone.data.SBOEnumeration;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Fancy
 * @date 2018/7/19
 */
public class TestSBOData extends TestCase{

    @Test
    public void testEnumeration(){
        //SBOEnumeration.createSBOCommonData();
        String code = String.valueOf(0);
        Assert.assertEquals(code,"0");

    }
}
