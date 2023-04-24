package org.jvm.monitor;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * I
 *
 * @author IIIDelay
 * @createTime 2023年03月30日 09:47:00
 */
public class I {
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100);
        boolean b = bloomFilter.mightContain(1);
        boolean b2 = bloomFilter.mightContain(2);
        System.out.println("b = " + b);
        System.out.println("b2 = " + b2);

        bloomFilter.put(1);
        bloomFilter.put(2);
        bloomFilter.put(3);
        boolean b3 = bloomFilter.mightContain(2);
        System.out.println("b3 = " + b3);
    }
}
