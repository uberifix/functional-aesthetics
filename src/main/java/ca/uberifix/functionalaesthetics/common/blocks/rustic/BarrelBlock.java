package ca.uberifix.functionalaesthetics.common.blocks.rustic;

import ca.uberifix.functionalaesthetics.common.blocks.BlockVariants;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.ListenerList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by uberifix
 */
public class BarrelBlock extends BlockRustic {
    public static final PropertyEnum<BlockVariants.EnumWoodVariant> PROPERTYVARIANT = PropertyEnum.<BlockVariants.EnumWoodVariant>create("variant", BlockVariants.EnumWoodVariant.class);

    public BarrelBlock() {
        super("barrel_block", Material.WOOD);
        this.registerBlock();
        this.initModelVariant(0, "oak");
        this.initModelVariant(1, "spruce");
        this.initModelVariant(2, "birch");
        this.initModelVariant(3, "jungle");
        this.initModelVariant(4, "acacia");
        this.initModelVariant(5, "dark_oak");
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
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (BlockVariants.EnumWoodVariant blockvariants$enumtype : BlockVariants.EnumWoodVariant.values())
        {
            list.add(new ItemStack(itemIn, 1, blockvariants$enumtype.getMetadata()));
        }
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((BlockVariants.EnumWoodVariant)state.getValue(PROPERTYVARIANT)).getMetadata();
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PROPERTYVARIANT, BlockVariants.EnumWoodVariant.byMetadata(meta));
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockVariants.EnumWoodVariant)state.getValue(PROPERTYVARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {PROPERTYVARIANT});
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        BlockVariants.EnumWoodVariant colour = BlockVariants.EnumWoodVariant.byMetadata(meta);

        return this.getDefaultState().withProperty(PROPERTYVARIANT, colour);
    }
}
