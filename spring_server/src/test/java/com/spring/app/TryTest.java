package com.spring.app;

import com.spring.app.springtry.CustomException;
import com.spring.app.springtry.TryConfig;
import com.spring.app.springtry.TryFacade;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TryTest {

    @Autowired
    private TryFacade tryFacade;

    @Autowired
    private TryConfig tryConfig;

    @Test
    public void tryTest()  {
        try {
            // 注意：在spring retry中的要执行的代码，必须是有返回值的，不然他总是会提示需要return一个返回值
            tryConfig.getInstance().execute(retryContext -> tryFacade.customException());
        }catch (CustomException e) {
            log.error(String.format("在junit  CustomException中捕获到异常: %s",e));
        }catch (Exception e) {
            log.error(String.format("在junit  Exception中捕获到异常: %s",e));
        }
    }
}
