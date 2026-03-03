package tfar.cropsandfarmsjava.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;

public class AnimalHeaterMenu extends BlockEntityMenu<AnimalHeaterBlockEntity> {

    public AnimalHeaterMenu(int containerId, Inventory inventory, BlockPos blockPos) {
        this(containerId,inventory,new SimpleContainer(6),new SimpleContainerData(4),blockPos);
    }

    public AnimalHeaterMenu(int containerId, Inventory inventory, Container container, ContainerData data, BlockPos pos) {
        super(CAFMenuTypes.ANIMAL_HEATER,inventory, containerId,data,pos);

        addSlot(new Slot(container,0,60,34));

        for (int l = 0;l < 3;l++) {
            addSlot(new Slot(container,l+1,141,20+18*l));
        }
        addSlot(new Slot(container,0,60,34));

        int x = 8;
        int y = 84;

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(inventory, l + k * 9 + 9, x + l * 18,y + k * 18));
            }
        }

        for (int i1 = 0; i1 < 9; i1++) {
            this.addSlot(new Slot(inventory, i1, x + i1 * 18, y + 58));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }


    @Override
    public boolean stillValid(Player player) {
        return player.level().getBlockState(pos).is(CropsAndFarmsBlocks.ANIMAL_HEATER);
    }
}
