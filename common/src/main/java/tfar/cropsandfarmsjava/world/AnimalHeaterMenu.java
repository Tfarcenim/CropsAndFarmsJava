package tfar.cropsandfarmsjava.world;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class AnimalHeaterMenu extends AbstractContainerMenu {

    private final ContainerData data;
    private final ContainerLevelAccess access;

    public AnimalHeaterMenu(int containerId, Inventory inventory) {
        this(containerId,inventory,new SimpleContainer(6),new SimpleContainerData(4),ContainerLevelAccess.NULL);
    }

    public AnimalHeaterMenu(int containerId, Inventory inventory, Container container, ContainerData data, ContainerLevelAccess access) {
        super(CropsAndFarmsMenuTypes.ANIMAL_HEATER, containerId);
        this.data = data;
        this.access = access;
        this.addDataSlots(data);



        int i = 36;
        int j = 137;

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(inventory, l + k * 9 + 9, i + l * 18,j + k * 18));
            }
        }

        for (int i1 = 0; i1 < 9; i1++) {
            this.addSlot(new Slot(inventory, i1, i + i1 * 18, 195));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    public long getDisplayEnergy() {
        return 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, CropsAndFarmsBlocks.ANIMAL_HEATER);
    }
}
