import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

public abstract class HttpRequestData {
    private String path;
    private HttpMethod method;
    private Map<String, String> header;

    private HttpRequestData(String path, HttpMethod method, Map<String, String> header) {
        this.path = path;
        this.method = method;
        this.header = header;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeader() {
        return header;
    }
}
