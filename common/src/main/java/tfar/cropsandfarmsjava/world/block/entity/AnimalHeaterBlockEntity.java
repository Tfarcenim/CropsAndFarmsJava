package tfar.cropsandfarmsjava.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import tfar.cropsandfarmsjava.world.AnimalHeaterMenu;
import tfar.cropsandfarmsjava.world.CropsAndFarmsBlockEntityTypes;

import java.util.List;

public class AnimalHeaterBlockEntity extends BlockEntity implements EnergyBlockEntity, MenuProvider, Nameable {

    public static final MutableComponent DEFAULT_NAME = Component.translatable("container.cropsandfarms.animal_heater");
    public static final MutableComponent UPGRADES = Component.translatable("container.cropsandfarms.upgrades");

    public AnimalHeaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(CropsAndFarmsBlockEntityTypes.ANIMAL_HEATER, pos, blockState);
    }

    protected SimpleContainer container = new SimpleContainer(4) {

        @Override
        public void setChanged() {
            AnimalHeaterBlockEntity.this.setChanged();
        }

        @Override
        public void fromTag(ListTag tag, HolderLookup.Provider levelRegistry) {
            int i;
            for(i = 0; i < this.getContainerSize(); ++i) {
                this.setItem(i, ItemStack.EMPTY);
            }

            for(i = 0; i < tag.size(); ++i) {
                CompoundTag compoundTag = tag.getCompound(i);
                int j = compoundTag.getByte("Slot") & 255;
                if (j < this.getContainerSize()) {
                    this.setItem(j, ItemStack.parse(levelRegistry, compoundTag).orElse(ItemStack.EMPTY));
                }
            }

        }

        @Override
        public ListTag createTag(HolderLookup.Provider levelRegistry) {
            ListTag listTag = new ListTag();

            for(int i = 0; i < this.getContainerSize(); ++i) {
                ItemStack itemStack = this.getItem(i);
                if (!itemStack.isEmpty()) {
                    CompoundTag compoundTag = new CompoundTag();
                    compoundTag.putByte("Slot", (byte)i);
                    listTag.add(itemStack.save(levelRegistry, compoundTag));
                }
            }

            return listTag;
        }
    };

    @Nullable
    private Component name;


    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0,1,2,3-> {
                    int shift = (3 - index) * 16;
                    int i = (int) ((energy >> shift) & 0xffff);
                    yield i;
                }
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("energy_storage",serializeEnergy());
        tag.put("inventory",container.createTag(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        deserializeEnergy(tag.getCompound("energy_storage"));
        container.fromTag(tag.getList("inventory", Tag.TAG_COMPOUND),registries);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AnimalHeaterBlockEntity animalHeaterBlockEntity) {
        if (animalHeaterBlockEntity.energy > 0) {
            if (animalHeaterBlockEntity.ageAnimals()) {
                animalHeaterBlockEntity.energy -= 20;
                animalHeaterBlockEntity.ageAnimals();
                animalHeaterBlockEntity.setChanged();
            }
        }
        if (!animalHeaterBlockEntity.container.getItem(0).isEmpty()) {
            if (animalHeaterBlockEntity.energy + COAL_ENERGY <= animalHeaterBlockEntity.getCapacity()) {
                animalHeaterBlockEntity.container.removeItem(0, 1);
                animalHeaterBlockEntity.energy+=COAL_ENERGY;
            }
        }
    }

    public boolean ageAnimals() {
        int r = 4;
        List<? extends AgeableMob> entityList = level.getEntitiesOfClass(AgeableMob.class,new AABB(getBlockPos()).inflate(r), ageableMob -> true);
        for (AgeableMob ageableMob : entityList) {
            ageableMob.ageUp(2);
            ageableMob.addEffect(new MobEffectInstance(MobEffects.GLOWING,2));
        }
        return !entityList.isEmpty();
    }

    long energy;

    public static final long CAPACITY = 1_000_000;

    @Override
    public long getCapacity() {
        return CAPACITY;
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


    public void setCustomName(@Nullable Component name) {
        this.name = name;
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AnimalHeaterMenu(containerId, playerInventory, container, this.dataAccess,this.getBlockPos());
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Override
    public Component getName() {
        return this.name != null ? this.name : DEFAULT_NAME;
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.name = componentInput.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CUSTOM_NAME, this.name);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("CustomName");
    }
}
