package ca.uberifix.functionalaesthetics.common.block;

import ca.uberifix.functionalaesthetics.common.lib.LibRef;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by uberifix
 */
public class BlockCommon extends Block {
    public BlockCommon(String name, Material material) {
        super(material);

        setRegistryName(name);
        setUnlocalizedName(LibRef.MOD_ID + "." + name);
    }

    public void registerBlock() {
        GameRegistry.register(this);
    }
}