package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.CategoryType;
import com.simongarton.factorio.model.ItemType;

// I don't have mining here yet

public abstract class Maker {

    public abstract ItemType getItemType();

    public abstract MakerType getMakerType();

    public abstract double getCraftingSpeed();

    public abstract boolean makes(CategoryType categoryType);
}
