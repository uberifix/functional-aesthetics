package ca.uberifix.functionalaesthetics.common.tileentity;

import ca.uberifix.functionalaesthetics.common.lib.LibRef;
import ca.uberifix.functionalaesthetics.common.tileentity.rustic.CampfireTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModTileEntities {
    public static void init() {
        if (RUSTIC_MODULE_ENABLED) {
            GameRegistry.registerTileEntity(CampfireTileEntity.class, LibRef.MOD_ID + "_campfire_te");
        }
    }
}
