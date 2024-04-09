package webserver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.processing.Filer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.function.ServerRequest;
import org.yaml.snakeyaml.reader.StreamReader;

import utils.FileIoUtils;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            DataOutputStream dos = new DataOutputStream(out);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            byte[] body = pathLinkByCondition(path);
            Map<String, String> stringStringMap = parseHeaders(br);

            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private static byte[] pathLinkByCondition(String firstLine) throws IOException, URISyntaxException {
        String[] firstLines = firstLine.split(" ");
        byte[] body = FileIoUtils.loadFileFromClasspath("./templates"+ path);
        return body;
    }

    // 처음줄을 통해 어디로 갈지를(path) return 하는 메서드

    // 두번째 ~ 마지막까지 해서 Map으로 ":" 나눠서 return 하는 메서드

    // private String getPath(String firstLine) {
    //     String[] firstLines = firstLine.split(" ");
    //     System.out.println(firstLines[1]);
    //     return firstLines[1];
    // }

    private Map<String, String> parseHeaders(BufferedReader bufferedReader) throws IOException {
        Map<String, String> headerMap = new HashMap<>();
        String headerLine = bufferedReader.readLine();

        while (!"".equals(headerLine)) {
            String[] headerSplit = headerLine.split(": ");
            headerMap.put(headerSplit[0], headerSplit[1]);
            headerLine = bufferedReader.readLine();
        }

        headerMap.forEach((key, value) -> System.out.println(key + ": " + value));
        return headerMap;
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
