package me.meow.manager;

import com.google.common.eventbus.Subscribe;
import me.meow.event.impl.UpdateEvent;
import me.meow.features.Feature;
import me.meow.util.traits.Util;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HoleManager extends Feature {
    private final int range = 8;
    private final List<Hole> holes = new ArrayList<>();
    private final BlockPos.Mutable pos = new BlockPos.Mutable();

    public HoleManager() {
        Util.EVENT_BUS.register(this);
    }

    @Subscribe private void onTick(UpdateEvent event) {
        holes.clear();
        for (int x = -range; x < range; x++) {
            for (int y = -range; y < range; y++) {
                for (int z = -range; z < range; z++) {
                    pos.set(Util.mc.player.getX() + x, Util.mc.player.getY() + y, Util.mc.player.getZ() + z);
                    Hole hole = getHole(pos);
                    if (hole == null) continue;
                    holes.add(hole);
                }
            }
        }
    }

    @Nullable public Hole getHole(BlockPos pos) {
        if (Util.mc.world.getBlockState(pos).getBlock() != Blocks.AIR)
            return null;
        HoleType type = HoleType.BEDROCK;
        for (Direction direction : Direction.Type.HORIZONTAL) {
            Block block = Util.mc.world.getBlockState(pos.offset(direction)).getBlock();
            if (block == Blocks.OBSIDIAN) type = HoleType.UNSAFE;
            else if (block != Blocks.BEDROCK) return null;
        }
        return new Hole(pos, type);
    }

    private record Hole(BlockPos pos, HoleType holeType) {
    }

    private enum HoleType {
        BEDROCK,
        UNSAFE;
    }
}
