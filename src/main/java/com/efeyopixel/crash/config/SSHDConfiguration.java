package com.efeyopixel.crash.config;

import com.efeyopixel.crash.service.PasswordServiceImpl;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class SSHDConfiguration {

    @Value("${ssh.port:22}")
    Integer sshPort;

    @Bean
    SshServer sshServer() throws IOException {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(sshPort);
        sshd.setPasswordAuthenticator(new PasswordServiceImpl());
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("src/main/resources/hostkey.ser")));
        sshd.setShellFactory(new ProcessShellFactory("C:\\Program Files\\Git\\usr\\bin\\bash.exe", "-i", "-l"));
        sshd.start();
        return sshd;
    }
}
