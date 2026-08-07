package com.hragro.backend.dto;

public class ResponseMessage {
    private String message;
    private boolean success;

    // Default constructor
    public ResponseMessage() {}

    // Constructor with parameters
    public ResponseMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}