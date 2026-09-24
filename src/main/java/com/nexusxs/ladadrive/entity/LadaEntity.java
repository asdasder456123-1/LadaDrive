package com.nexusxs.ladadrive.entity;

import com.nexusxs.ladadrive.LadaDriveEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LadaEntity extends Entity {

    private static final double MAX_SPEED = 0.32D;
    private static final double REVERSE_SPEED = 0.16D;
    private static final double ACCELERATION = 0.035D;
    private static final double FRICTION = 0.88D;
    private static final double BRAKE_FRICTION = 0.55D;

    public LadaEntity(
            EntityType<? extends LadaEntity> type,
            World world
    ) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return getPassengerList().isEmpty();
    }

    @Override
    protected void updatePassengerPosition(
            Entity passenger,
            PositionUpdater positionUpdater
    ) {
        positionUpdater.accept(
                passenger,
                getX(),
                getY() + 0.85D,
                getZ()
        );
    }

    @Override
    public ActionResult interact(
            PlayerEntity player,
            Hand hand
    ) {
        if (!getWorld().isClient) {
            if (hasPassengers()) {
                return ActionResult.PASS;
            }

            player.startRiding(this);

            getWorld().playSound(
                    null,
                    getBlockPos(),
                    SoundEvents.BLOCK_IRON_DOOR_OPEN,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.3F
            );
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (hasPassengers()
                && getFirstPassenger() instanceof PlayerEntity player) {

            drive(player);

        } else {
            applyFriction(FRICTION);
        }

        move(
                MovementType.SELF,
                getVelocity()
        );

        if (!getWorld().isClient
                && hasPassengers()
                && getVelocity().horizontalLengthSquared() > 0.015D
                && age % 12 == 0) {

            getWorld().playSound(
                    null,
                    getBlockPos(),
                    SoundEvents.BLOCK_PISTON_EXTEND,
                    SoundCategory.NEUTRAL,
                    0.12F,
                    0.65F + (float) (Math.random() * 0.15F)
            );
        }

        if (isOnGround()) {
            setVelocity(
                    getVelocity().x,
                    0.0D,
                    getVelocity().z
            );
        }
    }

    private void drive(PlayerEntity player) {

        float forward = player.forwardSpeed;
        float steering = player.sidewaysSpeed;

        double yaw = Math.toRadians(getYaw());

        double forwardX = -Math.sin(yaw);
        double forwardZ = Math.cos(yaw);

        double rightX = Math.cos(yaw);
        double rightZ = Math.sin(yaw);

        double currentSpeed =
                Math.sqrt(
                        getVelocity().x * getVelocity().x
                        + getVelocity().z * getVelocity().z
                );

        if (Math.abs(forward) > 0.01F) {

            double acceleration =
                    forward > 0
                            ? ACCELERATION
                            : ACCELERATION * 0.65D;

            double targetSpeed =
                    forward > 0
                            ? MAX_SPEED
                            : REVERSE_SPEED;

            double desiredX =
                    forwardX * targetSpeed * forward;

            double desiredZ =
                    forwardZ * targetSpeed * forward;

            double newX =
                    approach(
                            getVelocity().x,
                            desiredX,
                            acceleration
                    );

            double newZ =
                    approach(
                            getVelocity().z,
                            desiredZ,
                            acceleration
                    );

            setVelocity(
                    newX,
                    getVelocity().y,
                    newZ
            );

        } else {
            applyFriction(BRAKE_FRICTION);
        }

        if (Math.abs(steering) > 0.01F
                && currentSpeed > 0.01D) {

            float steeringAmount =
                    steering * 3.5F;

            if (forward < 0) {
                steeringAmount = -steeringAmount;
            }

            setYaw(
                    getYaw() - steeringAmount
            );

            double speed =
                    Math.sqrt(
                            getVelocity().x * getVelocity().x
                            + getVelocity().z * getVelocity().z
                    );

            setVelocity(
                    -Math.sin(Math.toRadians(getYaw())) * speed,
                    getVelocity().y,
                    Math.cos(Math.toRadians(getYaw())) * speed
            );
        }
    }

    private void applyFriction(double friction) {
        setVelocity(
                getVelocity().x * friction,
                getVelocity().y,
                getVelocity().z * friction
        );
    }

    private double approach(
            double current,
            double target,
            double amount
    ) {
        if (current < target) {
            return Math.min(current + amount, target);
        }

        if (current > target) {
            return Math.max(current - amount, target);
        }

        return target;
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.85D;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean damage(
            net.minecraft.entity.damage.DamageSource source,
            float amount
    ) {
        // العربية لا تتدمر بالضرب العادي في النسخة التجريبية.
        return false;
    }
}
