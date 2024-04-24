package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.LittleBoyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class LittleBoyRenderer extends MobRenderer<LittleBoyEntity, LittleBoyModel<LittleBoyEntity>> {
    public LittleBoyRenderer(EntityRendererProvider.Context pProviderContext) {
        super(pProviderContext, new LittleBoyModel<>(pProviderContext.bakeLayer(AllModelLayers.LITTLE_BOY_LAYER)), 1.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(LittleBoyEntity pEntity) {
        return new ResourceLocation(BMNW.MOD_ID, "textures/blockEntity/little_boy.png");
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(LittleBoyEntity pEntity, float pYaw, float pTickPartial, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pLightPack) {

        super.render(pEntity, pYaw, pTickPartial, pMatrixStack, pBuffer, pLightPack);
    }
}
