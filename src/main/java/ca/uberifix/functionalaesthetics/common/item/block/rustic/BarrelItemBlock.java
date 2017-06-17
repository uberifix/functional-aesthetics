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
        this.initModelVariant(0, "variant=oak");
        this.initModelVariant(1, "variant=spruce");
        this.initModelVariant(2, "variant=birch");
        this.initModelVariant(3, "variant=jungle");
        this.initModelVariant(4, "variant=acacia");
        this.initModelVariant(5, "variant=dark_oak");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockVariants.EnumWoodVariant variant = BlockVariants.EnumWoodVariant.byMetadata(stack.getMetadata());
        return super.getUnlocalizedName() + "." + variant.toString();
    }
}
