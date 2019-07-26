package com.spring.demo.permission;

public class PermissionConstants {

    // schedule的token字段
    public static String SCHEDULE = "schedule";

    // 操作
    public static String FORMAT = "%s:%s";

    // 创建
    public static String CREATE = "create";

    // 编辑
    public static String EDIT = "edit";

    // 删除
    public static String DELETE = "delete";

    // 上传
    public static String UPLOAD = "upload";

    // 检查
    public static String CHECK = "check";

    // 生成图片
    public static String IMAGE = "image";

    // 索引
    public static String INDEX = "index";

    // 申请
    public static String REQUEST = "request";

    // 审核
    public static String APPROVE = "approve";

    // 导出
    public static String EXPORT = "export";

    // 制作
    public static String MAKE = "make";

    // 上下架
    public static String ENABLE = "enable";

    // 修改状态
    public static String CHANGE = "change";

    // 发送
    public static String SEND = "send";

    // 注册
    public static String REGISTER = "register";

    // 代理
    public static String PROXY = "proxy";

    // 管理
    public static String MANAGE = "manage";

    // 分析
    public static String REPORT = "report";

    // 关联
    public static String CONNECT = "connect";

    // 汇总
    public static String SUM = "sum";

    // 第一食采权限设计
    //
    // 商品管理
    public static String ITEM = "item";

    // 编辑商品
    public static String editItem = f(EDIT, ITEM);

    // 删除商品
    public static String deleteItem = f(DELETE, ITEM);

    // 上下架商品
    public static String enableItem = f(ENABLE, ITEM);

    // 上传商品
    public static String uploadItem = f(UPLOAD, ITEM);

    // 检查商品
    public static String checkItem = f(CHECK, ITEM);

    // 生成图片
    public static String imageItem = f(IMAGE, ITEM);

    // 索引商品
    public static String indexItem = f(INDEX, ITEM);

    // 导出商品
    public static String exportItem = f(EXPORT, ITEM);

    // 关联商品
    public static String connectItem = f(CONNECT, ITEM);

    // 新品
    public static String REQUEST_ITEM = "requestItem";

    // 申请新品
    public static String requestNewItem = f(REQUEST, REQUEST_ITEM);

    // 审核新品
    public static String approveNewItem = f(APPROVE, REQUEST_ITEM);

    // 编辑新品
    public static String editRequestItem = f(EDIT, REQUEST_ITEM);

    // 删除新品
    public static String deleteRequestItem = f(DELETE, REQUEST_ITEM);

    // 调价单
    public static String ADJUSTMENT = "adjustment";

    // 申请调价单
    public static String requestAdjustment = f(REQUEST, ADJUSTMENT);

    // 审核调价单
    public static String approveAdjustment = f(APPROVE, ADJUSTMENT);

    // 上传调价单
    public static String uploadAdjustment = f(UPLOAD, ADJUSTMENT);

    // 编辑调价单
    public static String editAdjustment = f(EDIT, ADJUSTMENT);

    // 删除调价单
    public static String deleteAdjustment = f(DELETE, ADJUSTMENT);

    // 报价单
    public static String PRICE = "price";

    // 导出报价单
    public static String exportPrice = f(EXPORT, ADJUSTMENT);

    // 商品价格
    public static String ITEM_PRICE = "itemPrice";

    // 编辑商品价格
    public static String editItemPrice = f(EDIT, ITEM_PRICE);

    // 删除商品价格
    public static String deleteItemPrice = f(DELETE, ITEM_PRICE);

    // 设置商品价格
    public static String changeItemPrice = f(CHANGE, ITEM_PRICE);

    // 商品分类
    public static String CATALOG = "catalog";

    // 编辑商品分类
    public static String editCatalog = f(EDIT, CATALOG);

    // 删除商品分类
    public static String deleteCatalog = f(DELETE, CATALOG);

    // 商品品牌
    public static String BRAND = "brand";

    // 编辑商品品牌
    public static String editBrand = f(EDIT, BRAND);

    // 删除商品品牌
    public static String deleteBrand = f(DELETE, BRAND);

    // 商品标记
    public static String ITEM_MARK = "itemMark";

    // 编辑商品标记
    public static String editItemMark = f(EDIT, ITEM_MARK);

    // 删除商品标记
    public static String deleteItemMark = f(DELETE, ITEM_MARK);

    // 商品标签
    public static String TAG = "tag";

    // 编辑商品标签
    public static String editTag = f(EDIT, TAG);

    // 删除商品标签
    public static String deleteTag = f(DELETE, TAG);

    // 订单管理
    //
    // 订单
    public static String ORDER = "order";

    // 编辑订单
    public static String editOrder = f(EDIT, ORDER);

    // 删除订单
    public static String deleteOrder = f(DELETE, ORDER);

    // 修改状态
    public static String changeOrder = f(CHANGE, ORDER);

    // 导出订单
    public static String exportOrder = f(EXPORT, ORDER);

    // 分析订单
    public static String reportOrder = f(REPORT, ORDER);

    // 汇总订单
    public static String sumOrder = f(SUM, ORDER);

    public static String SMS = "sms";

    // 发短信
    public static String sendSMS = f(SEND, SMS);

    public static String EMAIL = "email";

    // 发邮件
    public static String sendEmail = f(SEND, EMAIL);

    // 送货单
    public static String ORDER_SEND = "orderSend";

    // 制作送货单
    public static String makeOrderSend = f(MAKE, ORDER_SEND);

    // 修改送货单
    public static String editOrderSend = f(EDIT, ORDER_SEND);

    // 删除收货单
    public static String deleteOrderSend = f(DELETE, ORDER_SEND);

    // 导出收货单
    public static String exportOrderSend = f(EXPORT, ORDER_SEND);

    // 收货单
    public static String ORDER_RECEIVE = "orderReceive";

    // 制作收货单
    public static String makeOrderReceive = f(MAKE, ORDER_RECEIVE);

    // 修改收货单
    public static String editOrderReceive = f(EDIT, ORDER_RECEIVE);

    // 删除收货单
    public static String deleteOrderReceive = f(DELETE, ORDER_RECEIVE);

    // 导出收货单
    public static String exportOrderReceive = f(EXPORT, ORDER_RECEIVE);

    // 报损单
    public static String ORDER_LOST = "orderLost";

    // 制作报损单
    public static String makeOrderLost = f(MAKE, ORDER_LOST);

    // 修改报损单
    public static String editOrderLost = f(EDIT, ORDER_LOST);

    // 删除报损单
    public static String deleteOrderLost = f(DELETE, ORDER_LOST);

    // 导出报损单
    public static String exportOrderLost = f(EXPORT, ORDER_LOST);

    // 退货单
    public static String ORDER_RETURN = "orderReturn";

    // 制作退货单
    public static String makeOrderReturn = f(MAKE, ORDER_RETURN);

    // 修改退货单
    public static String editOrderReturn = f(EDIT, ORDER_RETURN);

    // 删除退货单
    public static String deleteOrderReturn = f(DELETE, ORDER_RETURN);

    // 导出退货单
    public static String exportOrderReturn = f(EXPORT, ORDER_RETURN);

    // 人员管理
    //
    // 客户
    public static String USER = "user";

    // 注册客户
    public static String registerUser = f(REGISTER, USER);

    // 启停用客户
    public static String enableUser = f(ENABLE, USER);

    // 代客户下单
    public static String proxyUser = f(PROXY, USER);

    // 编辑客户
    public static String editUser = f(EDIT, USER);

    // 删除客户
    public static String deleteUser = f(DELETE, USER);

    // 意向客户
    public static String CUSTOMER = "customer";

    // 编辑意向客户
    public static String editCustomer = f(EDIT, CUSTOMER);

    // 删除意向客户
    public static String deleteCustomer = f(DELETE, CUSTOMER);

    // 新品建议
    public static String NEW_ITEM = "newItem";

    // 编辑新品建议
    public static String editNewItem = f(EDIT, NEW_ITEM);

    // 删除新品建议
    public static String deleteNewItem = f(DELETE, NEW_ITEM);

    // 投诉建议
    public static String QUESTION = "question";

    // 编辑投诉建议
    public static String editQuestion = f(EDIT, QUESTION);

    // 删除投诉建议
    public static String deleteQuestion = f(DELETE, QUESTION);

    // 客户搜索
    public static String KEYWORD = "keyword";

    // 编辑客户搜索
    public static String editKeyword = f(EDIT, KEYWORD);

    // 删除客户搜索
    public static String deleteKeyword = f(DELETE, KEYWORD);

    // 客户消息
    public static String MAIL = "mail";

    // 编辑客户消息
    public static String editMail = f(EDIT, MAIL);

    // 删除客户消息
    public static String deleteMail = f(DELETE, MAIL);

    // 客户收藏
    public static String FAVORITE = "favorite";

    // 编辑客户收藏
    public static String editFavorite = f(EDIT, FAVORITE);

    // 删除客户收藏
    public static String deleteFavorite = f(DELETE, FAVORITE);

    // 客户地址
    public static String ADDRESS = "address";

    // 编辑客户地址
    public static String editAddress = f(EDIT, ADDRESS);

    // 删除客户地址
    public static String deleteAddress = f(ADDRESS, DELETE);

    // 业务员
    public static String SALEMAN = "saleman";

    // 添加业务员
    public static String createSaleman = f(CREATE, SALEMAN);

    // 编辑业务员
    public static String editSaleman = f(EDIT, SALEMAN);

    // 删除业务员
    public static String deleteSaleman = f(DELETE, SALEMAN);

    // 启停用业务员
    public static String enableSaleman = f(ENABLE, SALEMAN);

    // 供应商
    public static String SUPPLIER = "supplier";

    // 添加供应商
    public static String createSupplier = f(CREATE, SUPPLIER);

    // 编辑供应商
    public static String editSupplier = f(EDIT, SUPPLIER);

    // 删除供应商
    public static String deleteSupplier = f(DELETE, SUPPLIER);

    // 启停用供应商
    public static String enableSupplier = f(ENABLE, SUPPLIER);

    // 送货员
    public static String EXPRESS = "express";

    // 添加送货员
    public static String createExpress = f(CREATE, EXPRESS);

    // 编辑送货员
    public static String editExpress = f(EDIT, EXPRESS);

    // 删除送货员
    public static String deleteExpress = f(DELETE, EXPRESS);

    // 启停用送货员
    public static String enableExpress = f(ENABLE, EXPRESS);

    // 网站管理
    public static String INFO = "info";

    // 信息管理
    public static String manageInfo = f(MANAGE, INFO);

    public static String SITE = "site";

    // 页面管理
    public static String manageSite = f(MANAGE, SITE);

    // 报表分析
    //
    // 采购完成率
    public static String SEND_REPORT = "sendReport";

    // 分析报表
    public static String reportSendReport = f(REPORT, SEND_REPORT);

    // 导出报表
    public static String exportSendReport = f(EXPORT, SEND_REPORT);

    // 配送到货率
    public static String RECEIVE_REPORT = "receiveReport";

    // 分析报表
    public static String reportReceiveReport = f(REPORT, RECEIVE_REPORT);

    // 导出报表
    public static String exportReceiveReport = f(EXPORT, RECEIVE_REPORT);

    // 客户毛利
    //
    public static String USER_PROFIT_REPORT = "userProfitReport";

    // 分析报表
    public static String reportUserProfitReport = f(REPORT, USER_PROFIT_REPORT);

    // 导出报表
    public static String exportUserProfitReport = f(EXPORT, USER_PROFIT_REPORT);

    //
    // 业务员毛利
    //
    public static String SALEMAN_PROFIT_REPORT = "salemanProfitReport";

    // 分析报表
    public static String reportSalemanProfitReport = f(REPORT, SALEMAN_PROFIT_REPORT);

    // 导出报表
    public static String exportSalemanProfitReport = f(EXPORT, SALEMAN_PROFIT_REPORT);

    //
    // 类别毛利
    public static String CATALOG_PROFIT_REPORT = "catalogProfitReport";

    // 分析报表
    public static String reportCatalogProfitReport = f(REPORT, CATALOG_PROFIT_REPORT);

    // 导出报表
    public static String exportCatalogProfitReport = f(EXPORT, CATALOG_PROFIT_REPORT);

    //
    // 商品毛利
    public static String ITEM_PROFIT_REPORT = "itemProfitReport";

    // 分析报表
    public static String reportItemProfitReport = f(REPORT, ITEM_PROFIT_REPORT);

    // 导出报表
    public static String exportItemProfitReport = f(EXPORT, ITEM_PROFIT_REPORT);

    //
    // 商品报损
    public static String ITEM_LOST_REPORT = "itemLostReport";

    // 分析报表
    public static String reportItemLostReport = f(REPORT, ITEM_LOST_REPORT);

    // 导出报表
    public static String exportItemLostReport = f(EXPORT, ITEM_LOST_REPORT);

    //
    // 供应商退货
    public static String ITEM_RETURN_REPORT = "itemReturnReport";

    // 分析报表
    public static String reportItemReturnReport = f(REPORT, ITEM_RETURN_REPORT);

    // 导出报表
    public static String exportItemReturnReport = f(EXPORT, ITEM_RETURN_REPORT);

    //
    // 采购商品
    public static String SUPPLIER_REPORT = "supplierReport";

    // 分析报表
    public static String reportSupplierReport = f(REPORT, SUPPLIER_REPORT);

    // 导出报表
    public static String exportSupplierReport = f(EXPORT, SUPPLIER_REPORT);

    //
    // 商品进销
    public static String SALE_REPORT = "saleReport";

    // 分析报表
    public static String reportSaleReport = f(REPORT, SALE_REPORT);

    // 导出报表
    public static String exportSaleReport = f(EXPORT, SALE_REPORT);

    //
    // 全场活动管理
    public static String COUPON = "coupon";

    // 编辑全场活动
    public static String editCoupon = f(EDIT, COUPON);

    // 删除全场活动
    public static String deleteCoupon = f(DELETE, COUPON);

    // 优惠券管理
    public static String COUPON_CODE = "couponCode";

    // 编辑优惠券
    public static String editCouponCode = f(EDIT, COUPON_CODE);

    // 删除优惠券
    public static String deleteCouponCode = f(DELETE, COUPON_CODE);

    // 导出优惠券
    public static String exportCouponCode = f(EXPORT, COUPON_CODE);

    // 发送优惠券
    public static String sendCouponCode = f(SEND, COUPON_CODE);

    //
    // 超市管理
    public static String STORE = "store";

    // 编辑超市
    public static String editStore = f(EDIT, STORE);

    // 删除超市
    public static String deleteStore = f(DELETE, STORE);

    //
    // 小区管理
    public static String DISTRICT = "district";

    // 编辑小区
    public static String editDistrict = f(EDIT, DISTRICT);

    // 删除小区
    public static String deleteDistrict = f(DELETE, DISTRICT);

    //
    // 行政区管理
    public static String AREA = "area";

    // 编辑行政区
    public static String editArea = f(EDIT, AREA);

    // 删除行政区
    public static String deleteArea = f(DELETE, AREA);

    private static String f(final String action, final String object) {
        return String.format(FORMAT, action, object);
    }
}
