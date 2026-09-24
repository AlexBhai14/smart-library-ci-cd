package com.smartlibrary.memberservice;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {
    private final MemberRepository repo;
    public MemberController(MemberRepository repo){this.repo=repo;}
    @GetMapping public List<Member> all(){return repo.findAll();}
    @PostMapping public Member add(@RequestBody Member m){return repo.save(m);}
    @PutMapping("/{id}") public Member update(@PathVariable Long id,@RequestBody Member m){
        return repo.findById(id).map(x->{x.setName(m.getName());x.setEmail(m.getEmail());x.setPhone(m.getPhone());return repo.save(x);}).orElseThrow();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
}
