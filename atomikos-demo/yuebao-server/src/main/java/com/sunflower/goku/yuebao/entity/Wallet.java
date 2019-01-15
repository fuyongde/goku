package com.sunflower.goku.yuebao.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author fuyongde
 */
public class Wallet extends BaseEntity {

    /**用户id**/
    private long userId;
    /**余额**/
    private long balance;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
