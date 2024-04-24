package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.jei;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.AlloyBlastFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.AlloyBlastFurnaceRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class BMNWJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(BMNW.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloyingCategory(registration.getJeiHelpers().getGuiHelper()));
        //TODO will be available in the second mod version
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        AlloyBlastFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        //TODO will be available in the second mod version
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlloyBlastFurnaceScreen.class, 83, 19, 30, 45,
                AlloyingCategory.ALLOY_BLAST_FURNACE_RECIPE_TYPE);
    }
}