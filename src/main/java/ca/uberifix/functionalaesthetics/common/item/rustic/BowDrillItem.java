package ca.uberifix.functionalaesthetics.common.item.rustic;

import ca.uberifix.functionalaesthetics.FunctionalAesthetics;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by uberifix
 */
public class BowDrillItem extends ItemRustic {
    public BowDrillItem() {
        super("bow_drill");
        this.setMaxStackSize(1);
        this.setMaxDamage(8);
        this.registerItem();
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        ItemStack item = player.getHeldItem(hand);
        int meta = block.getMetaFromState(worldIn.getBlockState(pos)) & 0x03;
        if (block == Blocks.LOG && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
            worldIn.setBlockState(pos, ModBlocks.campfire1Block.getStateFromMeta(meta));
            worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            item.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        } else if (block == Blocks.LOG2) {
            worldIn.setBlockState(pos, ModBlocks.campfire2Block.getStateFromMeta(meta));
            worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            item.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
