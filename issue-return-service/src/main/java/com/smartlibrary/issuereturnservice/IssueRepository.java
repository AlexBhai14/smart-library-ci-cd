package com.smartlibrary.issuereturnservice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IssueRepository extends JpaRepository<IssueRecord,Long>{ List<IssueRecord> findByStatus(String status); }
