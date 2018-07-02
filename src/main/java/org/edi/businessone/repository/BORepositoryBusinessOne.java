package org.edi.businessone.repository;

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
    private int laguage;
    private String licenseServer;
    private String sldServer;
    private int dbServiceType;
    private String dbUsername;
    private String dbPassword;
    private boolean useTrusted;


    private ICompany company;

    public ICompany getCompany() throws B1Exception{
        if(null == company)
            return this.connect();
        else
            return company;
    }

    public BORepositoryBusinessOne(){

    }

    public BORepositoryBusinessOne(IB1Connection connection){
        this.server = connection.getServer();
        this.companyDB = connection.getCompanyDB();
        this.userName = connection.getUserName();
        this.password = connection.getPassword();
        this.laguage = connection.getLanguage();
        this.licenseServer = connection.getLicenseServer();
        this.sldServer = connection.getSLDServer();
        this.dbServiceType = connection.getDbServerType();
        this.dbUsername = connection.getDbUserName();
        this.dbPassword = connection.getDbPassword();
        this.useTrusted = connection.isUseTrusted();
    }

    public ICompany connect()throws B1Exception {
        try{
            company = SBOCOMUtil.newCompany();
            company.setServer("dbserver");
            company.setCompanyDB("SBODemoGB");
            company.setUserName("manager");
            company.setPassword("Password");
            company.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2012);
            company.setUseTrusted(false);
            company.setLanguage(SBOCOMConstants.BoSuppLangs_ln_English);
            company.setDbUserName("sa");
            company.setDbPassword("SQLPassword");
            company.setSLDServer("SLDServer");
            company.setLicenseServer("licenserver:30000");

            int connectionResult = company.connect();
            if (connectionResult == 0) {
                System.out.println("Successfully connected to " + company.getCompanyName());
            } else {
                SBOErrorMessage errMsg = company.getLastError();
                throw new B1Exception("Cannot connect to server: "
                        + errMsg.getErrorMessage()
                        + " "
                        + errMsg.getErrorCode());
            }
            return company;
        }catch (Exception e){
            throw new B1Exception(e);
        }

    }

}
