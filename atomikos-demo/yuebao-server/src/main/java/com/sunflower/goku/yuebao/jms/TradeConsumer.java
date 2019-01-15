package com.sunflower.goku.yuebao.jms;

import com.sunflower.goku.common.api.jms.Queue;
import com.sunflower.goku.common.api.jms.TradeMessage;
import com.sunflower.goku.yuebao.service.YuebaoService;
import com.sunflower.goku.yuebao.service.dto.YuebaoTradeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 交易的消费者
 */
@Component
public class TradeConsumer {

    private static Logger logger = LoggerFactory.getLogger(TradeConsumer.class);

    @Autowired
    private YuebaoService yuebaoService;

    @JmsListener(destination = Queue.ZHIFUBAO_YUEBAO_TRADE)
    public void tradeIn(TradeMessage tradeMessage) {
        logger.info("tradeMessage:{}", tradeMessage);
        long userId = tradeMessage.getUserId();
        long amount = tradeMessage.getAmount();
        yuebaoService.tradeIn(new YuebaoTradeDTO(userId, amount));
        logger.info("trade in end");
    }
}
