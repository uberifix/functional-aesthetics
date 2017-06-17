package ca.uberifix.functionalaesthetics.common.block;

import ca.uberifix.functionalaesthetics.common.block.rustic.BarrelBlock;
import ca.uberifix.functionalaesthetics.common.block.rustic.StoneCampfire1Block;
import ca.uberifix.functionalaesthetics.common.block.rustic.StoneCampfire2Block;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModBlocks {
    public static BarrelBlock barrelBlock;
    public static StoneCampfire1Block stoneCampfire1Block;
    public static StoneCampfire2Block stoneCampfire2Block;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            barrelBlock = new BarrelBlock();
            stoneCampfire1Block = new StoneCampfire1Block();
            stoneCampfire2Block = new StoneCampfire2Block();
        }
    }
}
