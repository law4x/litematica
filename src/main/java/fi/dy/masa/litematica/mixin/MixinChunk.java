package fi.dy.masa.litematica.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import fi.dy.masa.litematica.util.WorldUtils;

@Mixin(Chunk.class)
public abstract class MixinChunk
{
    @Redirect(method = "setBlockState",
              at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isRemote:Z"))
    private boolean redirectIsRemote(World world)
    {
        return WorldUtils.shouldPreventBlockUpdates() ? true : world.isRemote;
    }
}
