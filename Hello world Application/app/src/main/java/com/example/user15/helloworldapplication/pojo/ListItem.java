package com.example.user15.helloworldapplication.pojo;

public class ListItem {

    private int flagImage;
    private String flagText;

    public ListItem(int flagImage, String flagText) {
        this.flagImage = flagImage;
        this.flagText = flagText;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public String getFlagText() {
        return flagText;
    }
}
