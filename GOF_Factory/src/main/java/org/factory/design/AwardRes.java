package org.factory.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AwardRes
 *
 * @author IIIDelay
 * @createTime 2023年04月08日 23:20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardRes {
    private String number;
    private String msg;
}
