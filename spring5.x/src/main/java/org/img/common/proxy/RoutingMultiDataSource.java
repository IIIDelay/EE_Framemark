package org.img.common.proxy;

import org.img.common.DSTypeContainer;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * RoutingMultiDataSource
 *
 * @author IIIDelay
 * @createTime 2023年03月17日 22:20:00
 */
public class RoutingMultiDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DSTypeContainer.get();
    }
}
