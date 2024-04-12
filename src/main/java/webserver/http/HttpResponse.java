package webserver.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private String statusLine;
    private HttpHeaders header;
    private byte[] body;

    public HttpResponse(String statusLine, byte[] body) {
        this.statusLine = statusLine;
        this.body = body;
        this.header = new HttpHeaders();
    }

    public HttpResponse() {
        this.statusLine = "";
        this.body = new byte[0];
        this.header = new HttpHeaders();
    }

    public void addBody(byte[] addBody) {
        int len = addBody.length + this.body.length;
        byte[] newBody = new byte[len];
        System.arraycopy(this.body, 0, newBody, 0, this.body.length);
        System.arraycopy(addBody, 0, newBody, this.body.length, addBody.length);
        this.body = newBody;
    }

    public void addHeader(String addHeader) {
        this.header.add(addHeader);
    }

    public void setStatus200() {
        statusLine = "HTTP/1.1 200 OK";
    }

    public void setStatus404() {
        statusLine = "HTTP/1.1 404 Not Found";
    }
    public void setStatus400() {
        statusLine = "HTTP/1.1 400 Bad Request";
    }
    public void setStatus302() {
        statusLine = "HTTP/1.1 302 Found";
    }

    public String getStatusLine() {
        return statusLine;
    }

    public HttpHeaders getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public void send(OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(statusLine+"\r\n");
        for (String headerLine : header.getAllHeader()) {
            dos.writeBytes(headerLine+"\r\n");
        }
        dos.writeBytes("\r\n");
        dos.write(body, 0, body.length);
        dos.flush();
    }
}
