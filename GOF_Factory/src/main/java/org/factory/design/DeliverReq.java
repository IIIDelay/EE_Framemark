package org.factory.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DeliverReq
 *
 * @author IIIDelay
 * @createTime 2023年04月08日 23:58:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverReq {
    private String userName;
    private Long sku;
    private Long orderId;
    private Long userPhone;
    private String consigneeUserName;
    private String consigneeUserPhone;
    private String consigneeUserAddress;

    public void setConsigneeUserAddress(String consigneeUserAddress) {
        this.consigneeUserAddress = consigneeUserAddress;
    }

    public String getConsigneeUserAddress() {
        return consigneeUserAddress;
    }
}
