package com.smartlibrary.issuereturnservice;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class IssueRecord {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long memberId; private Long bookId;
    private LocalDate issueDate; private LocalDate dueDate; private LocalDate returnDate;
    private String status;
    public IssueRecord(){}
    public Long getId(){return id;} public Long getMemberId(){return memberId;} public void setMemberId(Long v){memberId=v;}
    public Long getBookId(){return bookId;} public void setBookId(Long v){bookId=v;}
    public LocalDate getIssueDate(){return issueDate;} public void setIssueDate(LocalDate v){issueDate=v;}
    public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;}
    public LocalDate getReturnDate(){return returnDate;} public void setReturnDate(LocalDate v){returnDate=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
}
