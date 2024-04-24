package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HugeSoulfireSmokeParticles extends TextureSheetParticle {
    protected HugeSoulfireSmokeParticles(ClientLevel clientLevel, double x, double y, double z,
                                         SpriteSet spriteSet, double xd, double yd, double zd) {
        super(clientLevel, x, y, z, xd, yd, zd);

        this.friction = 0.8f;

        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.quadSize *= 30;
        this.lifetime = 1000;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1;
        this.gCol = 1;
        this.bCol = 1;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 240;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new HugeSoulfireSmokeParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
