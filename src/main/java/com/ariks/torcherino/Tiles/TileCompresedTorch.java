package com.ariks.torcherino.Tiles;
import static com.ariks.torcherino.Config.*;

public abstract class TileCompresedTorch {
    //CompressedTileBase1
    public static final class CompressedTileBase1 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * CTorch_lvl1_S;
        }
        @Override
        protected int SpeedModes(int newSpeed) {
            return CTorch_lvl1_M+1;
        }
        @Override
        protected int Radius(int area) {
            return CTorch_lvl1_R+1;
        }
    }
    //CompressedTileBase2
    public static final class CompressedTileBase2 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * CTorch_lvl2_S;
        }
        @Override
        protected int SpeedModes(int newSpeed) {
            return CTorch_lvl2_M+1;
        }
        @Override
        protected int Radius(int area) {
            return CTorch_lvl2_R+1;
        }
    }
    //CompressedTileBase3
    public static final class CompressedTileBase3 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * CTorch_lvl3_S;
        }
        @Override
        protected int SpeedModes(int newSpeed) {
            return CTorch_lvl3_M+1;
        }
        @Override
        protected int Radius(int area) {
            return CTorch_lvl3_R+1;
        }
    }
    //CompressedTileBase4
    public static final class CompressedTileBase4 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * CTorch_lvl4_S;
        }
        @Override
        protected int SpeedModes(int newSpeed) {
            return CTorch_lvl4_M+1;
        }
        @Override
        protected int Radius(int area) {
            return CTorch_lvl4_R+1;
        }
    }
    //CompressedTileBase5
    public static final class CompressedTileBase5 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * CTorch_lvl5_S;
        }
        @Override
        protected int SpeedModes(int newSpeed) {
            return CTorch_lvl5_M+1;
        }
        @Override
        protected int Radius(int area) {
            return CTorch_lvl5_R+1;
        }
    }
}
