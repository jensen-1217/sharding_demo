package com.itheima.sharding.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName tb_log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbLog implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String info;

    private static final long serialVersionUID = 1L;
}
