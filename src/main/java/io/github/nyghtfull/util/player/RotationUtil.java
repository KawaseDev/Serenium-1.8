package io.github.nyghtfull.util.player;

import io.github.nyghtfull.interfaces.IAccess;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationUtil implements IAccess {
    public static float smoothRotation(float from, float to, float speed) {
        float f = MathHelper.wrapAngleTo180_float(to - from);

        if (f > speed) {
            f = speed;
        }

        if (f < -speed) {
            f = -speed;
        }

        return from + f;
    }

    public static float[] getFixedRotations(float[] rotations, float[] lastRotations) {
        float yaw = rotations[0];
        float pitch = rotations[1];

        float lastYaw = lastRotations[0];
        float lastPitch = lastRotations[1];

        float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float gcd = f * f * f * 1.2F;

        float deltaYaw = yaw - lastYaw;
        float deltaPitch = pitch - lastPitch;

        float fixedDeltaYaw = deltaYaw - (deltaYaw % gcd);
        float fixedDeltaPitch = deltaPitch - (deltaPitch % gcd);

        float fixedYaw = lastYaw + fixedDeltaYaw;
        float fixedPitch = lastPitch + fixedDeltaPitch;

        return new float[] { fixedYaw, fixedPitch };
    }


    public static Vec3 getHitVec3(Entity entity) {
        Vec3 eyesPosition = mc.thePlayer.getPositionEyes(1.0f);

        float size = entity.getCollisionBorderSize();

        AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox().expand(
                size,
                size,
                size
        );

        double x = MathHelper.clamp_double(eyesPosition.xCoord, entityBoundingBox.minX, entityBoundingBox.maxX);
        double y = MathHelper.clamp_double(eyesPosition.yCoord, entityBoundingBox.minY, entityBoundingBox.maxY);
        double z = MathHelper.clamp_double(eyesPosition.zCoord, entityBoundingBox.minZ, entityBoundingBox.maxZ);

        return new Vec3(x, y, z);
    }

    public static float[] getRotations(Entity entity) {
        Vec3 vec3 = getHitVec3(entity);

        double x = vec3.xCoord - mc.thePlayer.posX;
        double y = vec3.yCoord - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double z = vec3.zCoord - mc.thePlayer.posZ;

        double theta = MathHelper.sqrt_double(x * x + z * z);

        float yaw = (float) (Math.atan2(z, x) * 180.0 / Math.PI - 90.0);
        float pitch = (float) (-(Math.atan2(y, theta) * 180.0 / Math.PI));

        return new float[] {
                (mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw)) % 360,
                (mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch)) % 360
        };
    }
}
