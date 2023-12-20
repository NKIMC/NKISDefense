package com.newkinidev.nkisdefense.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import static com.newkinidev.nkisdefense.NKISDefense.GUN_WORKBENCH_GUI;

public class GunWorkbenchGui extends AbstractContainerMenu {
    public GunWorkbenchGui(int containerId, Inventory playerInv) {
        super(GUN_WORKBENCH_GUI.get(), containerId);
        // ...
    }

    public GunWorkbenchGui(@Nullable MenuType<?> p_38851_, int p_38852_) {
        super(p_38851_, p_38852_);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
