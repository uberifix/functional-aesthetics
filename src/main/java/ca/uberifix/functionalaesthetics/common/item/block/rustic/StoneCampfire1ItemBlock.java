package ca.uberifix.functionalaesthetics.common.item.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.ItemBlockCommon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by uberifix
 */
public class StoneCampfire1ItemBlock extends ItemBlockCommon {
    public StoneCampfire1ItemBlock(Block block) {
        super(block, ModBlocks.stoneCampfire1Block.getRegistryName(), true);
        this.registerItemBlock();
        String[] WoodVariants = {"oak", "spruce", "birch", "jungle"};
        String[] StoneVariants = {"stone", "diorite", "andesite", "granite"};
        for(int i=0; i < WoodVariants.length; i++) {
            for(int j=0; j < StoneVariants.length; j++) {
                this.initModelVariant(i*4+j, "stone_variant="+StoneVariants[j]+",wood_variant="+WoodVariants[i]);
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BlockVariants.EnumStoneVariant stone_variant = BlockVariants.EnumStoneVariant.byMetadata(stack.getMetadata() & 0x03);
        BlockVariants.EnumWoodVariantOld wood_variant = BlockVariants.EnumWoodVariantOld.byMetadata(stack.getMetadata() >> 2);
        return super.getUnlocalizedName() + "_" + stone_variant.toString() + "_" + wood_variant.toString();
    }
}
