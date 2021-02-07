package com.spring.app.annotation.feild;

import lombok.Data;

/**
 * @author shenxie
 * @date 2021/2/5
 */
@Data
public class Order {

    @FieldName(name = "订单号")
    private Long orderNo;

    @FieldName
    private String name;
}
