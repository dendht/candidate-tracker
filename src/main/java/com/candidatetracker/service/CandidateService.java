package com.candidatetracker.service;

import com.candidatetracker.entity.Candidate;

import java.util.List;

public interface CandidateService
{
  public List<Candidate> getCandidates();

  public void saveCandidate(Candidate candidate);

  public Candidate getCandidate(int candidateId);

  public void deleteCandidate(int candidateId);

  public List<Candidate> searchCandidates(String searchString);
}
