package com.qihang.erp.api.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhijian.common.annotation.Log;
import com.zhijian.common.core.controller.BaseController;
import com.zhijian.common.core.domain.AjaxResult;
import com.zhijian.common.enums.BusinessType;
import com.qihang.erp.api.domain.TaoOrder;
import com.qihang.erp.api.service.ITaoOrderService;
import com.zhijian.common.utils.poi.ExcelUtil;
import com.zhijian.common.core.page.TableDataInfo;

/**
 * 淘宝订单Controller
 * 
 * @author qihang
 * @date 2024-01-03
 */
@RestController
@RequestMapping("/tao/order")
public class TaoOrderController extends BaseController
{
    @Autowired
    private ITaoOrderService taoOrderService;

    /**
     * 查询淘宝订单列表
     */
    @PreAuthorize("@ss.hasPermi('tao:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaoOrder taoOrder)
    {
        startPage();
        List<TaoOrder> list = taoOrderService.selectTaoOrderList(taoOrder);
        return getDataTable(list);
    }

    /**
     * 导出淘宝订单列表
     */
    @PreAuthorize("@ss.hasPermi('tao:order:export')")
    @Log(title = "淘宝订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaoOrder taoOrder)
    {
        List<TaoOrder> list = taoOrderService.selectTaoOrderList(taoOrder);
        ExcelUtil<TaoOrder> util = new ExcelUtil<TaoOrder>(TaoOrder.class);
        util.exportExcel(response, list, "淘宝订单数据");
    }

    /**
     * 获取淘宝订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('tao:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taoOrderService.selectTaoOrderById(id));
    }

    /**
     * 新增淘宝订单
     */
    @PreAuthorize("@ss.hasPermi('tao:order:add')")
    @Log(title = "淘宝订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaoOrder taoOrder)
    {
        return toAjax(taoOrderService.insertTaoOrder(taoOrder));
    }

    /**
     * 修改淘宝订单
     */
    @PreAuthorize("@ss.hasPermi('tao:order:edit')")
    @Log(title = "淘宝订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaoOrder taoOrder)
    {
        return toAjax(taoOrderService.updateTaoOrder(taoOrder));
    }

    /**
     * 删除淘宝订单
     */
    @PreAuthorize("@ss.hasPermi('tao:order:remove')")
    @Log(title = "淘宝订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taoOrderService.deleteTaoOrderByIds(ids));
    }
}