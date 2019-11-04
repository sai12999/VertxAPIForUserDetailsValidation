package com.vertx;

import com.vertex.responsehandlers.APIHandler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Verticle {

    public static void main(String args[]){

        Vertx vertx = Vertx.vertx();
        HttpServer httpVetexServer = vertx.createHttpServer();
        APIHandler apiHandler = new APIHandler();
        Router router = Router.router(vertx);

        router
                .route("/")
                .handler(routingContext -> apiHandler.welcomeHandler(routingContext));
        router
                .post("/validate")
                .handler(routingContext -> apiHandler.validateHandler(routingContext));
        router.
                route()
                .handler(routingContext -> apiHandler.resourceNotFoundHandler(routingContext));
        //Port number on which the application is listening
        httpVetexServer.requestHandler(router::accept).listen(8085);
    }
}
