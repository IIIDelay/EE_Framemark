package org.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * E
 *
 * @author IIIDelay
 * @createTime 2023年03月29日 15:17:00
 */
public class E {
    public static void main(String[] args) {
        // weakReferenceMethod();
        // 虚引用
        ReferenceQueue<MyWork> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyWork> phantomReference = new PhantomReference<>(new MyWork(), referenceQueue);
        System.out.printf("xx: ", phantomReference.get());
        System.out.println();

    }

    private static void weakReferenceMethod() {
        WeakReference<MyWork> myWorkWeakReference = new WeakReference<>(new MyWork());
        System.gc();
        System.out.println("end ... ");
    }
}

class MyWork {
    private String uname;
    private String solar;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("... invoke finalize ...");
    }
}
