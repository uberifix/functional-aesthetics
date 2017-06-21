package ca.uberifix.functionalaesthetics.common.config;

import ca.uberifix.functionalaesthetics.FunctionalAesthetics;
import ca.uberifix.functionalaesthetics.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by uberifix
 */
public class Config {
    //Config Categories
    private static final String CATEGORY_MODULES            = "All Modules";
    private static final String CATEGORY_RUSTIC             = "Rustic Module";
    private static final String CATEGORY_MODERN             = "Modern Module";
    //Rustic Module Configs
    public static boolean   RUSTIC_MODULE_ENABLED           = true;
    public static boolean   RUSTIC_CAMPFIRE_COOKING         = true;
    public static int       RUSTIC_CAMPFIRE_COOK_AMOUNT     = 4;
    public static int       RUSTIC_CAMPFIRE_COOK_TIME       = 100;
    //Modern Module Configs
    public static boolean   MODERN_MODULE_ENABLED           = true;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initModuleConfig(cfg);
            initRusticConfig(cfg);
            initModernConfig(cfg);
        } catch (Exception e1) {
            FunctionalAesthetics.logger.error("Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initModuleConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_MODULES, "Enable or Disable Various Modules, see https://mods.uberifix.ca/FunctionalAesthetics for what each module includes");
        RUSTIC_MODULE_ENABLED           = cfg.getBoolean("Enable Rustic Module", CATEGORY_MODULES, RUSTIC_MODULE_ENABLED, "Enable/Disable the Rustic Module");
        MODERN_MODULE_ENABLED           = cfg.getBoolean("Enable Modern Module", CATEGORY_MODULES, MODERN_MODULE_ENABLED, "Enable/Disable the Modern Module");
    }

    private static void initRusticConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_RUSTIC, "Customize the components included in the rustic module");
        RUSTIC_CAMPFIRE_COOKING         = cfg.getBoolean("Enable Campfire Cooking", CATEGORY_RUSTIC, RUSTIC_CAMPFIRE_COOKING, "Enable cooking at a campfire by throwing food near it");
        RUSTIC_CAMPFIRE_COOK_AMOUNT     = cfg.getInt("Campfire Cooking Amount", CATEGORY_RUSTIC, RUSTIC_CAMPFIRE_COOK_AMOUNT, 1, 16, "Amount of items campfires can cook simultaneously");
        RUSTIC_CAMPFIRE_COOK_TIME       = cfg.getInt("Campfire Cooking Time", CATEGORY_RUSTIC, RUSTIC_CAMPFIRE_COOK_TIME, 20, 1200,"Time it takes for food to cook at a campfire, in ticks");
    }

    private static void initModernConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_MODERN, "Customize the components included in the modern module");

    }
}