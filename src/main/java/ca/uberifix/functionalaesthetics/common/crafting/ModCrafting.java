package ca.uberifix.functionalaesthetics.common.crafting;

import ca.uberifix.functionalaesthetics.common.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by uberifix
 */
public class ModCrafting {
    public static IRecipe bowDrillRecipe = new ShapelessOreRecipe(new ItemStack(ModItems.bowDrillItem),
            new ItemStack(Items.STICK),
            new ItemStack(Items.BOW));

    public static void init() {
        GameRegistry.addRecipe(bowDrillRecipe);
    }
}
