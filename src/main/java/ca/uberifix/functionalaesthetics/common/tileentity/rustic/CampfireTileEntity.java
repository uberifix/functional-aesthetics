package ca.uberifix.functionalaesthetics.common.tileentity.rustic;

import ca.uberifix.functionalaesthetics.FunctionalAesthetics;
import ca.uberifix.functionalaesthetics.common.tileentity.TileEntityCommon;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;

import java.util.*;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_CAMPFIRE_COOKING;
import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_CAMPFIRE_COOK_AMOUNT;
import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_CAMPFIRE_COOK_TIME;

/**
 * Created by uberifix
 */
public class CampfireTileEntity extends TileEntityCommon implements ITickable{
    private final Map<UUID, Integer> itemsCooking = new HashMap<>();
    private int tick;
    private int currentlyCooking;

    @Override
    public void update () {
        tick = (tick + 1) % 20;

        if (isInvalid() || !getWorld().isBlockLoaded(getPos()) || getWorld().isRemote || !RUSTIC_CAMPFIRE_COOKING) { return; }

        try {
            double blockX = this.pos.getX(), blockY = this.pos.getY(), blockZ = this.pos.getZ();
            AxisAlignedBB COOK_RADIUS_AABB = new AxisAlignedBB(blockX - 1, blockY, blockZ - 1, blockX + 2, blockY + 0.01, blockZ + 2);
            List<EntityItem> itemsNearby = getWorld().getEntitiesWithinAABB(EntityItem.class, COOK_RADIUS_AABB);
            for (EntityItem itemNearby : itemsNearby) {
                double itemX = itemNearby.posX, itemY = itemNearby.posY, itemZ = itemNearby.posZ;
                UUID itemUUID = itemNearby.getPersistentID();
                ItemStack itemNearbyCooked = FurnaceRecipes.instance().getSmeltingResult(itemNearby.getEntityItem());
                boolean itemNearbyIsFood = itemNearbyCooked.getItem() instanceof ItemFood;
                if (!ItemStackTools.isEmpty(itemNearbyCooked)) {
                    if (itemNearbyIsFood && currentlyCooking < RUSTIC_CAMPFIRE_COOK_AMOUNT && !this.itemsCooking.containsKey(itemUUID)) {
                        EntityItem newItem = new EntityItem(getWorld(), itemX, itemY, itemZ, itemNearby.getEntityItem().splitStack(1));
                        newItem.setInfinitePickupDelay();
                        newItem.setVelocity(0, 0, 0);
                        getWorld().spawnEntity(newItem);
                        this.itemsCooking.put(newItem.getPersistentID(), RUSTIC_CAMPFIRE_COOK_TIME);
                        currentlyCooking++;
                    } else if (this.itemsCooking.containsKey(itemUUID)) {
                        this.itemsCooking.put(itemUUID, this.itemsCooking.get(itemUUID) - 1);
                        if (tick % 5 == 0) {
                            ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, true, itemX, itemY + 0.5, itemZ, 1, 0, 0, 0, 0.01f, new int[0]);
                        }
                        if (this.itemsCooking.get(itemUUID) <= 0) {
                            itemNearby.setEntityItemStack(itemNearbyCooked.copy());
                            itemNearby.setNoPickupDelay();
                            ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.END_ROD, true, itemX, itemY+0.5, itemZ, 5, 0, 0, 0, 0.01f, new int[0]);
                            currentlyCooking--;
                            this.itemsCooking.remove(itemUUID);
                        }
                    }
                } //else add to burn map
            }
        } catch (final Exception e) {
            FunctionalAesthetics.logger.warn(e);
        }
    }
}