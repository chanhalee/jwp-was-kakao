package webserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import webserver.http.HttpRequest;

public class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        response.getHeaders().entrySet().forEach(System.out::println);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void request_resttemplate2() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/index.html", String.class);
        response.getHeaders().entrySet().forEach(System.out::println);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @ValueSource(strings = {"src/test/java/testFiles/getIndexhtml.txt"})
    @ParameterizedTest
    void getIndexHtmlTest(final File file) throws IOException, HttpRequestMethodNotSupportedException {
        final FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);
        final HttpRequest request = new HttpRequest(br);
        Assertions.assertThat(request.getPath()).isEqualTo("/index.html");
        Assertions.assertThat(request.getMethod()).isEqualTo(HttpMethod.GET);
    }
    @ValueSource(strings = {"src/test/java/testFiles/getCss.txt"})
    @ParameterizedTest
    void getStyleCssTest(final File file) throws IOException, HttpRequestMethodNotSupportedException {
        final FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);
        final HttpRequest request = new HttpRequest(br);
        Assertions.assertThat(request.getPath()).isEqualTo("./css/style.css");
        Assertions.assertThat(request.getMethod()).isEqualTo(HttpMethod.GET);
    }
    @ValueSource(strings = {"src/test/java/testFiles/queryString.txt"})
    @ParameterizedTest
    void queryStringTest(final File file) throws IOException, HttpRequestMethodNotSupportedException {
        final FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);
        final HttpRequest request = new HttpRequest(br);
        Map<String, String> queryParameter = request.getQueryParameter();
        Assertions.assertThat(request.getPath()).isEqualTo("/user/create");
        Assertions.assertThat(request.getMethod()).isEqualTo(HttpMethod.GET);
        Assertions.assertThat(queryParameter.get("userId")).isEqualTo("cu");
        Assertions.assertThat(queryParameter.get("password")).isEqualTo("password");
        Assertions.assertThat(queryParameter.get("name")).isEqualTo("이동규");
        Assertions.assertThat(queryParameter.get("email")).isEqualTo("brainbackdoor@gmail.com");
    }
    @ValueSource(strings = {"src/test/java/testFiles/postBody.txt"})
    @ParameterizedTest
    void PostBodyTest(final File file) throws IOException, HttpRequestMethodNotSupportedException {
        final FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);
        final HttpRequest request = new HttpRequest(br);
        Map<String, String> parsedBody = request.parseBody();
        Assertions.assertThat(request.getPath()).isEqualTo("/user/create");
        Assertions.assertThat(request.getMethod()).isEqualTo(HttpMethod.POST);
        Assertions.assertThat(parsedBody.get("userId")).isEqualTo("cu");
        Assertions.assertThat(parsedBody.get("password")).isEqualTo("password");
        Assertions.assertThat(parsedBody.get("name")).isEqualTo("이동규");
        Assertions.assertThat(parsedBody.get("email")).isEqualTo("brainbackdoor@gmail.com");
    }
}
