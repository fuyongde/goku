package com.sunflower.goku.zhifubao.jms;

import com.sunflower.goku.common.api.jms.Queue;
import com.sunflower.goku.common.api.jms.TradeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 交易的异步通知
 */
@Component
public class TradeProducer {

    private static Logger logger = LoggerFactory.getLogger(TradeProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void tradeOut(TradeMessage tradeMessage) {
        logger.info("tradeMessage:{}", tradeMessage);
        jmsTemplate.convertAndSend(Queue.ZHIFUBAO_YUEBAO_TRADE, tradeMessage);
        logger.info("send mq end");
        throw new RuntimeException("a");
    }
}
