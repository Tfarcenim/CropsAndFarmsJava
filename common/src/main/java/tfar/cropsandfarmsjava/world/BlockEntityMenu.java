package tfar.cropsandfarmsjava.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public abstract class BlockEntityMenu<B extends BlockEntity> extends AbstractContainerMenu {
    private final ContainerData data;
    protected final BlockPos pos;
    protected final B blockEntity;

    protected BlockEntityMenu(@Nullable MenuType<?> menuType, Inventory inventory, int containerId, ContainerData data, BlockPos pos) {
        super(menuType, containerId);
        this.addDataSlots(data);
        this.data = data;
        this.pos = pos;
        blockEntity = (B) inventory.player.level().getBlockEntity(pos);
    }

    public B blockEntity() {
        return blockEntity;
    }

    public ContainerData data() {
        return data;
    }
}
