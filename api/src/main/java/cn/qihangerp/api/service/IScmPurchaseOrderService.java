package cn.qihangerp.api.service;

import java.util.List;
import cn.qihangerp.api.domain.ScmPurchaseOrder;
import cn.qihangerp.api.domain.bo.PurchaseOrderAddBo;
import cn.qihangerp.api.domain.bo.PurchaseOrderOptionBo;

/**
 * 采购订单Service接口
 * 
 * @author qihang
 * @date 2023-12-29
 */
public interface IScmPurchaseOrderService 
{
    /**
     * 查询采购订单
     * 
     * @param id 采购订单主键
     * @return 采购订单
     */
    public ScmPurchaseOrder selectScmPurchaseOrderById(Long id);

    /**
     * 查询采购订单列表
     * 
     * @param scmPurchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<ScmPurchaseOrder> selectScmPurchaseOrderList(ScmPurchaseOrder scmPurchaseOrder);

    /**
     * 新增采购订单
     * 
     * @param scmPurchaseOrder 采购订单
     * @return 结果
     */
    public int insertScmPurchaseOrder(PurchaseOrderAddBo addBo);

    /**
     * 修改采购订单
     * 
     * @param scmPurchaseOrder 采购订单
     * @return 结果
     */
    public int updateScmPurchaseOrder(PurchaseOrderOptionBo scmPurchaseOrder);

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的采购订单主键集合
     * @return 结果
     */
    public int deleteScmPurchaseOrderByIds(Long[] ids);

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单主键
     * @return 结果
     */
    public int deleteScmPurchaseOrderById(Long id);
}
