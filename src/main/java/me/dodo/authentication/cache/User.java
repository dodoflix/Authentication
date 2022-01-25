package me.dodo.authentication.cache;

import java.util.UUID;

public class User {
    UUID _UUID;
    boolean _loggedIn;
    String _password;

    public User(UUID UUID, boolean loggedIn, String password) {
        _UUID = UUID;
        _loggedIn = loggedIn;
        _password = password;
    }

    public UUID getUUID() {
        return _UUID;
    }

    public boolean getLoggedIn() {
        return _loggedIn;
    }

    public String getPassword() {
        return _password;
    }

    public void setUUID(UUID UUID) {
        _UUID = UUID;
    }

    public void setLoggedIn(boolean loggedIn) {
        _loggedIn = loggedIn;
    }

    public void setPassword(String password) {
        _password = password;
    }
}
