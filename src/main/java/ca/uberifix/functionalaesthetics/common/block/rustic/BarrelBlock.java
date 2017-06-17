package ca.uberifix.functionalaesthetics.common.block.rustic;

import ca.uberifix.functionalaesthetics.common.block.BlockVariants;
import net.minecraft.block.material.Material;
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
    public static final PropertyEnum<BlockVariants.EnumWoodVariant> VARIANT = PropertyEnum.create("variant", BlockVariants.EnumWoodVariant.class);

    public BarrelBlock() {
        super("barrel_block", Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockVariants.EnumWoodVariant.OAK));
        this.registerBlock();
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
        return state.getValue(VARIANT).getMetadata();
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
        return state.getValue(VARIANT).getMetadata();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }
}
