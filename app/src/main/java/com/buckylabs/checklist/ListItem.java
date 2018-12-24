package com.buckylabs.checklist;


public class ListItem {

    private String item;
    private boolean isItemChecked;

    public ListItem(String item, boolean isItemChecked) {
        this.item = item;
        this.isItemChecked = isItemChecked;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isItemChecked() {
        return isItemChecked;
    }

    public void setItemChecked(boolean itemChecked) {
        isItemChecked = itemChecked;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "item='" + item + '\'' +
                ", isItemChecked=" + isItemChecked +
                '}';
    }
}
