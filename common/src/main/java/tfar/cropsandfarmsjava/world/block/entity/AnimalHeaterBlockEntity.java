package tfar.cropsandfarmsjava.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import tfar.cropsandfarmsjava.world.CropsAndFarmsBlockEntityTypes;

import java.util.List;

public class AnimalHeaterBlockEntity extends BlockEntity implements EnergyBlockEntity{
    public AnimalHeaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(CropsAndFarmsBlockEntityTypes.ANIMAL_HEATER, pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("energy_storage",serializeEnergy());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        deserializeEnergy(tag.getCompound("energy_storage"));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AnimalHeaterBlockEntity animalHeaterBlockEntity) {
        if (animalHeaterBlockEntity.energy > 0) {
            animalHeaterBlockEntity.energy-=20;
            animalHeaterBlockEntity.ageAnimals();
            animalHeaterBlockEntity.setChanged();
        }
    }

    public void ageAnimals() {
        int r = 4;
        List<? extends AgeableMob> entityList = level.getEntitiesOfClass(AgeableMob.class,new AABB(getBlockPos()).inflate(r), ageableMob -> {
            return true;
        });
        for (AgeableMob ageableMob : entityList) {
            ageableMob.ageUp(20);
            ageableMob.addEffect(new MobEffectInstance(MobEffects.GLOWING,20));
        }
    }

    long energy;

    @Override
    public long getCapacity() {
        return 1_000_000;
    }

    @Override
    public long getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(long energy) {
        this.energy = energy;
    }

    public static final long COAL_ENERGY = 16_000;

    public boolean tryAddCoal() {
        if (giveEnergy(COAL_ENERGY,true) == COAL_ENERGY) {
            giveEnergy(COAL_ENERGY,false);
            return true;
        }
        return false;
    }
}
