package tfar.cropsandfarmsjava;


import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import tfar.cropsandfarmsjava.datagen.CropsAndFarmsDatagen;
import tfar.cropsandfarmsjava.world.*;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;
import tfar.cropsandfarmsjava.world.block.entity.EnergyBlockEntity;

@Mod(CropsAndFarms.MOD_ID)
public class CropsAndFarmsNeoforge {

    public CropsAndFarmsNeoforge(IEventBus eventBus) {
        eventBus.addListener(this::register);
        eventBus.addListener(this::createAttributes);
        eventBus.addListener(CropsAndFarmsDatagen::gather);
        eventBus.addListener(this::caps);
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        CropsAndFarms.init();
    }

    void createAttributes(EntityAttributeCreationEvent event) {
        CropsAndFarms.createAttributes(event::put);
    }

    void register(RegisterEvent event) {
        if (event.getRegistry() == BuiltInRegistries.BLOCK) {
            CropsAndFarms.initRegistries();
        }
    }

    void caps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,CropsAndFarmsBlockEntityTypes.ANIMAL_HEATER,(object, context) -> new EnergyWrapper(object));
    }

    public static class EnergyWrapper implements IEnergyStorage {

        private final EnergyBlockEntity blockEntity;

        public EnergyWrapper(EnergyBlockEntity blockEntity) {

            this.blockEntity = blockEntity;
        }

        @Override//return energy accepted
        public int receiveEnergy(int toReceive, boolean simulate) {
            return (int) blockEntity.giveEnergy(toReceive,simulate);
        }

        @Override
        public int extractEnergy(int toExtract, boolean simulate) {
            return (int) blockEntity.takeEnergy(toExtract,simulate);
        }

        @Override
        public int getEnergyStored() {
            return EnergyBlockEntity.cap(blockEntity.getEnergy());
        }

        @Override
        public int getMaxEnergyStored() {
            return EnergyBlockEntity.cap(blockEntity.getCapacity());
        }


        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    }
}