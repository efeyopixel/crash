package com.efeyopixel.crash.service;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PasswordServiceImpl implements PasswordAuthenticator {
    /**
     * Hardcoded username/password... should be checked against a database or other repository such as
     * Active Directory or LDAP
     * @param username
     * @param password
     * @param serverSession
     * @return
     * @throws PasswordChangeRequiredException
     * @throws AsyncAuthException
     */
    @Override
    public boolean authenticate(String username, String password, ServerSession serverSession) throws PasswordChangeRequiredException, AsyncAuthException {
        if (username.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("admin")) {
            log.info("successful login by - {}", username);
            return true;
        }
        return false;
    }

    @Override
    public boolean handleClientPasswordChangeRequest(ServerSession session, String username, String oldPassword, String newPassword) {
        return false;
    }
}
