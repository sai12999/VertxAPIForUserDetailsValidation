package com.common;

import io.vertx.core.json.JsonObject;

public class ResponseBuilder {

    public static JsonObject buildResponse(String msg){
        JsonObject jsonObject = new JsonObject();
        return jsonObject.put("status", msg);
    }
}
