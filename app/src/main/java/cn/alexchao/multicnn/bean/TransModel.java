package cn.alexchao.multicnn.bean;

import java.util.ArrayList;

public class TransModel implements java.io.Serializable {
    private ArrayList<String> messages;
    private String mOperation;  // "init" or "pull" or "push"
    private int mRange;
    private float[][][][] mPayload;

    public TransModel() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<String> newMessage) {
        this.messages = newMessage;
    }

    public String getmOperation() {
        return this.mOperation;
    }

    public void setmOperation(String operation) {
        this.mOperation = operation;
    }

    public int getmRange() {
        return mRange;
    }

    public void setmRange(int range) {
        this.mRange = range;
    }

    public float[][][][] getPayload() {
        return this.mPayload;
    }

    public void setPayload(float[][][][] payload) {
        this.mPayload = payload;
    }

}
