package com.candidatetracker.api.rest.exception;

import com.candidatetracker.entity.error.CandidateErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CandidateRestExceptionHandler
{
  @ExceptionHandler
  public ResponseEntity<CandidateErrorResponse> handleNotFoundException(CandidateNotFoundException ex)
  {
    CandidateErrorResponse response = new CandidateErrorResponse();
    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.setMessage(ex.getMessage());
    response.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<CandidateErrorResponse> handleOtherExceptions(Exception ex)
  {
    CandidateErrorResponse response = new CandidateErrorResponse();

    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setMessage(ex.getMessage());
    response.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
