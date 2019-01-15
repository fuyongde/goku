package com.sunflower.goku.common.api.jms;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 交易信息，用于MQ队列
 */
public class TradeMessage implements Serializable {
    private long userId;
    private long amount;

    public TradeMessage(long userId, long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
