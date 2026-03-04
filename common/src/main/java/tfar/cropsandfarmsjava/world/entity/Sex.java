package tfar.cropsandfarmsjava.world.entity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import tfar.cropsandfarmsjava.util.StreamCodecs;

public enum Sex {
    MALE,FEMALE;
    public static final StreamCodec<FriendlyByteBuf,Sex> STREAM_CODEC = StreamCodecs.enumCodec(Sex.class);
}
