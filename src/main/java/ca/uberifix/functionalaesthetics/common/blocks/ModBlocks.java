package ca.uberifix.functionalaesthetics.common.blocks;

import ca.uberifix.functionalaesthetics.common.blocks.rustic.BarrelBlock;
import ca.uberifix.functionalaesthetics.common.blocks.rustic.CampfireBlock;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModBlocks {
    public static BarrelBlock barrelBlock;
    public static CampfireBlock campfireBlock;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            barrelBlock = new BarrelBlock();
            campfireBlock = new CampfireBlock();
        }
    }
}
