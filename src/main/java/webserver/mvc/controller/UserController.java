package webserver.mvc.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import db.DataBase;
import model.User;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public class UserController extends AbstractController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        Map<String, String> parsedBody = request.getQueryParameter();
        String userId = parsedBody.getOrDefault("userId", "");
        String password = parsedBody.getOrDefault("password", "");
        String name = parsedBody.getOrDefault("name", "");
        String email = parsedBody.getOrDefault("email", "");
        DataBase.addUser(new User(userId, password, name, email));
        response.setStatus302();
        response.addHeader("Location: http://localhost:8080/index.html");
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        Map<String, String> parsedBody = request.parseBody();
        String userId = parsedBody.getOrDefault("userId", "");
        String password = parsedBody.getOrDefault("password", "");
        String name = parsedBody.getOrDefault("name", "");
        String email = parsedBody.getOrDefault("email", "");
        DataBase.addUser(new User(userId, password, name, email));
        response.setStatus302();
        response.addHeader("Location: http://localhost:8080/index.html");
    }
}
