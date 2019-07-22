package com.packs.counproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages ={"com.packs.counproc.repositories"})
@SpringBootApplication
public class CounprocApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounprocApplication.class, args);
	}

}
