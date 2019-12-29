package com.sunflower.goku.zhifubao.service;

import com.sunflower.goku.common.api.jms.TradeMessage;
import com.sunflower.goku.zhifubao.jms.TradeProducer;
import com.sunflower.goku.zhifubao.repository.WalletMapper;
import com.sunflower.goku.zhifubao.service.dto.ZhifubaoTradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 支付宝服务
 */
@Service
public class ZhifubaoService {

    @Resource
    private WalletMapper walletMapper;

    @Autowired
    private TradeProducer tradeProducer;

    @Transactional(rollbackOn = RuntimeException.class)
    public void tradeOut(ZhifubaoTradeDTO zhifubaoTradeDTO) {
        long userId = zhifubaoTradeDTO.getUserId();
        long amount = zhifubaoTradeDTO.getAmount();
        tradeProducer.tradeOut(new TradeMessage(userId, amount));
        walletMapper.tradeOut(userId, amount);
    }
}
