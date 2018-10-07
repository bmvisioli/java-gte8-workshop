package apis;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import javax.net.ssl.SSLContext;

public class Http {

  public static void main(String[] args) throws Exception {

    /*
        __  __  ______  ______    ____
       / / / / /_  __/ /_  __/   / __ \
      / /_/ /   / /     / /     / /_/ /
     / __  /   / /     / /     / ____/
    /_/ /_/   /_/     /_/     /_/


    Java 9 introduced an experimental HttpClient feature that was later
    made standard by Java 11. It uses HTTP2 by default and integrates to
    async pipeline.
     */

    var client = HttpClient.newBuilder()
        .sslContext(SSLContext.getDefault())
        .version(Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(5))
        .followRedirects(Redirect.ALWAYS)
        .build();

    var request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://api.ipify.org?format=json"))
        .header("Accept", "application/json")
        .build();

    /* Requesting
      - Sync : client.send
      - Async: client.sendAsync
     */
    var futureResponse = client.sendAsync(request, BodyHandlers.ofString());

    futureResponse.whenComplete((resp, ex) -> {
      System.out.println("Response Status Code [" + resp.statusCode() + "]");
      System.out.println("Response Body " + resp.body());
    })
    .get();

  }

}
