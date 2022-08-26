package com.king.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author getao
 * @date 2022/6/30 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;

    private String serial;
}
