package webserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import utils.IOUtils;

public class HttpRequest {
    private String path;
    private HttpMethod method;
    private HttpHeaders header;
    private Map<String, String> queryParameter;
    private String body;

    public HttpRequest(String path, HttpMethod method, HttpHeaders header, Map<String, String> queryParameter) {
        this.path = path;
        this.method = method;
        this.header = header;
        this.queryParameter = queryParameter;
        body = "";
    }

    public HttpRequest(BufferedReader br) throws IOException, HttpRequestMethodNotSupportedException {
        queryParameter = new HashMap<>();
        header = new HttpHeaders();
        method = null;

        String line = br.readLine();
        String[] firstLine = line.split(" ");

        if (firstLine[0].equals("GET")) {
            method = HttpMethod.GET;
        }
        if (firstLine[0].equals("POST")) {
            method = HttpMethod.POST;
        }
        if (method == null) {
            throw new HttpRequestMethodNotSupportedException("GET, POST 외의 메서드는 지원되지 않음");
        }

        path = firstLine[1];
        if (path.contains("?")) {
            String queryString = URLDecoder.decode(path.substring(path.indexOf('?') + 1), StandardCharsets.UTF_8);
            path = path.substring(0, path.indexOf('?'));
            queryParameter = Arrays.stream(queryString.split("&"))
                .map(it -> it.split("="))
                .collect(Collectors.toMap(it -> it[0], it -> it[1]));
        }

        line = br.readLine();
        while (line!=null && !"".equals(line)) {
            header.add(line);
            line = br.readLine();
        }

        if (header.getHeader("Content-Length") != null) {
            body = br.readLine();
        }

    }

    public String getPath() {
        return this.path;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public HttpHeaders getHeader() {
        return header;
    }

    public Map<String, String> parseBody() {
        String bodyString = URLDecoder.decode(body, StandardCharsets.UTF_8);
        System.out.println(bodyString);
        Map<String, String> parsedBody = Arrays.stream(bodyString.split("&"))
            .map(it -> it.split("="))
            .collect(Collectors.toMap(it -> it[0], it -> it[1]));
        return parsedBody;
    }

    public Map<String, String> getQueryParameter() {
        return queryParameter;
    }

}
