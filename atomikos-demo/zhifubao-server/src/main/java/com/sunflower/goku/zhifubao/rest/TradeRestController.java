package com.sunflower.goku.zhifubao.rest;

import com.sunflower.goku.zhifubao.service.ZhifubaoService;
import com.sunflower.goku.zhifubao.service.dto.ZhifubaoTradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 交易
 */
@RestController
@RequestMapping("/api/trade")
public class TradeRestController {

    @Autowired
    private ZhifubaoService zhifubaoService;

    @PostMapping()
    void trade(@RequestBody ZhifubaoTradeDTO zhifubaoTradeDTO) {
        zhifubaoService.tradeOut(zhifubaoTradeDTO);
    }
}
