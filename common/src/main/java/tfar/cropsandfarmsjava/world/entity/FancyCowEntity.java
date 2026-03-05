package tfar.cropsandfarmsjava.world.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import tfar.cropsandfarmsjava.network.CropsAndFarmsEntityDataSerializers;
import tfar.cropsandfarmsjava.util.StreamCodecs;

public class FancyCowEntity extends Cow implements GeoEntity {

    AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Sex> SEX = SynchedEntityData.defineId(FancyCowEntity.class, CropsAndFarmsEntityDataSerializers.SEX);
    private static final EntityDataAccessor<Variant> VARIANT = SynchedEntityData.defineId(FancyCowEntity.class, CropsAndFarmsEntityDataSerializers.COW_VARIANT);


    public FancyCowEntity(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkController(this));
        controllers.add(DefaultAnimations.genericDeathController(this));
    }

    public Sex getSex() {
        return entityData.get(SEX);
    }

    void setSex(Sex sex) {
        entityData.set(SEX,sex);
    }

    public Variant getVariant() {
        return entityData.get(VARIANT);
    }

    public void setVariant(Variant variant) {
        entityData.set(VARIANT,variant);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SEX,Sex.MALE);
        builder.define(VARIANT,Variant.HOLSTEIN);
    }



    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        String s = compound.getString("sex");
        if (!s.isBlank()) {
            setSex(Sex.valueOf(s));
        }
        String v = compound.getString("variant");
        if (!v.isBlank()) {
            setVariant(Variant.valueOf(s));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("sex",getSex().name());
        compound.putString("variant", getVariant().name());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData spawnGroupData1 = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        setSex(level.getRandom().nextBoolean() ? Sex.MALE : Sex.FEMALE);
        setVariant(Variant.pickRandom(level.getRandom()));
        return spawnGroupData1;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum Variant implements StringRepresentable {
        HOLSTEIN("holstein"),ANGUS("angus");

        public static final StreamCodec<FriendlyByteBuf,Variant> STREAM_CODEC = StreamCodecs.enumCodec(Variant.class);

        private final String name;

        Variant(String name) {
            this.name = name;
        }

        public static Variant pickRandom(RandomSource random) {
            int o = random.nextInt(Variant.values().length);
            return Variant.values()[o];
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
