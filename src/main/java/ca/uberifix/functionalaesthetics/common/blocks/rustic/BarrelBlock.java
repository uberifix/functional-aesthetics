package ca.uberifix.functionalaesthetics.common.blocks.rustic;

import ca.uberifix.functionalaesthetics.common.blocks.BlockVariants;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by uberifix
 */
public class BarrelBlock extends BlockRustic {
    public static final PropertyEnum<BlockVariants.EnumWoodVariant> VARIANT = PropertyEnum.<BlockVariants.EnumWoodVariant>create("variant", BlockVariants.EnumWoodVariant.class);

    public BarrelBlock() {
        super("barrel_block", Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockVariants.EnumWoodVariant.OAK));
        this.registerBlock();
        this.initModelVariant(0, "variant=oak");
        this.initModelVariant(1, "variant=spruce");
        this.initModelVariant(2, "variant=birch");
        this.initModelVariant(3, "variant=jungle");
        this.initModelVariant(4, "variant=acacia");
        this.initModelVariant(5, "variant=dark_oak");
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public int damageDropped(IBlockState state) {
        return ((BlockVariants.EnumWoodVariant)state.getValue(VARIANT)).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockVariants.EnumWoodVariant blockplanks$enumtype : BlockVariants.EnumWoodVariant.values())
        {
            list.add(new ItemStack(itemIn, 1, blockplanks$enumtype.getMetadata()));
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockVariants.EnumWoodVariant.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return ((BlockVariants.EnumWoodVariant)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
}
