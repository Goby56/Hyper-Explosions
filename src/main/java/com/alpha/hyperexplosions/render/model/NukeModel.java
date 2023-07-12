package com.alpha.hyperexplosions.render.model;

import com.alpha.hyperexplosions.HyperExplosions;
import me.x150.renderer.Renderer;
import me.x150.renderer.objfile.ObjFile;
import me.x150.renderer.util.RendererUtils;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class NukeModel {
    private static final Path path = HyperExplosions.getAssetsDir().resolve("models").resolve("custom").resolve("nuke");
    private final ObjFile obj;

    public NukeModel() {
        ObjFile obj;
        try {
            ObjFile.ResourceProvider provider = ObjFile.ResourceProvider.ofPath(path);
            obj = new ObjFile("model.obj", provider);
            provider.open("material.mtl");
            provider.open("texture.png");
        } catch (IOException e) {
            obj = null;
            e.printStackTrace();
        }
        this.obj = obj;
    }

    public void render(MatrixStack stack, Vec3d origin) {
        this.obj.draw(stack, new Matrix4f(), origin);
    }
}
