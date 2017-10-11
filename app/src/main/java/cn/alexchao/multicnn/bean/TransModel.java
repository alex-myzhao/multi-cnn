package cn.alexchao.multicnn.bean;

import java.util.ArrayList;

public class TransModel implements java.io.Serializable {
    // debug only
    private ArrayList<String> messages;

    // mark
    private String mOperation;  // "init" or "pull" or "push"
    private boolean mIsReq;

    // data
    private int[] mRange;  // row index, [mRange[0], mRange[1])
    private float[][][][] mPayload;  // number, channel, height, width

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

    public boolean getisReq() {
        return this.mIsReq;
    }

    public void setisReq(boolean isReq) {
        this.mIsReq = isReq;
    }

    public int[] getmRange() {
        return mRange;
    }

    public void setmRange(int[] range) {
        this.mRange = range;
    }

    public float[][][][] getPayload() {
        return this.mPayload;
    }

    public void setPayload(float[][][][] payload) {
        this.mPayload = payload;
    }

}
