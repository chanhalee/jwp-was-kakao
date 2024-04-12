package webserver.mvc.controller;

import utils.FileIoUtils;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.mvc.controller.AbstractController;

public class TemplateController extends AbstractController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response){
        try {
            String path = request.getPath();
            System.out.println(path.charAt(0));
            if (path.charAt(0)=='/') {
                path = "./templates" + path;
            }
            byte[] body = FileIoUtils.loadFileFromClasspath(path);
            response.addBody(body);
            response.addHeader("Content-Type: text/html;charset=utf-8");
            response.addHeader("Content-Length: "+body.length);
            response.setStatus200();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus404();
        }

    }


    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        response.setStatus400();
    }

}
