package org.boot.compoment;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Group01
 *
 * @author IIIDelay
 * @createTime 2023年03月31日 13:28:00
 */
public class Group01 implements DeferredImportSelector.Group {
    @Override
    public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
        System.out.println("xxx process...");
    }

    @Override
    public Iterable<Entry> selectImports() {
        return Lists.newArrayList();
    }
}
