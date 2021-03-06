package com.lambdaschool.expat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Starting class for testing
 */
@EnableWebMvc
// @EnableJpaAuditing
@SpringBootApplication
public class ExpatApplication
{
    private static final Logger logger = LoggerFactory.getLogger(com.lambdaschool.expat.ExpatApplication.class);

    private static boolean stop = false;

    @Autowired
    private static Environment env;

    private static void checkEnvironmentVariable(String envvar)
    {
        if (System.getenv(envvar) == null)
        {
            logger.error("Environment Variable " + envvar + " missing");
            stop = true;
        }
    }

    public static void main(String[] args)
    {
        checkEnvironmentVariable("OAUTHCLIENTID");
        checkEnvironmentVariable("OAUTHCLIENTSECRET");

        if (!stop)
        {
            ApplicationContext ctx = SpringApplication.run(com.lambdaschool.expat.ExpatApplication.class,
                                                           args);

            DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        }
    }

}
