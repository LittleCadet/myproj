package com.myproj.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysCompanyEntity
{
    private Integer id;
    /**公司名称*/
    private String companyName;
    /**公司类型*/
    private Byte companyType;
    /**公司状态*/
    private Byte status;
    /**上级id*/
    private Integer parentId;
    /**合作开始时间*/
    private Date cooperationStart;
    /**公司层级*/
    private Byte companyLevel;
    /**合作结束时间*/
    private Date cooperationEnd;
    /**公司在当前层级下的顺序*/
    private Integer seq;
    /**公司法人姓名*/
    private String corpn;
    /**联系电话*/
    private String telephone;
    /**联系地址*/
    private String address;
    /**营业执照*/
    private String businessLicence;
    /**初始管理员id*/
    private Integer adminId;
    /**所属业务员id*/
    private Integer salesmanId;
    /**客服id*/
    private Integer customerServiceId;
    /**微信比例*/
    private BigDecimal wxRate;
    /**支付宝比例*/
    private BigDecimal alipayRate;
    /**拉卡拉比例*/
    private BigDecimal lakalaRate;
    /**花呗比例*/
    private BigDecimal huaBeiRate;
    /**富友比例*/
    private BigDecimal fuYouRate;
    /**随行付比例*/
    private BigDecimal sxfRate;
    /**设置企业微信的佣金比例 */
    private BigDecimal entWxRate;
    /**支付宝小程序*/
    private Byte aliMiniProgramType;
    /**支付宝小程序数目*/
    private Integer aliMiniProgram;
    /**支付宝刷脸付*/
    private Byte alipayFacepayType;
    /**支付宝刷脸付数目*/
    private Integer alipayFacepay;
    /**微信小程序*/
    private Byte wxMiniProgramType;
    /**微信小程序数目*/
    private Integer wxMiniProgram;
    /**微信刷脸付类型*/
    private Byte wxpayFacepayType;
    /**微信刷脸付数目*/
    private Integer wxpayFacepay;
    /**富友间联*/
    private Byte fuYouPayType;
    /**富友间联数目*/
    private Integer fuYouPay;
    /**企业微信*/
    private Byte entWxPayType;
    /**企业微信数目*/
    private Integer entWxPay;
    /**备注*/
    private String remark;
    /**驳回原因*/
    private String rejectedReason;
    /**操作人*/
    private String operator;
    /**操作时间*/
    private Date operateTime;
    /**操作ip*/
    private String operateIp;
    /**微信账号*/
    private String subMchId;
    /**商户logo*/
    private String logo;
    /**创建时间*/
    private Date createTime;
    /**判定是否是oem运营商，1：是，0：否 */
    private Byte isOem;
    /**许可证*/
    private String agreementLicence;
    /** 轮播图 **/
    private String slideShow;
    /** 轮播图开关：1：是，0：否 **/
    private Integer slideShowSwitch;
    /**判定是否是梦想合伙人，1：是，0：否 */
    private Byte isBrand;

    /**直联微信的启用状态，0:都不启用， 1：启用*/
    private Byte directWxFlag;

    /**直联支付宝的启用状态，0:都不启用， 1：启用*/
    private Byte directAliFlag;

    /**间联富友随行付的启用状态，0:都不启用，1：随行付启用，2：富友启用*/
    private Byte redirectFlag;
}