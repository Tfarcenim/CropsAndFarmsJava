package tfar.cropsandfarmsjava.world.block.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface EnergyBlockEntity {
    long getCapacity();
    long getEnergy();
    void setEnergy(long energy);

    default long takeEnergy(long requested,boolean simulate) {
        long max = getEnergy();
        if (requested > max) {
            if (!simulate) {
                setEnergy(0);
                markDirty();
            }
            return max;
        } else {
            if (!simulate) {
                setEnergy(max - requested);
                markDirty();
            }
            return requested;
        }
    }

    default long giveEnergy(long received,boolean simulate) {
        long max = getCapacity() - getEnergy();
        if (received > max) {
            if(!simulate){
                setEnergy(getCapacity());
                markDirty();
            }
            return max;
        } else {
            if(!simulate){
                setEnergy(getEnergy()+received);
                markDirty();
            }
            return received;
        }
    }

    default void markDirty() {
        ((BlockEntity)this).setChanged();
    }

    default CompoundTag serializeEnergy() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("energy",getEnergy());
        return tag;
    }

    default void deserializeEnergy(CompoundTag tag) {
        setEnergy(tag.getLong("energy"));
    }
    static int cap(long value) {
        return Math.toIntExact(Math.min(value, Integer.MAX_VALUE));
    }
}
