package me.meow.features.modules.render;

import com.google.common.eventbus.Subscribe;
import me.meow.event.impl.Render3DEvent;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import me.meow.util.render.RenderUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;

import java.awt.*;

public class BlockHighlight extends Module {
    public Setting<Mode> mode = this.register(new Setting<>("Mode", Mode.OUTLINE));
    public Setting<Double> lineThickness = this.register(new Setting<>("Line", 2.0,0.0,5.0,v -> this.mode.getValue() == Mode.OUTLINE));
    public BlockHighlight() {
        super("BlockHighlight", "", Category.RENDER, true, false, false);
    }

    @Subscribe public void onRender3D(Render3DEvent event) {
        if (mode.getValue() == Mode.OUTLINE)
        if (mc.crosshairTarget instanceof BlockHitResult result) {
            VoxelShape shape = mc.world.getBlockState(result.getBlockPos()).getOutlineShape(mc.world, result.getBlockPos());
            if (shape.isEmpty()) return;
            Box box = shape.getBoundingBox();
            box = box.offset(result.getBlockPos());
            RenderUtil.drawBox(event.getMatrix(), box, Color.white, lineThickness.getValue());
        }
        if (mode.getValue() == Mode.FILL)
        if (mc.crosshairTarget instanceof BlockHitResult result) {
            VoxelShape shape = mc.world.getBlockState(result.getBlockPos()).getOutlineShape(mc.world, result.getBlockPos());
            if (shape.isEmpty()) return;
            Box box = shape.getBoundingBox();
            box = box.offset(result.getBlockPos());
            RenderUtil.drawBoxFilled(event.getMatrix(), box, Color.white);
        }

    }
    public enum Mode{
        OUTLINE, FILL
    }
}
