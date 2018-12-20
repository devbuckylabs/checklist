package com.buckylabs.checklist;


import java.util.List;

public class Category {
    private String category_name;
    private List<ListItem> listItems;


    public Category(String category_name, List<ListItem> listItems) {
        this.category_name = category_name;
        this.listItems = listItems;
    }


    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "CategoryListItem{" +
                "category_name='" + category_name + '\'' +
                ", listItems=" + listItems +
                '}';
    }

}
