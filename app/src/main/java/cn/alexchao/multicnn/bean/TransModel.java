package cn.alexchao.multicnn.bean;

import java.util.ArrayList;

public class TransModel implements java.io.Serializable {
    private ArrayList<String> messages;

    public TransModel() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<String> newMessage) {
        this.messages = newMessage;
    }
}
