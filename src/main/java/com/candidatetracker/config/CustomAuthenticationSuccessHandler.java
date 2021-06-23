package com.candidatetracker.config;

import com.candidatetracker.entity.User;
import com.candidatetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

  private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

  @Autowired
  private UserService userService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException
  {
    String userName = authentication.getName();
		logger.info(">>> userName: " + userName);
    User theUser = userService.findByUserName(userName);

    // now place in the session
    HttpSession session = request.getSession();
    session.setAttribute("user", theUser);

    // alternative: forward to home page
    // response.sendRedirect(request.getContextPath() + "/");
    super.onAuthenticationSuccess(request, response, authentication);
  }


}
