package ca.uberifix.functionalaesthetics.common.block;

import ca.uberifix.functionalaesthetics.common.block.rustic.*;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModBlocks {
    public static BarrelBlock barrelBlock;
    public static Campfire1Block campfire1Block;
    public static Campfire2Block campfire2Block;
    public static StoneCampfire1Block stoneCampfire1Block;
    public static StoneCampfire2Block stoneCampfire2Block;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            barrelBlock = new BarrelBlock();
            campfire1Block = new Campfire1Block();
            campfire2Block = new Campfire2Block();
            stoneCampfire1Block = new StoneCampfire1Block();
            stoneCampfire2Block = new StoneCampfire2Block();
        }
    }
}
