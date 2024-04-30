package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.DeepslateBuildersFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DeepslateBuildersFurnaceScreen extends AbstractContainerScreen<DeepslateBuildersFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY,
                    "textures/gui/builders_furnace.png");
    public DeepslateBuildersFurnaceScreen(DeepslateBuildersFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    @Override
    public void render(GuiGraphics pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 79, y + 31, 176, 14, menu.getScaledProgress(), 20);
        }
        guiGraphics.blit(TEXTURE, x + 62, y + 37, 176, 0, 13, menu.getScaledFuel());
    }
}
