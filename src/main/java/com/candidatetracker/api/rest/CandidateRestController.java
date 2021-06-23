package com.candidatetracker.api.rest;

import com.candidatetracker.api.rest.exception.CandidateNotFoundException;
import com.candidatetracker.entity.Candidate;
import com.candidatetracker.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateRestController
{
  @Autowired
  private CandidateService candidateService;

  @GetMapping("/candidates")
  public List<Candidate> getCandidates()
  {
    return candidateService.getCandidates();
  }

  @GetMapping("/candidates/{candidateId}")
  public Candidate getCandidate(@PathVariable int candidateId)
  {
    Candidate candidate = candidateService.getCandidate(candidateId);
    if (candidate == null)
    {
      throw new CandidateNotFoundException("Unable to find candidate with id: " + candidateId);
    }
    return candidate;
  }

  @PostMapping("/candidates")
  public Candidate addNewCandidate(@RequestBody Candidate candidate)
  {
    // force to store new candidate by giving id as "0"
    candidate.setId(0);
    // save new candidate
    candidateService.saveCandidate(candidate);
    return candidate;
  }

  @PutMapping("/candidates")
  public Candidate updateCandidate(@RequestBody Candidate candidate)
  {
    candidateService.saveCandidate(candidate);
    return candidate;
  }

  @DeleteMapping("/candidates/{candidateId}")
  public String deleteCandidate(@PathVariable int candidateId)
  {
    Candidate candidate = candidateService.getCandidate(candidateId);
    if (candidate == null)
    {
      throw new CandidateNotFoundException("Unable to find candidate with id: " + candidateId);
    }

    candidateService.deleteCandidate(candidateId);
    return "Deleted candidate with id: " + candidateId;
  }
}
