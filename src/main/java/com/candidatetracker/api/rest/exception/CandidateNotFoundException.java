package com.candidatetracker.api.rest.exception;

public class CandidateNotFoundException extends RuntimeException
{
  public CandidateNotFoundException()
  {
    super();
  }

  public CandidateNotFoundException(String message)
  {
    super(message);
  }

  public CandidateNotFoundException(Throwable cause)
  {
    super(cause);
  }

  public CandidateNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  protected CandidateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
