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
    private String companyName;

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String value) {
        this.companyName = value;
    }

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

    public final void setLanguage(int value){
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

    @Override
    public final void setSLDServer(String value){
        this.sldServer = value;
    }

    public final int getDBServerType() {
        return dbServiceType;
    }

    public final void setDBServerType(int value){
        this.dbServiceType = value;
    }

    public final String getDBUserName() {
        return dbUsername;
    }

    public final void setDBUserName(String value){
        this.dbUsername = value;
    }

    public final String getDBPassword() {
        return dbPassword;
    }

    public final void setDBPassword(String value){
        this.dbPassword = value;
    }

    @Override
    public boolean getIsUserTrusted() {
        return false;
    }

    public final boolean getIsUseTrusted() {
        return useTrusted;
    }

    public final void setIsUserTrusted(boolean value){
        this.useTrusted = value;
    }
}
