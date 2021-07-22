package com.persist.persist;

import com.persist.persist.libraries.connections.Connections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistApplication.class, args);
		new Connections();
	}

}
