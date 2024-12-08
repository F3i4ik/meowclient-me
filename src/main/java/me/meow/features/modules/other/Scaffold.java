package me.meow.features.modules.other;

import me.meow.Meow;
import me.meow.event.eventbus.EventHandler;
import me.meow.event.eventbus.EventPriority;
import me.meow.event.impl.LookAtEvent;
import me.meow.event.impl.MoveEvent;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import me.meow.util.models.Timer;
import me.meow.util.module.MovementUtil;
import me.meow.util.player.InventoryUtil2;

import me.meow.util.world.BlockUtil2;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import static me.meow.util.world.BlockUtil2.getPlaceSide;

public class Scaffold extends Module {
    public static Scaffold INSTANCE;
    public Setting<Boolean> tower = this.register(new Setting<>("Exp", false));
    public Setting<Boolean> packetPlace = this.register(new Setting<>("Exp", false));
    public Setting<Boolean> safeWalk = this.register(new Setting<>("Exp", false));
    public Setting<Boolean> rotate = this.register(new Setting<>("Exp", false));
    public Setting<Boolean> yawStep = this.register(new Setting<>("Exp", false));
    public Setting<Integer> steps = this.register(new Setting<>("Red", 108, 0, 255));
    public Setting<Boolean> checkFov = this.register(new Setting<>("Exp", false));
    public Setting<Integer> fov = this.register(new Setting<>("Red", 108, 0, 255));
    public Setting<Integer> priority = this.register(new Setting<>("Red", 108, 0, 255));
    public Setting<Integer> rotateTime = this.register(new Setting<>("Red", 108, 0, 255));
    public Scaffold() {
        super("Scaffold","", Category.MOVEMENT,true,false,false);
        INSTANCE = this;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMove(MoveEvent event) {
        if (!safeWalk.getValue()) return;
        SafeWalk.INSTANCE.onMove(event);
    }

    private final Timer timer = new Timer();

    private Vec3d vec;

    @EventHandler
    public void onRotation(LookAtEvent event) {
        if (rotate.getValue() && !timer.passedMs(rotateTime.getValue()) && vec != null) {
            event.setTarget(vec, steps.getValue(), priority.getValue());
        }
    }

    @Override
    public void onEnable() {
        lastVec3d = null;
        pos = null;
    }

    private BlockPos pos;
    private static Vec3d lastVec3d;

    
    private final Timer towerTimer = new Timer();

    @Override
    public void onUpdate() {
        int block = InventoryUtil2.findBlock();
        if (block == -1) return;
        BlockPos placePos = mc.player.getBlockPos().down();
        if (BlockUtil2.clientCanPlace(placePos, false)) {
            int old = mc.player.getInventory().selectedSlot;
            if (getPlaceSide(placePos) == null) {
                double distance = 1000;
                BlockPos bestPos = null;
                for (Direction i : Direction.values()) {
                    if (i == Direction.UP) continue;
                    if (BlockUtil2.canPlace(placePos.offset(i))) {
                        if (bestPos == null || mc.player.squaredDistanceTo(placePos.offset(i).toCenterPos()) < distance) {
                            bestPos = placePos.offset(i);
                            distance = mc.player.squaredDistanceTo(placePos.offset(i).toCenterPos());
                        }
                    }
                }
                if (bestPos != null) {
                    placePos = bestPos;
                } else {
                    return;
                }
            }
            if (rotate.getValue()) {
                Direction side = getPlaceSide(placePos);
                vec = (placePos.offset(side).toCenterPos().add(side.getOpposite().getVector().getX() * 0.5, side.getOpposite().getVector().getY() * 0.5, side.getOpposite().getVector().getZ() * 0.5));
                timer.reset();
                if (!faceVector(vec)) return;
            }
            InventoryUtil2.switchToSlot(block);
            BlockUtil2.placeBlock(placePos, false, packetPlace.getValue());
            InventoryUtil2.switchToSlot(old);
            if (rotate.getValue()) {
                Meow.rotationManager.snapBack();
            }
            pos = placePos;
            if (tower.getValue() && mc.options.jumpKey.isPressed() && !MovementUtil.isMoving()) {
                MovementUtil.setMotionY(0.42);
                MovementUtil.setMotionX(0);
                MovementUtil.setMotionZ(0);
                if (this.towerTimer.passedMs(1500L)) {
                    MovementUtil.setMotionY(-0.28);
                    this.towerTimer.reset();
                }
            } else {
                this.towerTimer.reset();
            }
        }
    }

    private boolean faceVector(Vec3d directionVec) {
        if (!yawStep.getValue()) {
            Meow.rotationManager.lookAt(directionVec);
            return true;
        } else {
            if (Meow.rotationManager.inFov(directionVec, fov.getValue())) {
                return true;
            }
        }
        return !checkFov.getValue();
    }



}
