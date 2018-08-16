package org.edi.businessone.repository;

import com.xxl.job.core.log.XxlJobLogger;
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.db.IB1Connection;
import com.sap.smb.sbo.api.*;

/**
 * @author Fancy
 * @date 2018/6/21
 */
public class BORepositoryBusinessOne {

    private String server;
    private String companyDB;
    private String userName;
    private String password;
    private Integer laguage;
    private String licenseServer;
    private String sldServer;
    private Integer dbServerType;
    private String dbUsername;
    private String dbPassword;
    private Boolean useTrusted;

    private volatile static BORepositoryBusinessOne boRepositoryBusinessOne = null;

    private volatile static ICompany company = null;

    public final static BORepositoryBusinessOne getInstance(IB1Connection ib1Connection) {
            synchronized (BORepositoryBusinessOne.class) {
                if (null == boRepositoryBusinessOne) {
                    boRepositoryBusinessOne = new BORepositoryBusinessOne(ib1Connection);
                    XxlJobLogger.log("初始化B1仓库" );
                }
            }
        return boRepositoryBusinessOne;
    }


    private BORepositoryBusinessOne(IB1Connection connection){
        this.server = connection.getServer();
        this.companyDB = connection.getCompanyDB();
        this.userName = connection.getUserName();
        this.password = connection.getPassword();
        this.laguage = connection.getLanguage();
        this.licenseServer = connection.getLicenseServer();
        this.sldServer = connection.getSLDServer();
        this.dbServerType = connection.getDBServerType();
        this.dbUsername = connection.getDBUserName();
        this.dbPassword = connection.getPassword();
        this.useTrusted = connection.getIsUserTrusted();
    }

    public final ICompany getCompany() throws B1Exception {
        synchronized (BORepositoryBusinessOne.class) {
            if (null == company) {
                XxlJobLogger.log("开始连接");
                return this.connect();
            } else {
                if (!company.isConnected()) {
                    XxlJobLogger.log("重新连接");
                    company.connect();
                    return company;
                }
                return company;
            }
        }
    }

    public void dispose(){
        if(company!=null ){
           if(company.isConnected()){
               company.disconnect();
               company.release();
           }
        }
    }

    private ICompany connect()throws B1Exception {
        try{
            company = SBOCOMUtil.newCompany();
            company.setServer(this.server);
            company.setCompanyDB(this.companyDB);
            company.setUserName(this.userName);
            company.setPassword(this.password);
            company.setDbServerType(this.dbServerType);
            company.setUseTrusted(this.useTrusted);
            company.setLanguage(this.laguage);
            company.setDbUserName(this.dbUsername);
            company.setDbPassword(this.dbPassword);
            company.setSLDServer(this.sldServer);
            company.setLicenseServer(this.licenseServer);

            int connectionResult = company.connect();
            if (connectionResult == 0) {
                XxlJobLogger.log("Successfully connected to " + company.getCompanyName());
            } else {
                SBOErrorMessage errMsg = company.getLastError();
                throw new B1Exception("Cannot connect to server: "
                        + errMsg.getErrorMessage()
                        + " "
                        + errMsg.getErrorCode());
            }
            return company;
        }catch (Exception e){
            XxlJobLogger.log(e);
            throw new B1Exception(e);
        }
    }

}
