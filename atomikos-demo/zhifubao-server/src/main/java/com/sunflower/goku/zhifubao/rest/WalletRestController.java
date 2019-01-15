package com.sunflower.goku.zhifubao.rest;

import com.sunflower.goku.zhifubao.entity.Wallet;
import com.sunflower.goku.zhifubao.repository.WalletMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @date 2019/1/15
 * @desc 钱包信息
 */
@RestController
@RequestMapping("/api/wallet")
public class WalletRestController {

    @Resource
    private WalletMapper walletMapper;

    @GetMapping("/{userId}")
    Wallet wallet(@PathVariable long userId) {
        return walletMapper.getByUserId(userId);
    }
}
