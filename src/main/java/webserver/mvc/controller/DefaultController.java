package webserver.mvc.controller;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.mvc.controller.AbstractController;

public class DefaultController extends AbstractController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        response.setStatus200();
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        response.setStatus400();
    }
}
