package com.sunflower.goku.zhifubao.repository;

import com.sunflower.goku.zhifubao.entity.Wallet;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 钱包的持久层
 */
public interface WalletMapper {

    /**
     * 获取用户的钱包信息
     *
     * @param userId 用户Id
     * @return 钱包
     */
    Wallet getByUserId(@Param("userId") long userId);

    /**
     * 查询用户的钱包信息
     *
     * @param userId 用户id
     * @return 钱包
     */
    Wallet getByUserId4Update(@Param("userId") long userId);

    /**
     * 资金流出
     *
     * @param userId 用户
     * @param amount 交易金额
     * @return 更新条数
     */
    int tradeOut(@Param("userId") long userId, @Param("amount") long amount);
}
