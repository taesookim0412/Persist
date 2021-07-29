package com.persist.persist.libraries.connections;

import org.springframework.context.annotation.Scope;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

@Scope("singleton")
public class Connections{
    //{ index : Slave }
    public static HashMap<String, Slave> slaves = new HashMap<String, Slave>();
    public Connections(){
        String persistUrl = "socket/persist";
        //#1: Groupshare
        slaves.put("0", new Slave("http://54.177.13.144/", persistUrl));
        //#2: Exp Con,
        slaves.put("1", new Slave("http://54.176.218.106/", persistUrl));
        //#3: TkBlog,
        slaves.put("2", new Slave("http://50.18.238.237/", persistUrl));
        //#4: Annotation,
        slaves.put("3", new Slave("http://54.215.97.177/", persistUrl));
        //#5: Portfolio (react),
        slaves.put("4", new Slave("http://54.193.14.94/", persistUrl));
        initializeExampleRequest();
    }
    private void initializeExampleRequest(){
        HttpClient client = HttpClient.newHttpClient();
        testLocalGetRequest(client);
    }



    //The effect of not setting a timeout is the same as setting an infinite Duration, i.e. block forever.
    private HttpRequest createHttpRequest(String url){
        return HttpRequest.newBuilder()
        .uri(URI.create(url)).build();
    }

    private void createGetRequest(String url, String body, HttpClient client) {
        try {
            HttpResponse<InputStream> response = client.sendAsync(createHttpRequest(url),  HttpResponse.BodyHandlers.ofInputStream())
                    .thenApply(data -> {
                        System.out.println(data);
                        return data;
                    }).get()
            ;
            InputStream stream = response.body();
            while (true){
                final byte[] buffer = new byte[24];
                int i = 0;
                while (true){
                    if (i == buffer.length || stream.available() == 0) {
                        break;
                    }
                    int data = stream.read();
//                    System.out.println(data);
                    //when does this happen
                    if (data == -1){
                        break;
                    }
                    else{
                        buffer[i] = (byte) data;
                        i += 1;
                    }
                }
                //if there is a response
                if (i > 0){
                    int j = buffer.length -1;
                    while (j >= 0){
                        //breaks at last character
                        if (buffer[j] != 0){
                            break;
                        }
//                        System.out.println("!" + buffer[j]);
                        j -= 1;
                    }
                    //slice at index after last char
                    j += 1;
                    byte[] fullResponse = Arrays.copyOfRange(buffer, 0, j);
                    String jsonResponse = new String(fullResponse, StandardCharsets.UTF_8);
                    System.out.println(jsonResponse);
                }

                Thread.sleep(1000);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*private void createGetRequest(String inputUrl, String body, HttpClient client) {
        try {
            final URL url = new URL(inputUrl);
            final InputStream istream = url.openStream();

            final byte[] buffer = new byte[1024 * 8];
            while (true) {
                final int len = istream.read(buffer);
                if (len <= 0) {
                    break;
                }
                System.out.println(Arrays.toString(buffer));
            }
        }
        catch (Exception e){

    }
}*/

    //Gets 40 bytes from a single get request
//    private void createGetRequest(String url, String body, HttpClient client) {
//        try {
//            HttpResponse<InputStream> response = client.sendAsync(createHttpRequest(url), HttpResponse.BodyHandlers.ofInputStream()).get();
//            InputStream stream = response.body();
//            while(true){
//                byte[] test = stream.readNBytes(40);
//                System.out.println(new String(test, StandardCharsets.UTF_8));
//                Thread.sleep(1000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void testGetRequest(HttpClient client){
        this.createGetRequest("https://opentdb.com/api.php?amount=10", "", client);
    }

    private void testLocalGetRequest(HttpClient client) {
        this.createGetRequest("http://localhost:8090/socket/persist", "", client);
    }

}
