package org.edi.businessone.repository;

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

//    public ICompany connect(){
//
//    }



}
