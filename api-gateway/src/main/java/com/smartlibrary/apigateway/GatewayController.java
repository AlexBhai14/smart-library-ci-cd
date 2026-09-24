package com.smartlibrary.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestClient client = RestClient.create();

    @Value("${AUTH_URL:http://localhost:8092}")
    String auth;

    @Value("${BOOK_URL:http://localhost:8093}")
    String book;

    @Value("${MEMBER_URL:http://localhost:8094}")
    String member;

    @Value("${ISSUE_URL:http://localhost:8095}")
    String issue;

    @PostMapping("/auth/login")
    public String login(@RequestBody String body) {
        return client.post()
                .uri(auth + "/api/auth/login")
                .header("Content-Type", "application/json")
                .body(body)
                .retrieve()
                .body(String.class);
    }

    @GetMapping("/books")
    public String books(@RequestParam(required = false) String q) {
        String url = book + "/api/books" + (q == null ? "" : "?q=" + q);
        return client.get().uri(url).retrieve().body(String.class);
    }

    @GetMapping("/members")
    public String members() {
        return client.get().uri(member + "/api/members")
                .retrieve().body(String.class);
    }

    @GetMapping("/issues") 
    public String issues() {
        return client.get().uri(issue + "/api/issues")
                .retrieve().body(String.class);
    }
}
