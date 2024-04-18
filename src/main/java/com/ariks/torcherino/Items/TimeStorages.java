package com.ariks.torcherino.Items;

import com.ariks.torcherino.util.Config;

public abstract class TimeStorages {

    public static final class TimeStorage_Lvl1 extends TimeStorage {
        public TimeStorage_Lvl1(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_Lvl_1;
        }
    }
    public static final class TimeStorage_Lvl2 extends TimeStorage {
        public TimeStorage_Lvl2(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_Lvl_2;
        }
    }
    public static final class TimeStorage_Lvl3 extends TimeStorage {
        public TimeStorage_Lvl3(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_Lvl_3;
        }
    }
    public static final class TimeStorage_Lvl4 extends TimeStorage {
        public TimeStorage_Lvl4(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_Lvl_4;
        }
    }
    public static final class TimeStorage_Lvl5 extends TimeStorage {
        public TimeStorage_Lvl5(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_Lvl_5;
        }
    }
    public static final class TimeStorage_infinite extends TimeStorage {
        public TimeStorage_infinite(String name) {
            super(name);
        }
        @Override
        protected int MaxConfigStorageTime() {
            return Config.Stored_Time_Bottle_infinite;
        }
    }
}
