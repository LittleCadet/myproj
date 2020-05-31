package com.dubbo.app.provider.spi;

import com.dubbo.app.provider.api.ProviderService;

/**
 * @author shenxie
 * @date 2020/5/31
 */
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String providerService() {
        return "succeed !";
    }
}
