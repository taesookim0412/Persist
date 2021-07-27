package com.persist.persist.libraries.connections;

import org.springframework.context.annotation.Scope;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

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
        testGetRequest(client);
    }

    //TODO: how to persist?
    private HttpRequest createHttpRequest(String url){
        return HttpRequest.newBuilder()
        .uri(URI.create(url)).build();
    }
    private void createGetRequest(String url, String body, HttpClient client){
        client.sendAsync(createHttpRequest(url), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    private void testGetRequest(HttpClient client){
        this.createGetRequest("https://opentdb.com/api.php?amount=10", "", client);
    }
}
