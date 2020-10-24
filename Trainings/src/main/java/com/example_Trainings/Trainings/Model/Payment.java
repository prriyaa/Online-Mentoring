package com.example_Trainings.Trainings.Model;

import org.springframework.data.annotation.Id;

public class Payment {

        private long Id;
        private long MentorId;
        private long  TrainingId;
        private String Txntype;
        private double Amount;
        private String datetime;
        private String Remarks;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getMentorId() {
        return MentorId;
    }

    public void setMentorId(long mentorId) {
        MentorId = mentorId;
    }

    public long getTrainingId() {
        return TrainingId;
    }

    public void setTrainingId(long trainingId) {
        TrainingId = trainingId;
    }

    public String getTxntype() {
        return Txntype;
    }

    public void setTxntype(String txntype) {
        Txntype = txntype;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
