package com.example.seckilldemo.vo;

import com.example.seckilldemo.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/25 14:19
 * 登录参数
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile(required = true)
    private String mobile;

    @NotNull
    @Length(min = 6)
    private String password;
}
