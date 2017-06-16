package ca.uberifix.functionalaesthetics.common.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by uberifix
 */
public class FunctionalAestheticsRusticTab extends CreativeTabs {
    public static final FunctionalAestheticsRusticTab INSTANCE = new FunctionalAestheticsRusticTab();

    public FunctionalAestheticsRusticTab() { super("tabFunctionalAestheticsRustic");}

    @Override
    public Item getTabIconItem() {return Items.BOOK;}
}
