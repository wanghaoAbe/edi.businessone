package org.edi.businessone.db;


public class B1Connection implements IB1Connection {

    private String server;
    private String companyDB;
    private String userName;
    private String password;
    private Integer laguage;
    private String licenseServer;
    private String sldServer;
    private Integer dbServiceType;
    private String dbUsername;
    private String dbPassword;
    private Boolean useTrusted;
    private String companyName;

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    @Override
    public final String getServer() {
        return server;
    }

    @Override
    public final void setServer(String value){
        this.server = value;
    }

    @Override
    public final String getCompanyDB() {
        return companyDB;
    }

    @Override
    public final void setCompanyDB(String value){
        this.companyDB = value;
    }

    @Override
    public final String getUserName() {
        return userName;
    }

    @Override
    public final void setUserName(String value){
        this.userName = value;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final void setPassword(String value){
        this.password = value;
    }

    @Override
    public final Integer getLanguage() {
        return laguage;
    }

    @Override
    public final void setLanguage(Integer value){
        this.laguage = value;
    }

    @Override
    public final String getLicenseServer() {
        return licenseServer;
    }

    @Override
    public final void setLicenseServer(String value){
        this.licenseServer = value;
    }

    @Override
    public final String getSLDServer() {
        return sldServer;
    }

    @Override
    public final void setSLDServer(String value){
        this.sldServer = value;
    }

    @Override
    public final Integer getDBServerType() {
        return dbServiceType;
    }

    @Override
    public final void setDBServerType(Integer value){
        this.dbServiceType = value;
    }

    @Override
    public final String getDBUserName() {
        return dbUsername;
    }

    @Override
    public final void setDBUserName(String value){
        this.dbUsername = value;
    }

    @Override
    public final String getDBPassword() {
        return dbPassword;
    }

    @Override
    public final void setDBPassword(String value){
        this.dbPassword = value;
    }

    @Override
    public final Boolean getIsUserTrusted() {
        return useTrusted;
    }

    @Override
    public final void setIsUserTrusted(Boolean value){
        this.useTrusted = value;
    }

    @Override
    public void setIsUserTrusted(String value) {
        this.setIsUserTrusted(Boolean.valueOf(value));
    }
}
