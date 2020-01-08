package com.myproj.suixinfu.suixingpay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2020/1/2
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeResponse {

    private String code;

    private RespData respData;

}
