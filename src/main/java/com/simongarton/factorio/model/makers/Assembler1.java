package com.simongarton.factorio.model.makers;

import com.simongarton.factorio.model.CategoryType;
import com.simongarton.factorio.model.ItemType;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.simongarton.factorio.model.CategoryType.ADVANCED_CRAFTING;
import static com.simongarton.factorio.model.CategoryType.CRAFTING;

@NoArgsConstructor
public class Assembler1 extends Maker {

    protected ItemType itemType = ItemType.ASSEMBLING_MACHINE_1;

    private final double craftingSpeed = 0.5;

    @Override
    public ItemType getItemType() {
        return this.itemType;
    }

    @Override
    public MakerType getMakerType() {
        return MakerType.ASSEMBLER;
    }

    @Override
    public double getCraftingSpeed() {
        return this.craftingSpeed;
    }

    @Override
    public boolean makes(CategoryType categoryType) {
        return Set.of(CRAFTING, ADVANCED_CRAFTING).contains(categoryType);
    }
}
