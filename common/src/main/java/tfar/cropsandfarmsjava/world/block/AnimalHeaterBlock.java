package tfar.cropsandfarmsjava.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import tfar.cropsandfarmsjava.platform.Services;
import tfar.cropsandfarmsjava.world.CropsAndFarmsBlockEntityTypes;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;

public class AnimalHeaterBlock extends Block implements EntityBlock {
    public AnimalHeaterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(ItemTags.COALS)) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AnimalHeaterBlockEntity animalHeaterBlockEntity) {
                if (!level.isClientSide) {
                    boolean b = animalHeaterBlockEntity.tryAddCoal();
                    if (b) {
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                    }
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AnimalHeaterBlockEntity animalHeaterBlockEntity) {
            if (!level.isClientSide) {
                Services.PLATFORM.openExtendedMenu((ServerPlayer) player,animalHeaterBlockEntity,pos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide? null : createTickerHelper(blockEntityType, CropsAndFarmsBlockEntityTypes.ANIMAL_HEATER,AnimalHeaterBlockEntity::serverTick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> serverType, BlockEntityType<E> clientType, BlockEntityTicker<? super E> ticker
    ) {
        return clientType == serverType ? (BlockEntityTicker<A>)ticker : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AnimalHeaterBlockEntity(blockPos,blockState);
    }
}
