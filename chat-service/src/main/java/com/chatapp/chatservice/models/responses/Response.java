package com.chatapp.chatservice.models.responses;

import java.util.Date;

public class Response {
    private Object result;
    private Date apiCallStartedAt;
    private Date apiCallEndedAt;
    private String errorMessage;

    public Response(Date apiCallStartedAt) {
        this.apiCallStartedAt = apiCallStartedAt;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Date getApiCallStartedAt() {
        return apiCallStartedAt;
    }

    public void setApiCallStartedAt(Date apiCallStartedAt) {
        this.apiCallStartedAt = apiCallStartedAt;
    }

    public Date getApiCallEndedAt() {
        return apiCallEndedAt;
    }

    public void setApiCallEndedAt(Date apiCallEndedAt) {
        this.apiCallEndedAt = apiCallEndedAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
