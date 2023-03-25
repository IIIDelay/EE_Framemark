package org.img.common.attribute;

import com.alibaba.druid.pool.DruidDataSource;
import org.iiidev.utils.PropertiesHelper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * DataSourceAttr
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 21:58:00
 */
@Component
public class DataSourceAttr {
    public DataSource pg() {
        return switchDataSource("pg");
    }

    public DataSource mysql() {
        return switchDataSource("mysql");
    }

    private DataSource switchDataSource(String prefix) {
        DruidDataSource dds = new DruidDataSource();
        Properties ppt = PropertiesHelper.loadProperties("db/db.properties", prefix);
        dds.setDriverClassName(ppt.getProperty(prefix + ".driverClassName"));
        dds.setUrl(ppt.getProperty(prefix + ".url"));
        dds.setUsername(ppt.getProperty(prefix + ".username"));
        dds.setPassword(ppt.getProperty(prefix + ".pwd"));
        return dds;
    }

}
