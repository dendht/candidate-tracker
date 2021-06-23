package com.candidatetracker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.candidatetracker")
@EnableTransactionManagement
// TODO: move to Spring Data JPA
// @EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class CRMAppConfig implements WebMvcConfigurer
{
  // define logger
  private static final Logger logger = Logger.getLogger(CRMAppConfig.class.getName());

  //  Alternative solution, without usage of @PropertySource and Environment env
  //  @Value("${spring.datasource.url}")
  //  private String jdbcUrl;
  //  @Value("${spring.datasource.username}")
  //  private String username;
  //  @Value("${spring.datasource.password}")
  //  private String password;

  @Autowired
  private Environment env;

  // configure resources
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry)
  {
    registry.addResourceHandler("/css/**")
        .addResourceLocations("/WEB-INF/css/");
    registry.addResourceHandler("/images/**")
        .addResourceLocations("/WEB-INF/images/")
        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
  }

  // define a bean for ViewResolver
  @Bean
  public ViewResolver viewResolver()
  {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/view/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  // Configuration of c3p0, sessionFactory, data source etc

  @Bean
  public LocalSessionFactoryBean sessionFactory()
  {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.candidatetracker.entity");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public DataSource dataSource()
  {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();

    // log connection props
    logger.info(">>> JDBC Url: " + env.getProperty("spring.datasource.url"));
    logger.info(">>> JDBC User: " + env.getProperty("spring.datasource.username"));

    // set connection props
    dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUser(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));

    // set connection pool props
    // usually auto-mapped from props file, for example as hibernate.c3p0.min_size/max_size
    dataSource.setInitialPoolSize(getIntProperty("hibernate.c3p0.initialPoolSize"));

    return dataSource;
  }

  private int getIntProperty(String propName)
  {
    String property = env.getProperty(propName);
    if (property == null)
    {
      return 0;
    }
    return Integer.parseInt(property);
  }

  private Properties hibernateProperties()
  {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty(
        "spring.jpa.hibernate.ddl-auto", "update");
    hibernateProperties.setProperty(
        "hibernate.show_sql", "true");
    hibernateProperties.setProperty(
        "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

    return hibernateProperties;
  }

  // TODO: beans for Spring Data JPA

  //  @Bean
  //  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
  //
  //    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
  //    vendorAdapter.setGenerateDdl(true);
  //
  //    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
  //    factory.setJpaVendorAdapter(vendorAdapter);
  //    factory.setPackagesToScan("com.candidateTracker.entity");
  //    factory.setDataSource(dataSource());
  //    return factory;
  //  }
  //
  //  @Bean
  //  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
  //
  //    JpaTransactionManager txManager = new JpaTransactionManager();
  //    txManager.setEntityManagerFactory(entityManagerFactory);
  //    return txManager;
  //  }
}
