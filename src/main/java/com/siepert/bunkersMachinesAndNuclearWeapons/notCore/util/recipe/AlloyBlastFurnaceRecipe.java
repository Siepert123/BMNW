package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.jei.AlloyingCategory;
import mezz.jei.api.registration.IRecipeRegistration;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class AlloyBlastFurnaceRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    public AlloyBlastFurnaceRecipe(ResourceLocation id, ItemStack output,
                              NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }
    @Override
    @ParametersAreNonnullByDefault
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;

        if (recipeItems.get(0).test(pContainer.getItem(1))) {
            return recipeItems.get(1).test(pContainer.getItem(2));
        }
        if (recipeItems.get(1).test(pContainer.getItem(1))) {
            return recipeItems.get(0).test(pContainer.getItem(2));
        }

        return false;
    }
    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer p_44001_, @NotNull RegistryAccess p_267165_) {
        return output;
    }
    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }
    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess p_267052_) {
        return output.copy();
    }
    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public ItemStack getResultItem() {
        return output.copy();
    }
    public Ingredient getFuelItem() {
        return Ingredient.of(Items.LAVA_BUCKET, Blocks.COAL_BLOCK.asItem(), Items.COAL, Items.CHARCOAL, Blocks.OAK_LOG.asItem(), Blocks.OAK_WOOD.asItem());
    }
    public static class Type implements RecipeType<AlloyBlastFurnaceRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "alloying";
    }
    public static void addAllRecipes(RecipeManager recipeManager, IRecipeRegistration registration) {
        List<AlloyBlastFurnaceRecipe> alloyBlastFurnaceRecipes = recipeManager.getAllRecipesFor(AlloyBlastFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(AlloyingCategory.ALLOY_BLAST_FURNACE_RECIPE_TYPE, alloyBlastFurnaceRecipes);
    }
    public static class Serializer implements RecipeSerializer<AlloyBlastFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY, "alloying");
        @Override
        public @NotNull AlloyBlastFurnaceRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlloyBlastFurnaceRecipe(pRecipeId, output, inputs);
        }
        @Override
        public @Nullable AlloyBlastFurnaceRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            return new AlloyBlastFurnaceRecipe(id, output, inputs);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buf, AlloyBlastFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}