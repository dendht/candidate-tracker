package com.candidatetracker.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyCrmMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
  @Override
  protected Class<?>[] getRootConfigClasses()
  {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[] {CRMAppConfig.class};
  }

  @Override
  protected String[] getServletMappings()
  {
    return new String[] {"/"};
  }

}
