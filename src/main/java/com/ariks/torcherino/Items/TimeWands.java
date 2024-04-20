package com.ariks.torcherino.Items;

import com.ariks.torcherino.util.Config;

public abstract class TimeWands {

    public static final class TimeWand_lvl_1 extends TimeWand {
        public TimeWand_lvl_1(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl1;
        }
        @Override
        protected int DurabilityWand() {
            return 256;
        }
    }
    public static final class TimeWand_lvl_2 extends TimeWand {
        public TimeWand_lvl_2(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl2;
        }
        @Override
        protected int DurabilityWand() {
            return 512;
        }
    }
    public static final class TimeWand_lvl_3 extends TimeWand {
        public TimeWand_lvl_3(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl3;
        }
        @Override
        protected int DurabilityWand() {
            return 1024;
        }
    }
    public static final class TimeWand_lvl_4 extends TimeWand {
        public TimeWand_lvl_4(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl4;
        }
        @Override
        protected int DurabilityWand() {
            return 2048;
        }
    }
    public static final class TimeWand_lvl_5 extends TimeWand {
        public TimeWand_lvl_5(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl5;
        }
        @Override
        protected int DurabilityWand() {
            return 4096;
        }
    }
    public static final class TimeWand_lvl_6 extends TimeWand {
        public TimeWand_lvl_6(String name) {
            super(name);
        }
        @Override
        protected int SpeedWand() {
            return Config.SpeedWand_lvl6;
        }
        @Override
        protected int DurabilityWand() {
            return 1;
        }
    }
}
