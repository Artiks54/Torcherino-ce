package com.ariks.torcherino.Tiles;

import static com.ariks.torcherino.util.Config.*;

public abstract class TileDCompresedTorch {
    //DCompressedTileBase1
    public static final class DCompressedTileBase1 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * DTorch_lvl1_S;
        }
        @Override
        protected int SpeedModes() {
            return DTorch_lvl1_M+1;
        }
        @Override
        protected int Radius() {
            return DTorch_lvl1_R+1;
        }
    }
    //DCompressedTileBase2
    public static final class DCompressedTileBase2 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * DTorch_lvl2_S;
        }
        @Override
        protected int SpeedModes() {
            return DTorch_lvl2_M+1;
        }
        @Override
        protected int Radius() {
            return DTorch_lvl2_R+1;
        }
    }
    //DCompressedTileBase3
    public static final class DCompressedTileBase3 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * DTorch_lvl3_S;
        }
        @Override
        protected int SpeedModes() {
            return DTorch_lvl3_M+1;
        }
        @Override
        protected int Radius() {
            return DTorch_lvl3_R+1;
        }
    }
    //DCompressedTileBase4
    public static final class DCompressedTileBase4 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * DTorch_lvl4_S;
        }
        @Override
        protected int SpeedModes() {
            return DTorch_lvl4_M+1;
        }
        @Override
        protected int Radius() {
            return DTorch_lvl4_R+1;
        }
    }
    //DCompressedTileBase5
    public static final class DCompressedTileBase5 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * DTorch_lvl5_S;
        }
        @Override
        protected int SpeedModes() {
            return DTorch_lvl5_M+1;
        }
        @Override
        protected int Radius() {
            return DTorch_lvl5_R+1;
        }
    }
}
