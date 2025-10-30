package com.artiks.torcherinoCe.network;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataSerializer {
    private static final Map<Class<?>, Integer> TYPE_IDS = new HashMap<>();
    private static final Map<Integer, Class<?>> ID_TYPES = new HashMap<>();

    static {
        registerType(0, Integer.class);
        registerType(1, String.class);
        registerType(2, Boolean.class);
        registerType(3, Float.class);
        registerType(4, Double.class);
        registerType(5, int[].class);
        registerType(6, String[].class);
        registerType(7, Byte.class);
        registerType(8, Short.class);
        registerType(9, Long.class);
    }
    public static void registerType(int id, Class<?> clazz) {
        TYPE_IDS.put(clazz, id);
        ID_TYPES.put(id, clazz);
    }

    public static byte[] serialize(Map<String, Object> data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(data.size());
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                dos.writeUTF(entry.getKey());
                Object value = entry.getValue();
                Class<?> type = value.getClass();
                Integer typeId = TYPE_IDS.get(type);
                if (typeId == null) {
                    throw new RuntimeException("Unsupported type: " + type);
                }
                dos.writeByte(typeId);
                writeValue(dos, value, type);
            }
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed", e);
        }
        return baos.toByteArray();
    }

    public static Map<String, Object> deserialize(byte[] data) {
        Map<String, Object> result = new HashMap<>();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bais);
        try {
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String key = dis.readUTF();
                int typeId = dis.readByte();
                Class<?> type = ID_TYPES.get(typeId);
                if (type == null) {
                    throw new RuntimeException("Unknown type ID: " + typeId);
                }
                Object value = readValue(dis, type);
                result.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
        return result;
    }

    private static void writeValue(DataOutputStream dos, Object value, Class<?> type) throws IOException {
        if (type == Integer.class) {
            dos.writeInt((Integer) value);
        } else if (type == String.class) {
            dos.writeUTF((String) value);
        } else if (type == Long.class) {
            dos.writeLong((Long) value);
        } else if (type == Boolean.class) {
            dos.writeBoolean((Boolean) value);
        } else if (type == Float.class) {
            dos.writeFloat((Float) value);
        } else if (type == Double.class) {
            dos.writeDouble((Double) value);
        } else if (type == int[].class) {
            int[] array = (int[]) value;
            dos.writeInt(array.length);
            for (int val : array) {
                dos.writeInt(val);
            }
        } else if (type == String[].class) {
            String[] array = (String[]) value;
            dos.writeInt(array.length);
            for (String val : array) {
                dos.writeUTF(val);
            }
        }
    }

    private static Object readValue(DataInputStream dis, Class<?> type) throws IOException {
        if (type == Integer.class) {
            return dis.readInt();
        } else if (type == String.class) {
            return dis.readUTF();
        } else if (type == Long.class) {
            return dis.readLong();
        } else if (type == Boolean.class) {
            return dis.readBoolean();
        } else if (type == Float.class) {
            return dis.readFloat();
        } else if (type == Double.class) {
            return dis.readDouble();
        } else if (type == int[].class) {
            int length = dis.readInt();
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = dis.readInt();
            }
            return array;
        } else if (type == String[].class) {
            int length = dis.readInt();
            String[] array = new String[length];
            for (int i = 0; i < length; i++) {
                array[i] = dis.readUTF();
            }
            return array;
        }
        return null;
    }
}