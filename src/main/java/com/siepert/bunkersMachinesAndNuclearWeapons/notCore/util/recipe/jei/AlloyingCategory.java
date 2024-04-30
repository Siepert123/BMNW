package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.jei;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock.DisplayBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.AlloyBlastFurnaceRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AlloyingCategory implements IRecipeCategory<AlloyBlastFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY, "alloying");
    public static final ResourceLocation TEXTURES = new ResourceLocation(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY,
            "textures/gui/jei_helpers/alloy_blast_furnace.png");
    public static final RecipeType<AlloyBlastFurnaceRecipe> ALLOY_BLAST_FURNACE_RECIPE_TYPE =
            new RecipeType<>(UID, AlloyBlastFurnaceRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public AlloyingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 63, 13, 70, 56);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DisplayBlocks.ALLOY_BLAST_FURNACE.get()));
    }

    @Override
    public RecipeType<AlloyBlastFurnaceRecipe> getRecipeType() {
        return ALLOY_BLAST_FURNACE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.bmnw.alloying");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloyBlastFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 3).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 37).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 51, 20).addItemStack(recipe.getResultItem());
    }
}
