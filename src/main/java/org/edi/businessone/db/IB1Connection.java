package org.edi.businessone.db;

/**
 * @author Fancy
 * @date 2018/6/21
 */
public interface IB1Connection {

    String getCompanyName();

    void setCompanyName(String value);

    /**
     * 服务器地址
     *
     * @return
     */
    String getServer();

    void setServer(String value);

    /**
     * 连接的公司
     *
     * @return
     */
    String getCompanyDB();

    void setCompanyDB(String value);
    /**
     * 用户
     *
     * @return
     */
    String getUserName();

    void setUserName(String value);
    /**
     * 密码
     *
     * @return
     */
    String getPassword();

    void setPassword(String value);
    /**
     * 语言
     *
     * @return
     */
    int getLanguage();

    void setLanguage(int value);
    /**
     * 许可服务
     *
     * @return
     */
    String getLicenseServer();

    void setLicenseServer(String value);
    /**
     * 架构服务
     *
     * @return
     */
    String getSLDServer();

    void setSLDServer(String value);
    /**
     * 数据库类型
     *
     * @return
     */
    int getDBServerType();

    void setDBServerType(int value);
    /**
     * 数据库用户
     *
     * @return
     */
    String getDBUserName();

    void setDBUserName(String value);
    /**
     * 数据库密码
     *
     * @return
     */
    String getDBPassword();

    void setDBPassword(String value);
    /**
     * 使用信任连接
     *
     * @return
     */
    boolean getIsUserTrusted();

    void setIsUserTrusted(boolean value);
}
