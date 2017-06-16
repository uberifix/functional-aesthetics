package ca.uberifix.functionalaesthetics.common.blocks.rustic;

import ca.uberifix.functionalaesthetics.common.blocks.BlockCommon;
import ca.uberifix.functionalaesthetics.common.tab.FunctionalAestheticsRusticTab;
import net.minecraft.block.material.Material;


/**
 * Created by uberifix
 */
public class BlockRustic extends BlockCommon {
    public BlockRustic(String name, Material material) {
        super(name, material);
        setCreativeTab(FunctionalAestheticsRusticTab.INSTANCE);
    }

    public void init() {
        this.registerBlock();
        this.initModel();
    }
}
