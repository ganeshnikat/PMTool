package com.kanban.pmtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.kanban.pmtool.*")
public class ProjectManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementToolApplication.class, args);
	}

}
