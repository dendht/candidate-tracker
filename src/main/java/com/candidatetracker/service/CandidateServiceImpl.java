package com.candidatetracker.service;

import com.candidatetracker.dataaccess.CandidateDao;
import com.candidatetracker.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService
{
  @Autowired
  private CandidateDao candidateDao;

  @Override
  @Transactional
  public List<Candidate> getCandidates()
  {
    return candidateDao.getCandidates();
  }

  @Override
  @Transactional
  public void saveCandidate(Candidate candidate)
  {
    candidateDao.saveCandidate(candidate);
  }

  @Override
  @Transactional
  public Candidate getCandidate(int candidateId)
  {
    return candidateDao.getCandidate(candidateId);
  }

  @Override
  @Transactional
  public void deleteCandidate(int candidateId)
  {
    candidateDao.deleteCandidate(candidateId);
  }

  @Override
  @Transactional
  public List<Candidate> searchCandidates(String searchString)
  {
    return candidateDao.searchCandidates(searchString);
  }
}
