package ca.uberifix.functionalaesthetics.common.item.block;

import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.rustic.BarrelItemBlock;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModItemBlocks {
    public static BarrelItemBlock barrelItemBlock;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            barrelItemBlock = new BarrelItemBlock(ModBlocks.barrelBlock);
        }
    }
}
