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
    public static final ResourceLocation UID = new ResourceLocation(BMNW.MOD_ID, "alloying");
    public static final ResourceLocation TEXTURES = new ResourceLocation(BMNW.MOD_ID,
            "textures/gui/alloy_blast_furnace.png");
    public static final RecipeType<AlloyBlastFurnaceRecipe> ALLOY_BLAST_FURNACE_RECIPE_TYPE =
            new RecipeType<>(UID, AlloyBlastFurnaceRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public AlloyingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 0, 0, 176, 81);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 50).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.INPUT, 18, 50).addIngredients(recipe.getFuelItem());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 33).addItemStack(recipe.getResultItem());
    }
}
