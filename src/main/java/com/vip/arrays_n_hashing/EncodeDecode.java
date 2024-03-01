package com.vip.arrays_n_hashing;

import java.util.ArrayList;
import java.util.List;

// Problem: Complete encode() and decode() methods for network transmission. 
// Use Len:String format to transmit. Since the len can be 200 (size 3),len size =3.
// Java: String.format for int to fixed length str, Integer.parseInt for str -> int 
// substring method is [) start - inclusive, end -exclusive.
// Kept string type instead of converting to char array which is more efficient but tricky given the method return types.
public class EncodeDecode {

    public class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            strs.forEach(str -> {
                String len = String.format("%03d", str.length());
                sb.append(len + str);
            });
            // System.out.println(sb.toString());
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> retValList = new ArrayList<>();

            int i = 0;
            while (i < s.length()) {

                int len = Integer.parseInt(s.substring(i, i + 3));
                String str = s.substring(i + 3, i + 3 + len);
                // System.out.println(str);
                retValList.add(str);
                i += len + 3;
            }

            // retValList.forEach(l -> System.out.println(l));
            return retValList;
        }
    }

    void test_01() {
        List<String> strs = new ArrayList<>();
        strs.add("HelloMUTHU");
        strs.add("World");
        Codec codec = new Codec();
        System.out.println(codec.decode(codec.encode(strs)));
    }

    void test_02() {
        List<String> strs = new ArrayList<>();
        strs.add("");
        // strs.add("World");
        Codec codec = new Codec();
        System.out.println(codec.decode(codec.encode(strs)));
    }

    public static void main(String[] args) {
        EncodeDecode obj = new EncodeDecode();
        obj.test_01();
        obj.test_02();
    }
}
