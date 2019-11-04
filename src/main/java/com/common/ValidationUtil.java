package com.common;

import io.vertx.core.json.JsonObject;
import java.util.*;

import com.exceptions.InvalidFormFieldException;

public class ValidationUtil {

    public static boolean validate(JsonObject form) throws InvalidFormFieldException {
        Set<String> keys = form.getMap().keySet();
        validateKeys(keys);
        validateValues(form);
        return true;
    }

    public static void validateKeys(Set<String> keys) throws InvalidFormFieldException{
        if(!keys.contains(ValidationConstants.NAME))
            throw new InvalidFormFieldException(ValidationConstants.EMPTY_NAME_FIELD_EXCEPTION);
        if(!keys.contains(ValidationConstants.GENDER))
            throw new InvalidFormFieldException(ValidationConstants.EMPTY_GENDER_FIELD_EXCEPTION);
        if(!keys.contains(ValidationConstants.AADHAR))
            throw new InvalidFormFieldException(ValidationConstants.EMPTY_AADHAR_FIELD_EXCEPTION);
    }

    public static void validateValues(JsonObject form) throws InvalidFormFieldException{
        Map<String, Object> map = form.getMap();
        for(String key : map.keySet()){
            switch(key){
                case ValidationConstants.NAME :
                    if(map.get(key).toString().trim().length() == 0)
                        throw new InvalidFormFieldException(ValidationConstants.EMPTY_NAME_EXCEPTION);
                    break;
                case ValidationConstants.GENDER :
                    if(map.get(key).toString().trim().length() == 0)
                        throw new InvalidFormFieldException(ValidationConstants.EMPTY_GENDER_EXCEPTION);
                    break;
                case ValidationConstants.AADHAR :
                    if(map.get(key).toString().trim().length() != 12)
                        throw new InvalidFormFieldException(ValidationConstants.AADHAR_lENGTH_EXCEPTION);
                    break;
            }
        }
    }


}