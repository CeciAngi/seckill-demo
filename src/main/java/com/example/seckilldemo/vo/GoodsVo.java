package com.example.seckilldemo.vo;

import com.example.seckilldemo.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：qhh
 * @date ：Created in 2022/4/7 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private int stockCount;
    private Date startDate;
    private Date endDate;
}
