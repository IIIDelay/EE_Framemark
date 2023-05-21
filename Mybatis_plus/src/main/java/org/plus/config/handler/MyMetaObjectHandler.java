package org.plus.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        strictFillStrategy(metaObject, "create_time", LocalDateTime::now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictFillStrategy(metaObject, "update_time", LocalDateTime::now);
    }
}
