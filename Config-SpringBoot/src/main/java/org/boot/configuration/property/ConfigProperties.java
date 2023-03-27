package org.boot.configuration.property;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.iiidev.common.constant.CacheConstant;
import org.iiidev.common.constant.DataBaseConstant;
import org.iiidev.utils.AttrTransferUtil;
import org.redisson.config.Config;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * ConfigProperties
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:20:00
 */
@Component
public class ConfigProperties implements EnvironmentAware {
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    /**
     * dataSourceChoose : 根据前缀区分数据源配置信息
     *
     * @param dbPrefix dbPrefix
     * @return DataSource
     */
    public DataSource dataSourceChoose(String dbPrefix, Consumer<DataSource> extendAttr) {
        DruidDataSource dds = new DruidDataSource();
        if (StringUtils.isNotEmpty(dbPrefix)) {
            dds.setUsername(env.getProperty(dbPrefix + DataBaseConstant.USER_NAME));
            dds.setPassword(env.getProperty(dbPrefix + DataBaseConstant.PWD));
            dds.setUrl(env.getProperty(dbPrefix + DataBaseConstant.URL));
            dds.setDriverClassName(env.getProperty(dbPrefix + DataBaseConstant.DRIVER_CLASS));
            // 除基本连接外的扩展属性
            Optional.ofNullable(extendAttr).ifPresent(setter -> setter.accept(dds));
        }
        return dds;
    }

    /**
     * redisConfiguration
     *
     * @param deploymentMode deploymentMode
     * @return RedisConfiguration
     */
    public RedisConfiguration redisConfigurationChoose(CacheConstant.DeploymentMode deploymentMode) {
        // 如果 deploymentMode 为单机模式则执行 redis单机配置
        if (Objects.equals(deploymentMode, CacheConstant.DeploymentMode.STAND_ALONE)) {
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
            configuration.setHostName(env.getProperty("redis." + deploymentMode.mode + ".hostname"));
            configuration.setPort(AttrTransferUtil.safeGetterElse(env.getProperty("redis." + deploymentMode.mode +
                            ".port"),
                    Integer::parseInt, 0));
            return configuration;
        }
        return null;
    }

    /**
     * redissionConfigChoose
     *
     * @param deploymentMode deploymentMode
     * @param appendAttr     appendAttr
     * @return Config
     */
    public Config redissionConfigChoose(CacheConstant.DeploymentMode deploymentMode, Consumer<Config> appendAttr) {
        Config config;
        if (Objects.equals(deploymentMode, CacheConstant.DeploymentMode.STAND_ALONE)) {
            config = new Config();
            config.useSingleServer().setAddress(env.getProperty("redission." + deploymentMode.mode + ".server"));
            Optional.ofNullable(appendAttr).ifPresent(setter -> setter.accept(config));
            return config;
        }
        return null;
    }

}
