package com.itheima.sharding.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName t_dict
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TDict implements Serializable {
    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典值
     */
    private String value;

    private static final long serialVersionUID = 1L;
}
