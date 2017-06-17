package ca.uberifix.functionalaesthetics.common.item.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.ItemBlockCommon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by uberifix
 */
public class BarrelItemBlock extends ItemBlockCommon{
    public BarrelItemBlock(Block block) {
        super(block, ModBlocks.barrelBlock.getRegistryName(), true);
        this.registerItemBlock();
        String[] WoodVariants = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};
        for(int i=0; i < WoodVariants.length; i++) {
            this.initModelVariant(i, "variant="+WoodVariants[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockVariants.EnumWoodVariantAll variant = BlockVariants.EnumWoodVariantAll.byMetadata(stack.getMetadata());
        return super.getUnlocalizedName() + "." + variant.toString();
    }
}
