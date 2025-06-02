package ws;

import jakarta.xml.ws.Endpoint;

public class ServerJWS {
    public static void main(String[] args) {
        // déployer le projet dans ce lien
        String url = "http://0.0.0.0:9090/";
        Endpoint.publish(url, new BanqueService());
        System.out.println("Web service déployé sur l url " + url);
    }
}