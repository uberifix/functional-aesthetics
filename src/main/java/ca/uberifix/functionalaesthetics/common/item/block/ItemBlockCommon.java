package ca.uberifix.functionalaesthetics.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by uberifix
 */
public class ItemBlockCommon extends ItemBlock {
    public ItemBlockCommon(Block block, ResourceLocation name, Boolean hasSubtypes) {
        super(block);
        this.setRegistryName(name);
        this.setHasSubtypes(hasSubtypes);
    }

    public void registerItemBlock() {
        GameRegistry.register(this);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0,new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    @SideOnly(Side.CLIENT)
    public void initModelVariant(int meta , String variant) {
        ModelLoader.setCustomModelResourceLocation(this, meta,new ModelResourceLocation(getRegistryName(), variant));
    }
}
