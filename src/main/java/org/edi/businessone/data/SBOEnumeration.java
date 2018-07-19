package org.edi.businessone.data;

import com.sap.smb.sbo.api.SBOCOMConstants;
import org.edi.businessone.db.B1Exception;
import org.edi.freamwork.data.DataConvertException;
import org.edi.freamwork.data.keyValue.KeyValue;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Fancy
 * @date 2018/7/19
 */
public class SBOEnumeration {

    private volatile static Map<String, List<KeyValue>> valueMap;

    private static Map<String, List<KeyValue>> getValueMap() {
        if (valueMap == null) {
            synchronized (Enumeration.class) {
                if (valueMap == null) {
                    valueMap = createSBOCommonData();
                }
            }
        }
        return valueMap;
    }

    public static Map<String, List<KeyValue>> createSBOCommonData (){
        Map<String, List<KeyValue>> valueMap = new HashMap<>();
        List<KeyValue> keyValues;
        for (Field field : SBOCOMConstants.class.getDeclaredFields()) {
            int index = field.getName().indexOf("_");
            if (index < 0) {
                continue;
            }
            try {
                String group = field.getName().substring(0, index);
                String name = field.getName().substring(index + 1);
                Object value = field.get(SBOCOMConstants.class);
                if (!(value instanceof Integer)) {
                    continue;
                }
                keyValues = valueMap.get(group);
                if (keyValues == null) {
                    keyValues = new ArrayList<>();
                    valueMap.put(group, keyValues);
                }
                keyValues.add(new KeyValue(name, value));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  valueMap;
    }

    public static Integer valueOf(String type, String value)  {
        List<KeyValue> values = getValueMap().get(type);
        if (values != null) {
            for (KeyValue keyValue : values) {
                if (keyValue.getKey().equals(value)) {
                    return (Integer) keyValue.getValue();
                }
            }
        }
       throw new DataConvertException(OpDescription.SBO_DATA_CONVERT_ERROR);
    }

    public static Integer valueOf(boolean value) {
        if (value) {
            return SBOCOMConstants.BoYesNoEnum_tYES;
        } else {
            return SBOCOMConstants.BoYesNoEnum_tNO;
        }
    }

}
