package com.fuiou.mchnt.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2019/10/25
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmDto {

    private String trace_no;

    private String ins_cd;

    private String mchnt_cd;

    private String sign;

}
