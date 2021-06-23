package com.candidatetracker.controller;

import com.candidatetracker.entity.Candidate;
import com.candidatetracker.entity.User;
import com.candidatetracker.service.CandidateService;
import com.candidatetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateController
{
  @Autowired
  private CandidateService candidateService;

  @Autowired
  private UserService userService;

  private void injectUserData(Model model)
  {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    User currentUser = userService.findByUserName(currentPrincipalName);
    if (currentUser != null)
    {
      model.addAttribute("userName",
          currentUser.getFirstName() + " " + currentUser.getLastName());
      model.addAttribute("userEmail", currentUser.getEmail());
    }
    else
    {
      model.addAttribute("userName", "anonymous");
      model.addAttribute("userEmail", "unknown");
    }
  }

  @GetMapping("/list")
  public String listCandidates(Model model)
  {
    List<Candidate> candidates = candidateService.getCandidates();
    model.addAttribute("candidates", candidates);

    // add user info to the page
    injectUserData(model);

    return "list-candidates";
  }

  @GetMapping("/showAddForm")
  public String addCandidateForm(Model model)
  {
    Candidate candidate = new Candidate();
    model.addAttribute("candidate", candidate);

    return "candidate-add-form";
  }

  @PostMapping("/saveCandidate")
  public String saveCandidate(@ModelAttribute("candidate") Candidate candidate)
  {
    candidateService.saveCandidate(candidate);
    return "redirect:/candidate/list";
  }

  @GetMapping("/showUpdateForm")
  public String showFormForUpdate(@RequestParam("candidateId") int candidateId,
      Model model)
  {
    Candidate candidate = candidateService.getCandidate(candidateId);
    model.addAttribute("candidate", candidate);
    return "candidate-add-form";
  }

  @GetMapping("/deleteCandidate")
  public String deleteCandidate(@RequestParam("candidateId") int candidateId)
  {
    candidateService.deleteCandidate(candidateId);
    return "redirect:/candidate/list";
  }

  @GetMapping("/search")
  public String searchCandidate(@RequestParam("searchName") String searchString,
      Model model)
  {
    List<Candidate> candidates = candidateService.searchCandidates(searchString);
    // set found candidates to model
    model.addAttribute("candidates", candidates);
    // set string back to model, to save previous user input in "input" field
    model.addAttribute("searchName", searchString);

    // add user info to the page
    injectUserData(model);

    return "list-candidates";
  }
}
