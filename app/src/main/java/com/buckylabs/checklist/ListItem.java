package com.buckylabs.checklist;


public class ListItem {

    private String item;

    public ListItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "item='" + item + '\'' +
                '}';
    }
}
