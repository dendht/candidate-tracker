package com.candidatetracker.dataaccess;

import com.candidatetracker.entity.Candidate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CandidateDaoImpl implements CandidateDao
{
  @Autowired
  // private SessionFactory sessionFactory;
  // replaced SessionFactory with EntityManager to use Hibernate JPA API
  private EntityManager entityManager;

  @Override
  public List<Candidate> getCandidates()
  {
    Session currentSession = entityManager.unwrap(Session.class);
    List<Candidate> candidates = currentSession
        .createQuery("FROM Candidate ORDER BY lastName ASC", Candidate.class)
        .getResultList();
    return candidates;
  }

  @Override
  public void saveCandidate(Candidate candidate)
  {
    Session currentSession = entityManager.unwrap(Session.class);
    currentSession.saveOrUpdate(candidate);
  }

  @Override
  public Candidate getCandidate(int candidateId)
  {
    Session currentSession = entityManager.unwrap(Session.class);
    return currentSession.get(Candidate.class, candidateId);
  }

  @Override
  public void deleteCandidate(int candidateId)
  {
    Session currentSession = entityManager.unwrap(Session.class);
    Query query = currentSession.createQuery("DELETE FROM Candidate WHERE id = :candidateId");
    query.setParameter("candidateId", candidateId);
    query.executeUpdate();
  }

  @Override
  public List<Candidate> searchCandidates(String searchString)
  {
    Session currentSession = entityManager.unwrap(Session.class);
    if (searchString != null && !searchString.trim().isBlank())
    {
      // search by search term
      Query query = currentSession
          .createQuery("FROM Candidate WHERE lower(firstName) LIKE :searchString "
              + "OR lower(lastName) LIKE :searchString ORDER BY lastName ASC", Candidate.class);
      query.setParameter("searchString", "%" + searchString.trim() + "%");
      return query.getResultList();
    }
    else
    {
      // get whole list of candidates
      return currentSession
          .createQuery("FROM Candidate ORDER BY lastName ASC", Candidate.class)
          .getResultList();
    }
  }
}
