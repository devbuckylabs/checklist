package com.buckylabs.checklist;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryDiff extends DiffUtil.Callback {

    List<Category> oldcategories;
    List<Category> newcategories;

    public CategoryDiff(List<Category> oldcategories, List<Category> newcategories) {
        this.oldcategories = oldcategories;
        this.newcategories = newcategories;
    }

    @Override
    public int getOldListSize() {
        return oldcategories.size();
    }

    @Override
    public int getNewListSize() {
        return newcategories.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldcategories.get(i).getId() == newcategories.get(i1).getId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        final Category oldCategory = oldcategories.get(i);
        final Category newCategory = newcategories.get(i1);

        return oldCategory.getCategory_name().equals(newCategory.getCategory_name());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}

