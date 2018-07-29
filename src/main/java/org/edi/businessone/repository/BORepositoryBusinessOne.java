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
    private Integer laguage;
    private String licenseServer;
    private String sldServer;
    private Integer dbServerType;
    private String dbUsername;
    private String dbPassword;
    private Boolean useTrusted;

    private static volatile  BORepositoryBusinessOne boRepositoryBusinessOne;

    private static ICompany company;

    public static BORepositoryBusinessOne getInstance(IB1Connection ib1Connection){
        if(null == company){
            synchronized (BORepositoryBusinessOne.class){
                if(null == company || !company.getCompanyName().equals(ib1Connection.getCompanyName())){
                    boRepositoryBusinessOne = new BORepositoryBusinessOne(ib1Connection);
                }
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

    public ICompany getCompany() throws B1Exception{
        if(null == company) {
            return this.connect();
        }else {
            return company;
        }
    }

    public ICompany connect()throws B1Exception {
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
