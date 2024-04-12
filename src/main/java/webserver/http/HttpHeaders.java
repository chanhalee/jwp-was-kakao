package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpHeaders {
    private final Map<String, String> headers = new HashMap<>();

    public void add(String header) {
        final String[] split = header.split(": ");
        headers.put(split[0], split[1]);
    }

    public String getHeader(String key) {
        return headers.get(key);
    }
    public List<String> getAllHeader() {
        return headers.entrySet().stream().map(entry -> entry.getKey() +": "+ entry.getValue()).collect(Collectors.toList());
    }
}
