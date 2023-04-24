package org.boot.compoment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * MySelect
 *
 * @author IIIDelay
 * @createTime 2023年03月31日 13:24:00
 */
public class MySelect implements DeferredImportSelector {
    @Override
    public Class<? extends Group> getImportGroup() {
        return Group01.class;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("invoke MySelect`s selectImports...");
        return new String[0];
    }
}
