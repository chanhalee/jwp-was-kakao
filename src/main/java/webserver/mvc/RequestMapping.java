package webserver.mvc;

import webserver.http.HttpRequest;
import webserver.mvc.controller.Controller;
import webserver.mvc.controller.DefaultController;
import webserver.mvc.controller.TemplateController;
import webserver.mvc.controller.UserController;

public class RequestMapping {
    public static Controller getRequestMapping(HttpRequest request) {
        if (request.getPath().equals("/")) {
            return new DefaultController();
        }
        if (request.getPath().equals("/index.html")) {
            return new TemplateController();
        }
        if (request.getPath().equals("./css/style.css")) {
            return new TemplateController();
        }
        if (request.getPath().equals("/user/create")) {
            return new UserController();
        }
        return new TemplateController();
    }
}
