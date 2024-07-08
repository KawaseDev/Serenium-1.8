package net.optifine.shaders.config;

public enum EnumShaderOption
{
    ANTIALIASING("ANTIALIASING", "antialiasingLevel", "0"),
    NORMAL_MAP("NORMAL_MAP", "normalMapEnabled", "true"),
    SPECULAR_MAP("SPECULAR_MAP", "specularMapEnabled", "true"),
    RENDER_RES_MUL("RENDER_RES_MUL", "renderResMul", "1.0"),
    SHADOW_RES_MUL("SHADOW_RES_MUL", "shadowResMul", "1.0"),
    HAND_DEPTH_MUL("HAND_DEPTH_MUL", "handDepthMul", "0.125"),
    CLOUD_SHADOW("CLOUD_SHADOW", "cloudShadow", "true"),
    OLD_HAND_LIGHT("OLD_HAND_LIGHT", "oldHandLight", "default"),
    OLD_LIGHTING("OLD_LIGHTING", "oldLighting", "default"),
    SHADER_PACK("SHADER_PACK", "shaderPack", ""),
    TWEAK_BLOCK_DAMAGE("TWEAK_BLOCK_DAMAGE", "tweakBlockDamage", "false"),
    SHADOW_CLIP_FRUSTRUM("SHADOW_CLIP_FRUSTRUM", "shadowClipFrustrum", "true"),
    TEX_MIN_FIL_B("TEX_MIN_FIL_B", "TexMinFilB", "0"),
    TEX_MIN_FIL_N("TEX_MIN_FIL_N", "TexMinFilN", "0"),
    TEX_MIN_FIL_S("TEX_MIN_FIL_S", "TexMinFilS", "0"),
    TEX_MAG_FIL_B("TEX_MAG_FIL_B", "TexMagFilB", "0"),
    TEX_MAG_FIL_N("TEX_MAG_FIL_N", "TexMagFilN", "0"),
    TEX_MAG_FIL_S("TEX_MAG_FIL_S", "TexMagFilS", "0");

    private String resourceKey = null;
    private String propertyKey = null;
    private String valueDefault = null;

    private EnumShaderOption(String resourceKey, String propertyKey, String valueDefault)
    {
        this.resourceKey = resourceKey;
        this.propertyKey = propertyKey;
        this.valueDefault = valueDefault;
    }

    public String getResourceKey()
    {
        return this.resourceKey;
    }

    public String getPropertyKey()
    {
        return this.propertyKey;
    }

    public String getValueDefault()
    {
        return this.valueDefault;
    }
}
