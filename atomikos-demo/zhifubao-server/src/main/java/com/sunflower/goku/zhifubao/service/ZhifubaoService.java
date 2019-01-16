package com.sunflower.goku.zhifubao.service;

import com.sunflower.goku.common.api.jms.TradeMessage;
import com.sunflower.goku.zhifubao.entity.Wallet;
import com.sunflower.goku.zhifubao.jms.TradeProducer;
import com.sunflower.goku.zhifubao.repository.WalletMapper;
import com.sunflower.goku.zhifubao.service.dto.ZhifubaoTradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 支付包服务
 */
@Service
public class ZhifubaoService {

    @Resource
    private WalletMapper walletMapper;

    @Autowired
    private TradeProducer tradeProducer;

    @Transactional(rollbackFor = Exception.class)
    public void tradeOut(ZhifubaoTradeDTO zhifubaoTradeDTO) {
        long userId = zhifubaoTradeDTO.getUserId();
        long amount = zhifubaoTradeDTO.getAmount();
        Wallet wallet = walletMapper.getByUserId4Update(userId);
        long balance = wallet.getBalance();
        balance -= amount;
        tradeProducer.tradeOut(new TradeMessage(userId, amount));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        walletMapper.updateBalance(userId, balance);
    }
}
