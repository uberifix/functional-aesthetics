package ca.uberifix.functionalaesthetics.common.blocks.rustic;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by uberifix
 */
public class CampfireBlock extends BlockRustic {
    private static final AxisAlignedBB CAMPFIRE_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.4D, 0.8D);

    public CampfireBlock() {
        super("campfire_block", Material.WOOD);
        this.init();
        this.setLightLevel(1.0F);
        translucent = true;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CAMPFIRE_AABB;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return 15;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY() + 0.5D;
        double d2 = (double)pos.getZ();
        for (int i = 0; i < 4; i++) {
            double rand1 = Math.random() * 0.3;
            double rand2 = Math.random() * 0.3;
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.5, d1, d2 + 0.5, 0.0D, 0.01D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + rand1 + 0.35, d1, d2 + rand2 + 0.35, 0.0D, 0.01D, 0.0D, new int[0]);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.setFire(10);
    }
}
