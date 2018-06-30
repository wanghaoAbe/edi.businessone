package org.edi.businessone.db;


public class B1Connection implements IB1Connection {

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

    public final String getServer() {
        return server;
    }

    public final void setServer(String value){
        this.server = value;
    }

    public final String getCompanyDB() {
        return companyDB;
    }

    public final void setCompanyDB(String value){
        this.companyDB = value;
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String value){
        this.userName = value;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String value){
        this.password = password;
    }

    public final int getLanguage() {
        return laguage;
    }

    public final void setLaguage(int value){
        this.laguage = value;
    }

    public final String getLicenseServer() {
        return licenseServer;
    }

    public final void setLicenseServer(String value){
        this.licenseServer = value;
    }

    public final String getSLDServer() {
        return sldServer;
    }

    public final void setSldServer(String value){
        this.sldServer = value;
    }

    public final int getDbServerType() {
        return dbServiceType;
    }

    public final void setDbServiceType(int value){
        this.dbServiceType = value;
    }

    public final String getDbUserName() {
        return dbUsername;
    }

    public final void setDbUsername(String value){
        this.dbUsername = value;
    }

    public final String getDbPassword() {
        return dbPassword;
    }

    public final void setDbPassword(String value){
        this.dbPassword = value;
    }

    public final boolean isUseTrusted() {
        return useTrusted;
    }

    public final void setUseTrusted(boolean value){
        this.useTrusted = value;
    }
}
