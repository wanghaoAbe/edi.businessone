package org.edi.businessone.data;

import org.edi.freamwork.bo.IBusinessObject;

/**
 * @author Fancy
 * @date 2018/8/14
 */
public class SBOResult {

    public String code;

    public String getCode(){return code;}

    public void setCode(String code){this.code = code;}

    public String uniquekey;

    public String getUniquekey(){return uniquekey;}

    public void setUniquekey(String uniquekey){
        this.uniquekey = uniquekey;
    }

    public String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String returnEntry;

    public String getReturnEntry(){return returnEntry;}

    public void setReturnEntry(String returnEntry){
        this.returnEntry = returnEntry;
    }


}
