package ca.uberifix.functionalaesthetics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by uberifix
 */
public class BlockCommon extends Block {
    public BlockCommon(String name, Material material) {
        super(material);

        setRegistryName(name);
        setUnlocalizedName(name);
    }

    public void registerBlock() {
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setHasSubtypes(true), this.getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    @SideOnly(Side.CLIENT)
    public void initModelVariant(int meta , String variant) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), meta,new ModelResourceLocation(getRegistryName(), variant));
    }
}