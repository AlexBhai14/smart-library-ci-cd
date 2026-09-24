package com.smartlibrary.frontendservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.client.RestClient;

@Controller
public class FrontendController {
    @Value("${API_GATEWAY_URL:http://localhost:8091}") String gateway;
    private final RestClient client=RestClient.create();

    @GetMapping("/") public ModelAndView home(){return new ModelAndView("index");}
    @GetMapping("/admin") public ModelAndView admin(){return new ModelAndView("admin");}

    @ResponseBody @GetMapping("/api/books")
    public String books(@RequestParam(required=false) String q){
        String url=gateway+"/gateway/books"+(q==null?"":"?q="+q);
        return client.get().uri(url).retrieve().body(String.class);
    }
    @ResponseBody @GetMapping("/api/members")
    public String members(){return client.get().uri(gateway+"/gateway/members").retrieve().body(String.class);}
    @ResponseBody @GetMapping("/api/issues")
    public String issues(){return client.get().uri(gateway+"/gateway/issues").retrieve().body(String.class);}

    @ResponseBody @PostMapping("/api/auth/login")
    public String login(@RequestBody String body){
        return client.post().uri("http://auth-service:8092/api/auth/login")
                .header("Content-Type","application/json").body(body).retrieve().body(String.class);
    }

    @ResponseBody @PostMapping("/api/books")
    public String addBook(@RequestBody String body){
        return client.post().uri("http://book-service:8093/api/books").header("Content-Type","application/json").body(body).retrieve().body(String.class);
    }
    @ResponseBody @PostMapping("/api/members")
    public String addMember(@RequestBody String body){
        return client.post().uri("http://member-service:8094/api/members").header("Content-Type","application/json").body(body).retrieve().body(String.class);
    }
    @ResponseBody @PostMapping("/api/issues")
    public String issue(@RequestBody String body){
        return client.post().uri("http://issue-return-service:8095/api/issues").header("Content-Type","application/json").body(body).retrieve().body(String.class);
    }
    @ResponseBody @PostMapping("/api/issues/{id}/return")
    public String ret(@PathVariable Long id){
        return client.post().uri("http://issue-return-service:8095/api/issues/"+id+"/return").retrieve().body(String.class);
    }
    @ResponseBody @PostMapping("/api/books/{id}/reserve")
    public String reserve(@PathVariable Long id){
        return client.post().uri("http://book-service:8093/api/books/"+id+"/reserve").retrieve().body(String.class);
    }
    @ResponseBody @PostMapping("/api/books/{id}/release")
    public String release(@PathVariable Long id){
        return client.post().uri("http://book-service:8093/api/books/"+id+"/release").retrieve().body(String.class);
    }
}
