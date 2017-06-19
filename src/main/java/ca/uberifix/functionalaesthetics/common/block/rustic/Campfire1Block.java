package ca.uberifix.functionalaesthetics.common.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.tileentity.rustic.CampfireTileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by uberifix
 */
public class Campfire1Block extends BlockRustic implements ITileEntityProvider{
    public static final PropertyEnum<BlockVariants.EnumWoodVariantOld> WOOD_VARIANT = PropertyEnum.create("wood_variant", BlockVariants.EnumWoodVariantOld.class);
    private static final AxisAlignedBB CAMPFIRE_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.4D, 0.8D);

    public Campfire1Block() {
        super("campfire_1", Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(WOOD_VARIANT, BlockVariants.EnumWoodVariantOld.OAK));
        this.setLightLevel(1.0F);
        translucent = true;
        this.registerBlock();
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

    public void convertToStoneCampfire(World worldIn, EntityPlayer playerIn, BlockPos pos, int meta) {
        playerIn.getHeldItem(EnumHand.MAIN_HAND).stackSize -= 1;
        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, ModBlocks.stoneCampfire1Block.getStateFromMeta(meta));
        }
    }
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        Item cobblestone = Item.getItemFromBlock(Blocks.COBBLESTONE);
        Item stone = Item.getItemFromBlock(Blocks.STONE);
        if (heldItem == null) { return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ); }
        if (heldItem.getItem() == cobblestone) {
            int meta = getMetaFromState(worldIn.getBlockState(pos));
            convertToStoneCampfire(worldIn, playerIn, pos, meta * 4);
            return true;
        } else if (heldItem.getItem() == stone && heldItem.getMetadata() == 3) {
            int meta = getMetaFromState(worldIn.getBlockState(pos));
            convertToStoneCampfire(worldIn, playerIn, pos, meta * 4 + 1);
            return true;
        } else if (heldItem.getItem() == stone && heldItem.getMetadata() == 5) {
            int meta = getMetaFromState(worldIn.getBlockState(pos));
            convertToStoneCampfire(worldIn, playerIn, pos, meta * 4 + 2);
            return true;
        } else if (heldItem.getItem() == stone && heldItem.getMetadata() == 1) {
            int meta = getMetaFromState(worldIn.getBlockState(pos));
            convertToStoneCampfire(worldIn, playerIn, pos, meta * 4 + 3);
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i <= 3; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    public int damageDropped(IBlockState state) { return getMetaFromState(state); }

    public IBlockState getStateFromMeta(int meta) {
        int wood_bits = (meta);
        BlockVariants.EnumWoodVariantOld wood = BlockVariants.EnumWoodVariantOld.byMetadata(wood_bits);
        return this.getDefaultState().withProperty(WOOD_VARIANT, wood);
    }

    public int getMetaFromState(IBlockState state) {
        BlockVariants.EnumWoodVariantOld wood = state.getValue(WOOD_VARIANT);
        int wood_bits = wood.getMetadata();
        return wood_bits;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, WOOD_VARIANT);
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) { return new CampfireTileEntity(); }
}
