package com.ariks.torcherinoCe.Block.RfMolecular;

import java.awt.*;

public class TextureMolecularChanger {

    public static final TextureMolecularChanger[] TEXTURES = {
            new TextureMolecularChanger("red.png", new Color(215, 0, 0), 0),
            new TextureMolecularChanger("orange.png", new Color(215, 110, 0), 15),
            new TextureMolecularChanger("yellow.png", new Color(215, 215, 0), 30),
            new TextureMolecularChanger("green.png", new Color(35, 215, 0), 45),
            new TextureMolecularChanger("blue.png", new Color(0, 35, 215), 60),
            new TextureMolecularChanger("marine.png", new Color(0, 215, 215), 75),
            new TextureMolecularChanger("pink.png", new Color(215, 0, 140), 90),
            new TextureMolecularChanger("white.png", new Color(230, 230, 230), 105),
            new TextureMolecularChanger("black.png", new Color(20, 20, 20), 120)
    };

    final String path;
    final Color color;
    final int textureX;

    public TextureMolecularChanger(String path, Color color, int textureX) {
        this.path = path;
        this.color = color;
        this.textureX = textureX;
    }
}
