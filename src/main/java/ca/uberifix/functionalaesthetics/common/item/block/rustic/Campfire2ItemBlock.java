package ca.uberifix.functionalaesthetics.common.item.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.ItemBlockCommon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by uberifix
 */
public class Campfire2ItemBlock extends ItemBlockCommon {
    public Campfire2ItemBlock(Block block) {
        super(block, ModBlocks.campfire2Block.getRegistryName(), true);
        this.registerItemBlock();
    }

    public void initModels() {
        String[] WoodVariants = {"acacia", "dark_oak"};
        for(int i=0; i < WoodVariants.length; i++) {
            this.initModelVariant(i, "wood_variant="+WoodVariants[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockVariants.EnumWoodVariantNew wood_variant = BlockVariants.EnumWoodVariantNew.byMetadata(stack.getMetadata());
        return super.getUnlocalizedName() + "_" + wood_variant.toString();
    }
}