package ca.uberifix.functionalaesthetics.common.item.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.ItemBlockCommon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by uberifix
 */
public class Campfire1ItemBlock extends ItemBlockCommon {
    public Campfire1ItemBlock(Block block) {
        super(block, ModBlocks.campfire1Block.getRegistryName(), true);
        this.registerItemBlock();
        String[] WoodVariants = {"oak", "spruce", "birch", "jungle"};
        for(int i=0; i < WoodVariants.length; i++) {
            this.initModelVariant(i, "wood_variant="+WoodVariants[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockVariants.EnumWoodVariantOld wood_variant = BlockVariants.EnumWoodVariantOld.byMetadata(stack.getMetadata());
        return super.getUnlocalizedName() + "_" + wood_variant.toString();
    }
}
