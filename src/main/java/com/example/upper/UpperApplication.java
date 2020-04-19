package com.example.upper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpperApplication {

	public static void main(String[] args) {
		sun.misc.SignalHandler sh = sig -> System.exit(128 + sig.getNumber());
		sun.misc.Signal.handle(new sun.misc.Signal("INT"), sh);
		sun.misc.Signal.handle(new sun.misc.Signal("TERM"), sh);
		SpringApplication.run(UpperApplication.class, args);
	}
}	
