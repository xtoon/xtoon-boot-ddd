package com.xtoon.boot.sys.domain.external;

/**
 * 生成Token外部接口
 *
 * @author haoxin
 * @date 2021-04-23
 **/
public interface TokenGeneratorExternalService {

    /**
     * 生成token
     *
     * @return
     */
    String generateValue();
}
