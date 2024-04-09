import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

public class HttpRequestGetData extends HttpRequestData {
    private final Map<String, String> queryParameter;

    public HttpRequestGetData(Map<String, String> queryParameter) {
        this.queryParameter = queryParameter;
    }

    public static HttpRequestData createHttpRequestData(InputStream in) throws IOException {
        Map<String, String> queryParameters = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String firstLine = br.readLine();
        String[] firstLines = firstLine.split(" ");
        String method = firstLines[0];
        String path = firstLines[1];

        if (path.contains("?")) {
            String[] split = path.split("\\?");
            queryParameters.put(split[0], split[1]);
        }

        Map<String, String> header = new HashMap<>();
        String headerLine = br.readLine();

        while (!"".equals(headerLine)) {
            String[] headerSplit = headerLine.split(": ");
            header.put(headerSplit[0], headerSplit[1]);
            headerLine = br.readLine();
        }

        return new HttpRequestData(path, HttpMethod.resolve(method), header);
    }
}
