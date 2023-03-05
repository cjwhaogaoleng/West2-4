package com.example.myapplication.projectJava.UserResponds;

import java.sql.Blob;

public class Proposer {
    private int proposerId;
    private String proposerName;
    private int raisedFund;
    private Blob image;
    private int projectId;

    public Proposer() {
    }

    public Proposer(int proposerId, String proposerName, int raisedFund, Blob image, int projectId) {
        this.proposerId = proposerId;
        this.proposerName = proposerName;
        this.raisedFund = raisedFund;
        this.image = image;
        this.projectId = projectId;
    }

    public int getProposerId() {
        return proposerId;
    }

    public void setProposerId(int proposerId) {
        this.proposerId = proposerId;
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public int getRaisedFund() {
        return raisedFund;
    }

    public void setRaisedFund(int raisedFund) {
        this.raisedFund = raisedFund;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Proposer{" +
                "proposerId=" + proposerId +
                ", proposerName='" + proposerName + '\'' +
                ", raisedFund=" + raisedFund +
                ", image=" + image +
                ", projectId=" + projectId +
                '}';
    }
}
