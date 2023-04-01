package org.iiidev.common.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TreeNode
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 12:30:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    private Long id;
    private Long parentId;
}
