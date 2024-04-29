package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.jei;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockItems;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModMenuTypes;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.AlloyBlastFurnaceMenu;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.BuildersFurnaceMenu;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.AlloyBlastFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.BuildersFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.AlloyBlastFurnaceRecipe;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.BuildersFurnaceRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class BMNWJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY, "jei_plugin");
    }



    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloyingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new BuildersSmeltingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        AlloyBlastFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        BuildersFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlloyBlastFurnaceScreen.class, 83, 19, 30, 45,
                AlloyingCategory.ALLOY_BLAST_FURNACE_RECIPE_TYPE);
        registration.addRecipeClickArea(BuildersFurnaceScreen.class, 83, 19, 30, 45,
                BuildersSmeltingCategory.BUILDERS_FURNACE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlockItems.ALLOY_BLAST_FURNACE.get()), AlloyingCategory.ALLOY_BLAST_FURNACE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlockItems.BUILDERS_FURNACE.get()), BuildersSmeltingCategory.BUILDERS_FURNACE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(BuildersFurnaceMenu.class, ModMenuTypes.BUILDERS_FURNACE_MENU.get(),
                BuildersSmeltingCategory.BUILDERS_FURNACE_RECIPE_TYPE,
                1,1, 3, 36);
        registration.addRecipeTransferHandler(AlloyBlastFurnaceMenu.class, ModMenuTypes.ALLOY_BLAST_FURNACE_MENU.get(),
                AlloyingCategory.ALLOY_BLAST_FURNACE_RECIPE_TYPE,
                1,2, 3, 36);
    }
}