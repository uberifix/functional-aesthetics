package ca.uberifix.functionalaesthetics.common.item;

import ca.uberifix.functionalaesthetics.common.item.rustic.BowDrillItem;
import net.minecraftforge.client.model.ModelLoader;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModItems {
    public static BowDrillItem bowDrillItem;

    public static void init() {
        if (RUSTIC_MODULE_ENABLED) {
            bowDrillItem = new BowDrillItem();
        }
    }

    public static void initClient() {
        if (RUSTIC_MODULE_ENABLED) {
            bowDrillItem.initModel();
        }
    }
}
