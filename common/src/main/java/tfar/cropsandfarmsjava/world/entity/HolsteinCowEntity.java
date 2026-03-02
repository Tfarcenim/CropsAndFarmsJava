package tfar.cropsandfarmsjava.world.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HolsteinCowEntity extends Cow implements GeoEntity {

    AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public HolsteinCowEntity(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        boolean isDead = this.dead || this.isDeadOrDying();
        controllers.add(new AnimationController<>(this, "idle_controller", 0, event -> {
            return event.setAndContinue(DefaultAnimations.IDLE);
                })
                .triggerableAnim("die", DefaultAnimations.DIE));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
