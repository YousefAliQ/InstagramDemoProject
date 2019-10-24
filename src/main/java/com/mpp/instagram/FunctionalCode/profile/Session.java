package com.mpp.instagram.FunctionalCode.profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private String sessionId;
    private Profile profile;

    public Session(LocalDateTime startSession, LocalDateTime endSession) {
        this.startSession = startSession;
        this.endSession = endSession;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Session{" +
                "startSession=" + startSession +
                ", endSession=" + endSession +
                ", sessionId='" + sessionId + '\'' +
                ", profile=" + profile +
                '}';
    }
}
