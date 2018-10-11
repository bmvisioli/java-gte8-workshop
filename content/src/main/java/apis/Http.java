package apis;

import static boilerplate.Boilerplate.println;

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
    made standard by Java 11. It uses HTTP2 by default and integrates well
    to async pipelines.
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
        .timeout(Duration.ofSeconds(5))
        .build();

    /* Requesting
      - Sync : client.send
      - Async: client.sendAsync
     */
    var futureResponse = client.sendAsync(request, BodyHandlers.ofString());

    /*
      Response is HttpResponse<Type> where type depends on the BodyHandler you used before
      BodyHandlers.ofString()   -> HttpResponse<String>
      BodyHandlers.discarding() -> HttpResponse<Void>
      BodyHandlers.ofLines()    -> HttpResponse<Stream<String>>
     */
    futureResponse.whenComplete((resp, ex) -> {
      println("Response Status Code [" + resp.statusCode() + "]");
      println("Response Body " + resp.body());
    })
    .get();

  }

}
