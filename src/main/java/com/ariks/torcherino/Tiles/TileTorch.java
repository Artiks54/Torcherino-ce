package com.ariks.torcherino.Tiles;

import static com.ariks.torcherino.util.Config.*;

public abstract class TileTorch {
    //TileBase1
        public static final class TileBase1 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * Torch_lvl1_S;
        }
        @Override
        protected int SpeedModes() {
            return Torch_lvl1_M + 1;
        }
        @Override
        protected int Radius() {
            return Torch_lvl1_R+1;
        }
    }
    //TileBase2
        public static final class TileBase2 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * Torch_lvl2_S;
        }
        @Override
        protected int SpeedModes() {
            return Torch_lvl2_M+1;
        }
        @Override
        protected int Radius() {
            return Torch_lvl2_R+1;
        }
    }
    //TileBase3
        public static final class TileBase3 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
            return base * Torch_lvl3_S;
        }
        @Override
        protected int SpeedModes() {
            return Torch_lvl3_M + 1;
        }
        @Override
        protected int Radius() {
            return Torch_lvl3_R + 1;
        }
    }
    //TileBase4
        public static final class TileBase4 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
                return base * Torch_lvl4_S;
            }
        @Override
        protected int SpeedModes() {
                return Torch_lvl4_M + 1;
            }
        @Override
        protected int Radius() {
                return Torch_lvl4_R + 1;
            }
    }
    //TileBase5
        public static final class TileBase5 extends TileTorcherinoBase {
        @Override
        protected int speedBase(int base) {
                    return base * Torch_lvl5_S;
                }
        @Override
        protected int SpeedModes() {
                    return Torch_lvl5_M + 1;
                }
        @Override
        protected int Radius() {
                    return Torch_lvl5_R + 1;
                }
    }
}
