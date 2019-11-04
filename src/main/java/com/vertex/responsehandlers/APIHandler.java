package com.vertex.responsehandlers;

import com.common.ResponseBuilder;
import com.common.ValidationConstants;
import com.common.ValidationUtil;
import com.exceptions.InvalidFormFieldException;
import io.vertx.ext.web.RoutingContext;

public class APIHandler {

    public void validateHandler(RoutingContext routingContext)
    {
        routingContext.request().bodyHandler(body -> {
            try{
                if(body.toString().equals(""))
                    throw new InvalidFormFieldException(ValidationConstants.EMPTY_BODY);
                if(ValidationUtil.validate(body.toJsonObject())){
                    routingContext.response().putHeader("content-type", "application/json").end(ResponseBuilder.buildResponse(ValidationConstants.SUCESS_MESSAGE).toString());
                }
            }
            catch (Exception e){
                    routingContext.response().putHeader("content-type", "application/json").end(ResponseBuilder.buildResponse(e.getMessage()).toString());
            }
        });
    }

    public void resourceNotFoundHandler(RoutingContext routingContext){
        routingContext.response().putHeader("content-type", "application/json").end(ResponseBuilder.buildResponse("please enter a valid url").toString());
    }

    public void welcomeHandler(RoutingContext routingContext){
        routingContext.response().putHeader("content-type", "application/json").end(ResponseBuilder.buildResponse("welcome").toString());
    }


}
