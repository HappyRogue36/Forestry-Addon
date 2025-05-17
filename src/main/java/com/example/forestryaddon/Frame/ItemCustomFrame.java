package com.example.forestryaddon.Frame;

// Minecraft
import forestry.api.genetics.IGenome;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

// Forestry API
import forestry.api.apiculture.genetics.IBee;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.hives.IHiveFrame;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.api.genetics.IMutation;

import javax.annotation.Nullable;

public class ItemCustomFrame extends Item implements IHiveFrame {
    private final CustomBeeModifier modifier;

    // Конструктор для рамки, принимает свойства и модификатор пчёл
    public ItemCustomFrame(Properties properties, CustomBeeModifier modifier) {
        super(properties.durability(240)); // Устанавливаем базовую прочность рамки, её можно будет изменить, если нужно
        this.modifier = modifier;
    }

    @Override
    public ItemStack frameUsed(IBeeHousing house, ItemStack frame, IBee queen, int wear) {
        // Уменьшаем прочность рамки в зависимости от использования
        frame.setDamageValue(frame.getDamageValue() + wear);

        // Проверяем, не достигла ли рамка максимального износа
        if (frame.getDamageValue() >= frame.getMaxDamage()) {
            return ItemStack.EMPTY; // Рамка ломается, если износ достиг максимума
        }

        return frame; // Возвращаем обновлённую рамку
    }

    @Override
    public IBeeModifier getBeeModifier(ItemStack frame) {
        return modifier; // Возвращаем модификатор пчёл, переданный в конструктор
    }
}