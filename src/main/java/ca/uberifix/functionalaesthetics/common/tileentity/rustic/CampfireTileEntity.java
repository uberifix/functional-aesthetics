package ca.uberifix.functionalaesthetics.common.tileentity.rustic;

import ca.uberifix.functionalaesthetics.FunctionalAesthetics;
import ca.uberifix.functionalaesthetics.common.tileentity.TileEntityCommon;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
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
    private final Table<UUID, EntityItem, Integer> itemsCooking = HashBasedTable.create();
    private final Map<UUID, EntityItem> removeCooking = new HashMap<>();
    private final Table<UUID, EntityItem, Integer> itemsBurning = HashBasedTable.create();
    private final Map<UUID, EntityItem> removeBurning = new HashMap<>();
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
            // Cook items if in range and remove from list, decrease counter
            for (Table.Cell<UUID, EntityItem, Integer> cell : itemsCooking.cellSet()) {
                UUID itemUUID = cell.getRowKey();
                EntityItem itemCooking = cell.getColumnKey();
                Integer itemTimer = cell.getValue();
                double itemX = itemCooking.posX, itemY = itemCooking.posY, itemZ = itemCooking.posZ;
                ItemStack itemCooked = FurnaceRecipes.instance().getSmeltingResult(itemCooking.getEntityItem());
                if (!itemCooking.isEntityAlive()) { currentlyCooking--; this.removeCooking.put(itemUUID, itemCooking); }
                if (itemsNearby.contains(itemCooking)) {
                    this.itemsCooking.put(itemUUID, itemCooking, itemTimer - 1);
                    if (tick % 5 == 0) {
                        ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, true, itemX, itemY + 0.5, itemZ, 1, 0, 0, 0, 0.01f, new int[0]);
                    }
                    if (itemTimer <= 0) {
                        itemCooking.setEntityItemStack(itemCooked.copy());
                        itemCooking.setNoPickupDelay();
                        ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.END_ROD, true, itemX, itemY + 0.5, itemZ, 5, 0, 0, 0, 0.01f, new int[0]);
                        currentlyCooking--;
                        this.removeCooking.put(itemUUID, itemCooking);
                    }
                } else { itemCooking.setNoPickupDelay(); currentlyCooking--; this.removeCooking.put(itemUUID, itemCooking); }
            }
            // Burn items if in range and remove from list
            for (Table.Cell<UUID, EntityItem, Integer> cell : itemsBurning.cellSet()) {
                UUID itemUUID = cell.getRowKey();
                EntityItem itemBurning = cell.getColumnKey();
                Integer itemTimer = cell.getValue();
                double itemX = itemBurning.posX, itemY = itemBurning.posY, itemZ = itemBurning.posZ;
                if (!itemBurning.isEntityAlive()) { this.removeBurning.put(itemUUID, itemBurning); }
                if (itemsNearby.contains(itemBurning)) {
                    this.itemsBurning.put(itemUUID, itemBurning, itemTimer - 1);
                    if (tick % 5 == 0) {
                        ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.SMOKE_LARGE, true, itemX, itemY + 0.5, itemZ, 1, 0, 0, 0, 0.01f, new int[0]);
                    }
                    if (itemTimer <= 0) {
                        itemBurning.setDead();
                        ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.FLAME, true, itemX, itemY + 0.5, itemZ, 10, 0, 0, 0, 0.01f, new int[0]);
                        this.removeBurning.put(itemUUID, itemBurning);
                    }
                } else { this.removeBurning.put(itemUUID, itemBurning); }
            }
            // Removes items from tables without concurrent modification exceptions
            for (Map.Entry<UUID, EntityItem> entry : removeCooking.entrySet()) {
                this.itemsCooking.remove(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<UUID, EntityItem> entry : removeBurning.entrySet()) {
                this.itemsBurning.remove(entry.getKey(), entry.getValue());
            }
            // Get items nearby and add to cook list or burn list, if cooking increase counter
            for (EntityItem itemNearby : itemsNearby) {
                double itemX = itemNearby.posX, itemY = itemNearby.posY, itemZ = itemNearby.posZ;
                UUID itemUUID = itemNearby.getPersistentID();
                ItemStack itemNearbyCooked = FurnaceRecipes.instance().getSmeltingResult(itemNearby.getEntityItem());
                boolean itemNearbyIsFood = itemNearbyCooked.getItem() instanceof ItemFood;
                if (!ItemStackTools.isEmpty(itemNearbyCooked)) {
                    if (itemNearbyIsFood && currentlyCooking < RUSTIC_CAMPFIRE_COOK_AMOUNT && !this.itemsCooking.containsRow(itemUUID)) {
                        EntityItem newItem = new EntityItem(getWorld(), itemX, itemY, itemZ, itemNearby.getEntityItem().splitStack(1));
                        newItem.setInfinitePickupDelay();
                        newItem.setVelocity(0, 0, 0);
                        getWorld().spawnEntity(newItem);
                        this.itemsCooking.put(newItem.getPersistentID(), newItem, RUSTIC_CAMPFIRE_COOK_TIME);
                        currentlyCooking++;
                    } else if (!itemNearbyIsFood && !this.itemsBurning.containsRow(itemUUID)) { this.itemsBurning.put(itemUUID, itemNearby, 60); }
                } else if (!this.itemsBurning.containsRow(itemUUID)) { this.itemsBurning.put(itemUUID, itemNearby, 60); }
            }
        } catch (final Exception e) {
            FunctionalAesthetics.logger.warn("Exception for Campfire TileEntity at: "+ this.getPos()+", in world: "+this.getWorld().getWorldInfo().getWorldName());
            FunctionalAesthetics.logger.warn(e);
        }
    }
}