package com.efeyopixel.crash;

import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.server.SshServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
@SpringBootApplication(proxyBeanMethods = false)
public class CrashApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CrashApplication.class, args);

		SshServer sshServer = run.getBean(SshServer.class);
		try {
			Scanner scanner = new Scanner(System.in);
			boolean quit = false;
			while (!quit) {
				log.info("\nTo stop application enter (\"quit\" to exit, \"?\" for help):");
				String command = scanner.nextLine();
				switch (command) {
					case "?":
					case "help":
						log.info(
								"q[uit]  Stop application and and exit\n" +
								"?       Prints this message\n"
						);
						break;
					case "quit":
					case "q":
						log.info("exiting....");
						sshServer.stop();
						quit = true;
						break;
					default:
						log.info("Invalid command - enter \"?\" for additional help");
						break;
				}
			}
			log.info("server exited");
		} catch (IOException e) {
			log.error("caught IOException ", e);
			System.exit(1);
		}
	}
}
