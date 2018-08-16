package org.edi.businessone.db;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.edi.businessone.data.MyConfiguration;
import org.edi.businessone.data.SBOClassData;
import org.edi.businessone.data.SBOEnumeration;
import org.edi.freamwork.configuration.ConfigException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fancy
 * @date 2018/7/17
 */
@Component(value = "companyManager")
public class CompanyManager {

    private static List<IB1Connection> b1Connections = new ArrayList<>();

    /**
     * 获取B1连接配置
     * @return B1公司连接信息集合
     */
    private List<IB1Connection> getB1Connection() throws ConfigException{
        List<IB1Connection> b1Connections = new ArrayList<>() ;
        IB1Connection b1Connection;
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(new File(this.getCompanyFilePath()));
            Element nodes = document.getRootElement();
            List<Element> companyNodes = nodes.elements(MyConfiguration.COMPANY_NODE_NAME);
            for (Element companyNode:companyNodes) {
                b1Connection = new B1Connection();
                b1Connection.setCompanyName(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_COMPANY_NAME));
                b1Connection.setServer(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_DB_SERVER));
                b1Connection.setDBServerType(SBOEnumeration.valueOf(SBOClassData.DB_TYPE,getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_DB_TYPE)));
                b1Connection.setDBUserName(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_DB_USER));
                b1Connection.setDBPassword(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_DB_PASSWORD));
                b1Connection.setCompanyDB(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_COMPANY));
                b1Connection.setLicenseServer(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_LICENSE_SERVER));
                b1Connection.setSLDServer(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_SLD_SERVER));
                b1Connection.setLanguage(SBOEnumeration.valueOf(SBOClassData.LANGUAGE,getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_LANGUAGE)));
                b1Connection.setIsUserTrusted(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_DB_USE_TRUSTED));
                b1Connection.setUserName(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_USER));
                b1Connection.setPassword(getNodeValue(companyNode,MyConfiguration.CONFIG_ITEM_B1_PASSWORD));
                b1Connections.add(b1Connection);
            }
        } catch (DocumentException e) {
            throw new ConfigException(e);
        }
        return b1Connections;
    }

    /**
     * 根据公司名 获取B1连接信息
     * @param companyName 公司名称
     * @return B1连接对象
     */
    public IB1Connection getB1ConnInstance(String companyName) throws ConfigException{
        IB1Connection connection = new B1Connection();
        if(b1Connections == null || b1Connections.size() == 0){
            b1Connections = getB1Connection();
        }
        for (IB1Connection conn:b1Connections) {
            if(companyName.equals(conn.getCompanyName())){
                connection = conn;
            }
        }
        return connection;
    }

    private String getNodeValue(Element element,String nodeName){
        if(element == null || nodeName == null || nodeName.isEmpty()) {
            return "";
        }
        Node node = element.selectSingleNode(nodeName);
        if(node == null){
            return "";
        }
        return node.getStringValue();

    }

    private String getCompanyFilePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(MyConfiguration.B1_COMPANY_RESOURCE_NAME);
        if(url != null){
            return url.getFile();
        }
        return "";
    }
}
