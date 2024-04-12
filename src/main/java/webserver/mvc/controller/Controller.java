package webserver.mvc.controller;

import org.springframework.http.HttpMethod;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public interface Controller {
    public void service(HttpRequest request, HttpResponse response);
}

abstract class AbstractController implements Controller{
    public void service(HttpRequest request, HttpResponse response){
        HttpMethod method = request.getMethod();
        if (method.matches("GET")) {
            doGet(request, response);
        }
        if (method.matches("POST")) {
            doPost(request, response);
        }
    }


    protected abstract void doGet(HttpRequest request, HttpResponse response);
    protected abstract void doPost(HttpRequest request, HttpResponse response);
}