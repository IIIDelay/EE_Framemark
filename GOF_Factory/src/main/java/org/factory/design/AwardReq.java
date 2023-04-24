package org.factory.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * AwardReq
 *
 * @author IIIDelay
 * @createTime 2023年04月08日 23:20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardReq {
    private int awardType;
    private int uid;
    private Long awardNumber;
    private Long bizId;
    private Map<String, String> extMap;
}
