package org.edi.businessone.util;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Fancy
 * @date 2018/8/15
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {

        //服务类所在的包路径
        packages("org.edi.business.service");
       // packages("org.edi.initialfantasy.filter");
        //注册JSON转换器
        register(JacksonJsonProvider.class);

    }
}
