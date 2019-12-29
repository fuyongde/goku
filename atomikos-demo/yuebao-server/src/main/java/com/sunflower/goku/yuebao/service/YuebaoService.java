package com.sunflower.goku.yuebao.service;

import com.sunflower.goku.yuebao.repository.WalletMapper;
import com.sunflower.goku.yuebao.service.dto.YuebaoTradeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 余额宝服务
 */
@Service
public class YuebaoService {

    private static Logger logger = LoggerFactory.getLogger(YuebaoService.class);

    @Resource
    private WalletMapper walletMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void tradeIn(YuebaoTradeDTO yuebaoTradeDTO) {
        long userId = yuebaoTradeDTO.getUserId();
        long amount = yuebaoTradeDTO.getAmount();
        walletMapper.tradeIn(userId, amount);
        logger.info("tradeIn:{}", yuebaoTradeDTO);
    }
}
