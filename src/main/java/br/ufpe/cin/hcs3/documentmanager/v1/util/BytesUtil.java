package br.ufpe.cin.hcs3.documentmanager.v1.util;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class BytesUtil {

    private BytesUtil(){
        throw new IllegalStateException("Utility class");
    }

    public static Boolean hasEqualSize(byte[] left, byte[] right) {
        return left.length == right.length;
    }

    public static Boolean equals(byte[] left, byte[] right) {
        return Arrays.equals(left, right);
    }

    public static OptionalInt differentOffset(byte[] left, byte[] right) {
        return IntStream.range(0, left.length)
                .filter(i -> left[i] != right[i])
                .findFirst();
    }
}
