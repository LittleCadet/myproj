package com.myproj.app.copy;

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
public class CopyB {

    private String name;

    private Integer age;

    private Integer sex;
}
