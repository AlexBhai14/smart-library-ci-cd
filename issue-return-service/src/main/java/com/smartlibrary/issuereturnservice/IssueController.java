package com.smartlibrary.issuereturnservice;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin
public class IssueController {
    private final IssueRepository repo;
    public IssueController(IssueRepository repo){this.repo=repo;}
    @GetMapping public List<IssueRecord> all(){return repo.findAll();}
    @PostMapping public IssueRecord issue(@RequestBody IssueRequest req){
        IssueRecord r=new IssueRecord();
        r.setMemberId(req.memberId()); r.setBookId(req.bookId());
        LocalDate d=LocalDate.now(); r.setIssueDate(d); r.setDueDate(d.plusDays(7));
        r.setStatus("ISSUED"); return repo.save(r);
    }
    @PostMapping("/{id}/return") public IssueRecord returnBook(@PathVariable Long id){
        IssueRecord r=repo.findById(id).orElseThrow();
        r.setReturnDate(LocalDate.now()); r.setStatus("RETURNED"); return repo.save(r);
    }
    public record IssueRequest(Long memberId,Long bookId){}
}
