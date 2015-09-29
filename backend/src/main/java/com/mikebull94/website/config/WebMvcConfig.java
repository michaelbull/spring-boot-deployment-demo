package com.mikebull94.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment environment;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String frontend;

		if (environment.acceptsProfiles("development")) {
			String workingDirectory = System.getProperty("user.dir");

			/*
			 * if we are running via gradle bootRun then the working directory is
			 * suffixed with /backend and thus won't find resources correctly
			 */
			if (workingDirectory.endsWith("/backend")) {
				workingDirectory = workingDirectory.substring(0, workingDirectory.lastIndexOf("/backend"));
			}

			/* find front-end build files from the local file system */
			frontend = "file:///" + workingDirectory + "/frontend/build/dist/";
		} else {
			/* find front-end build files from the classpath */
			frontend = "classpath:/";
		}

		registry.addResourceHandler("/**").addResourceLocations(frontend, "classpath:/static/");
	}
}
