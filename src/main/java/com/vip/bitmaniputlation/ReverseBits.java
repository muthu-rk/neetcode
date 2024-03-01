package com.vip.bitmaniputlation;

// https://leetcode.com/problems/reverse-bits/

// 1. Java has no unsigned int. Doesnt affect the output. Use Integer.toBinaryString to print binary equivalent of int.
// 2. Loop for 32 times and >> input by 1 and get LSB using & 1. Then OR this LSB with << 1 out val.
// 3. Loop exit condition: while (i < 32 && n != 0)
// 4. Add extra 0s to out if n=0 but i<32. Run additional loop.
public class ReverseBits {
    public int reverseBits(int n) {
        int out = 0;
        int i = 0;
        while (i < 32 && n != 0) {
            // System.out.println("n: " + Integer.toBinaryString(n));
            int andVal = n & 1;
            n = n >> 1;
            out = out << 1;
            out |= andVal;
            i++;
            // System.out.println("out: " + Integer.toBinaryString(out));
        }

        while (i < 32) {
            out = out << 1;
            i++;
        }

        return out;
    }

    void test_01() {
        int n = 15065253;
        // int n = -3;
        System.out.println(Integer.toBinaryString(n));
        System.out.println("Test 01: ");
        int out = reverseBits(n);
        System.out.println(Integer.toBinaryString(out));
        System.out.println(out);

    }

    public static void main(String[] args) {
        ReverseBits obj = new ReverseBits();
        obj.test_01();
    }
}
