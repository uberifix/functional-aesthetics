package ca.uberifix.functionalaesthetics.common.item;

import ca.uberifix.functionalaesthetics.common.lib.LibRef;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by uberifix
 */
public class ItemCommon extends Item {
    public ItemCommon(String name, CreativeTabs tab) {
        super();

        setRegistryName(name);
        setUnlocalizedName(LibRef.MOD_ID + "." + name);
        setCreativeTab(tab);
    }

    public void registerItem() { GameRegistry.register(this); }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0,new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
