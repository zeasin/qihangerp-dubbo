package com.qihang.erp.api.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.qihang.erp.api.domain.DouOrderItem;
import com.qihang.erp.api.domain.TaoOrderAddress;
import com.qihang.erp.api.mapper.TaoOrderAddressMapper;
import com.zhijian.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.zhijian.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.qihang.erp.api.domain.TaoOrderItem;
import com.qihang.erp.api.mapper.TaoOrderMapper;
import com.qihang.erp.api.domain.TaoOrder;
import com.qihang.erp.api.service.ITaoOrderService;

/**
 * 淘宝订单Service业务层处理
 * 
 * @author qihang
 * @date 2024-01-03
 */
@Service
public class TaoOrderServiceImpl implements ITaoOrderService 
{
    @Autowired
    private TaoOrderMapper taoOrderMapper;
    @Autowired
    private TaoOrderAddressMapper addressMapper;

    /**
     * 查询淘宝订单
     * 
     * @param id 淘宝订单主键
     * @return 淘宝订单
     */
    @Override
    public TaoOrder selectTaoOrderById(Long id)
    {
        return taoOrderMapper.selectTaoOrderById(id);
    }

    /**
     * 查询淘宝订单列表
     * 
     * @param taoOrder 淘宝订单
     * @return 淘宝订单
     */
    @Override
    public List<TaoOrder> selectTaoOrderList(TaoOrder taoOrder)
    {
        var orderList = taoOrderMapper.selectTaoOrderList(taoOrder);
        for (var o:orderList) {
            List<TaoOrderItem> items = taoOrderMapper.selectOrderItemByOrderId(o.getId());
            o.setTaoOrderItemList(items);
        }
        return orderList;
    }

    /**
     * 新增淘宝订单
     * 
     * @param taoOrder 淘宝订单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTaoOrder(TaoOrder taoOrder)
    {
        if(StringUtils.isNull(taoOrder.getShippingFee())) taoOrder.setShippingFee(BigDecimal.ZERO);
        taoOrder.setTotalAmount(taoOrder.getTotalAmount().add(taoOrder.getShippingFee()));
        taoOrder.setPayAmount(taoOrder.getTotalAmount());
        taoOrder.setStatus(2L);
        taoOrder.setStatusStr("等待发货");
        taoOrder.setRefundStatus("0");
        taoOrder.setAuditStatus(0L);
        taoOrder.setSendStatus(0L);
        taoOrder.setIsComment(0);
        taoOrder.setCreateTime(DateUtils.getNowDate());
        int rows = taoOrderMapper.insertTaoOrder(taoOrder);
        insertTaoOrderItem(taoOrder);
        // 添加地址
        TaoOrderAddress address = new TaoOrderAddress();
        address.setOrderId(taoOrder.getId());
        address.setContactPerson(taoOrder.getReceiver());
        address.setMobile(taoOrder.getPhone());
        address.setProvince(taoOrder.getProvince());
        address.setCity(taoOrder.getCity());
        address.setArea(taoOrder.getTown());
        address.setAddress(taoOrder.getAddress());
        addressMapper.insertTaoOrderAddress(address);
        return rows;
    }

    /**
     * 修改淘宝订单
     * 
     * @param taoOrder 淘宝订单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTaoOrder(TaoOrder taoOrder)
    {
        taoOrder.setUpdateTime(DateUtils.getNowDate());
        taoOrderMapper.deleteTaoOrderItemByOrderId(taoOrder.getId());
        insertTaoOrderItem(taoOrder);
        return taoOrderMapper.updateTaoOrder(taoOrder);
    }

    /**
     * 批量删除淘宝订单
     * 
     * @param ids 需要删除的淘宝订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTaoOrderByIds(Long[] ids)
    {
        taoOrderMapper.deleteTaoOrderItemByOrderIds(ids);
        return taoOrderMapper.deleteTaoOrderByIds(ids);
    }

    /**
     * 删除淘宝订单信息
     * 
     * @param id 淘宝订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTaoOrderById(Long id)
    {
        taoOrderMapper.deleteTaoOrderItemByOrderId(id);
        return taoOrderMapper.deleteTaoOrderById(id);
    }

    /**
     * 新增淘宝订单明细信息
     * 
     * @param taoOrder 淘宝订单对象
     */
    public void insertTaoOrderItem(TaoOrder taoOrder)
    {
        List<TaoOrderItem> taoOrderItemList = taoOrder.getTaoOrderItemList();
        Long id = taoOrder.getId();
        if (StringUtils.isNotNull(taoOrderItemList))
        {
            List<TaoOrderItem> list = new ArrayList<TaoOrderItem>();
            for (TaoOrderItem taoOrderItem : taoOrderItemList)
            {
                taoOrderItem.setSubItemId(taoOrder.getId());
                taoOrderItem.setOrderId(id);
                taoOrderItem.setDiscountFee(BigDecimal.ZERO);
                taoOrderItem.setAdjustFee(BigDecimal.ZERO);
                taoOrderItem.setStatus("2");
                taoOrderItem.setRefundStatus(0L);
                taoOrderItem.setLogisticsStatus(0L);
                taoOrderItem.setNewSpecId(0L);
                taoOrderItem.setAfterSaleState(0L);
                taoOrderItem.setIsGift(0);
                taoOrderItem.setIsSwap(0);
                list.add(taoOrderItem);
            }
            if (list.size() > 0)
            {
                taoOrderMapper.batchTaoOrderItem(list);
            }
        }
    }
}
