package br.ufpe.cin.hcs3.documentmanager.v1.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.IntStream;

@UtilityClass
public class BytesUtil {
     static Boolean hasEqualSize(byte[] left, byte[] right) {
        return left.length == right.length;
    }

     static Boolean equals(byte[] left, byte[] right) {
        return Arrays.equals(left, right);
    }

     static Integer differentOffset(byte[] left, byte[] right) {
        return IntStream.range(0, left.length)
                .filter(i -> left[i] != right[i])
                .findFirst()
                .getAsInt();
    }
}
