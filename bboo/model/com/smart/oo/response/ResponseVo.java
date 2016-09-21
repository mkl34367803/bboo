/*
 * 文件名：ResponseVo.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ResponseVo.java
 * 修改人：dangge
 * 修改时间：2016年1月12日
 * 修改内容：新增
 */
package com.smart.oo.response;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author dangge
 */
public class ResponseVo {
    /**
     * 添加字段注释.
     */
    private String requestResult;

    /**
     * 添加字段注释.
     */
    private String responseBody;

    /**
     * 设置requestResul.
     * 
     * @return 返回requestResul
     */
    public String getRequestResult() {
        return requestResult;
    }

    /**
     * 获取requestResul.
     * 
     * @param requestResul
     *            要设置的requestResul
     */
    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult;
    }

    /**
     * 设置responseBody.
     * 
     * @return 返回responseBody
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * 获取responseBody.
     * 
     * @param responseBody
     *            要设置的responseBody
     */
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

}
