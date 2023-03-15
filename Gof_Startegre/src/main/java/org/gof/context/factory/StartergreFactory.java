package org.gof.context.factory;

import io.vavr.control.Try;
import org.gof.constant.PayStartegreEnum;
import org.gof.inter.IPayStartergre;
import org.iiidev.utils.AttrTransferUtil;

/**
 * StartergreFactory
 *
 * @author IIIDelay
 * @createTime 2023年03月13日 22:14:00
 */
public class StartergreFactory {
    public IPayStartergre getPayStartergre(String startegreType) {
        String className = AttrTransferUtil.levelSafeGetter(startegreType, PayStartegreEnum::valueOf,
                PayStartegreEnum::className);
        return (IPayStartergre) Try.of(() -> Class.forName(className).newInstance()).getOrElse(() -> null);
    }
}
