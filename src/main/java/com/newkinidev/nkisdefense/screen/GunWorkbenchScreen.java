package com.newkinidev.nkisdefense.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.newkinidev.nkisdefense.NKISDefense;
import com.newkinidev.nkisdefense.gui.GunWorkbenchGui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.CraftingTableBlock;

import static com.newkinidev.nkisdefense.NKISDefense.MODID;

public class GunWorkbenchScreen extends AbstractContainerScreen<GunWorkbenchGui> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MODID, "textures/gui/gun_workbench_gui.png");

    public GunWorkbenchScreen(AbstractContainerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super((GunWorkbenchGui) p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    public static void register() {
        MenuScreens.register(NKISDefense.GUN_WORKBENCH_GUI.get(), GunWorkbenchScreen::new);
    }
}
