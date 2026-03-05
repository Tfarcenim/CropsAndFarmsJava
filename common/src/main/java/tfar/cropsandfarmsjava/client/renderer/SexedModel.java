package tfar.cropsandfarmsjava.client.renderer;

import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import tfar.cropsandfarmsjava.world.entity.Sex;

public record SexedModel<G extends GeoAnimatable>(GeoModel<G> male,GeoModel<G> female) {
    public GeoModel<G> getModel(Sex sex) {
        return switch (sex) {
            case MALE -> male;
            case FEMALE -> female;
        };
    }
}
