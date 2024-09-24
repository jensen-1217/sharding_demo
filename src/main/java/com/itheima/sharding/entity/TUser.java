package com.itheima.sharding.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName t_user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TUser implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String fullname;

    /**
     * 用户类型
     */
    private String userType;

    private static final long serialVersionUID = 1L;
}
