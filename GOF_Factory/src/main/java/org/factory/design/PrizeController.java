package org.factory.design;

import org.factory.service.CouponService;
import org.factory.service.GoodsService;
import org.factory.service.IQiYiCardService;
import org.factory.vo.CouponResult;
import org.iiidev.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PrizeController
 *
 * @author IIIDelay
 * @createTime 2023年04月08日 23:20:00
 */
public class PrizeController {
    private Logger logger = LoggerFactory.getLogger(PrizeController.class);

    public AwardRes awardForUser(AwardReq req) {
        String reqJson = JsonUtil.objToStr(req);
        logger.info("内容: {}, 请求id: {}", reqJson, req.getUid());
        AwardRes awardRes = null;
        if (1 == req.getAwardType()) {
            CouponService couponService = new CouponService();
            CouponResult couponResult = couponService.sendCoupon(req.getUid(), req.getAwardNumber());
            if ("0000".equals(couponResult.getCode())) {
                awardRes = new AwardRes("0000", "发射成功");
            } else {
                awardRes = new AwardRes("0001", couponResult.getInfo());
            }
        } else if (2 == req.getAwardType()) {
            GoodsService goodsService = new GoodsService();
            DeliverReq deliverReq = new DeliverReq();
            deliverReq.setUserName(queryUserName(req.getUid()));
            deliverReq.setUserPhone(queryUserPhone(req.getUid()));
            deliverReq.setSku(req.getAwardNumber());
            deliverReq.setOrderId(req.getBizId());
            deliverReq.setConsigneeUserName(req.getExtMap().get("consigneeUserName"));
            deliverReq.setConsigneeUserPhone(req.getExtMap().get("consigneeUserPhone"));
            deliverReq.setConsigneeUserAddress(req.getExtMap().get("consigneeUserAddress"));
            Boolean isSuccess = goodsService.deliverGoods(deliverReq);
            if (isSuccess) {
                awardRes = new AwardRes("0000", "􀝎􀶱􀱮􀛑");
            } else {
                awardRes = new AwardRes("0001", "􀝎􀶱􀥦􁨳");
            }
        } else if (3 == req.getAwardType()) {
            Long bindMobileNumber = queryUserPhone(req.getUid());
            IQiYiCardService iQiYiCardService = new IQiYiCardService();
            iQiYiCardService.grantToken(bindMobileNumber, req.getAwardNumber());
            awardRes = new AwardRes("0000", "发放成功");
        }
        return awardRes;
    }

    private Long queryUserPhone(int uid) {
        return 18813149988L;
    }

    private String queryUserName(int uid) {
        return "花花";
    }
}
