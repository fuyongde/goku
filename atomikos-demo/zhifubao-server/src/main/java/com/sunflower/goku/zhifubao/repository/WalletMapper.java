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
     * @param userId 用户Id
     * @return
     */
    Wallet getByUserId(@Param("userId") long userId);

    /**
     * 查询用户的钱包信息
     * @param userId 用户id
     * @return
     */
    Wallet getByUserId4Update(@Param("userId") long userId);

    /**
     * 更新用户余额
     * @param userId 用户
     * @param balance 余额
     * @return
     */
    int updateBalance(@Param("userId") long userId, @Param("balance") long balance);
}
