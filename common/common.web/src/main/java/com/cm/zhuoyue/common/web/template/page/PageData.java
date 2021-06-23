package com.cm.zhuoyue.common.web.template.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author 陈萌
 * @date 2021/6/23 15:39
 */
@Data
@ApiModel(value = "分页响应参数", description = "通用")
public class PageData<T> {
    @ApiModelProperty("响应信息")
    private List<T> data;
    @ApiModelProperty("当前页数")
    private int currentPage;
    @ApiModelProperty("每页显示数")
    private int pageSize;
    @ApiModelProperty("总页数")
    private int totalPages;
    @ApiModelProperty("总数据数")
    private long totalRows;
    @ApiModelProperty("开始记录")
    private int startNum;
    @ApiModelProperty("结束记录")
    private int endNum;
    @ApiModelProperty("下一页")
    private int nextPage;
    @ApiModelProperty("上一页")
    private int previousPage;
    @ApiModelProperty("是否有下一页")
    private boolean hasNextPage;
    @ApiModelProperty("是否有前一页")
    private boolean hasPreviousPage;

    /**
     * 获取记录开始行码
     *
     * @param pageNum  当前页数，从1开始
     * @param pageSize 每页显示数
     * @return 记录开始行码
     */
    public static int getDataStartNum(int pageNum, int pageSize) {
        if (pageNum == 0) {
            pageNum = 1;
        }
        return (pageNum - 1) * pageSize + 1;
    }

    /**
     * 获取记录结束行码
     *
     * @param dataStartNum 记录开始行码
     * @param pageSize     每页显示数
     * @return 记录结束行码
     */
    public static int getDataEndNum(int dataStartNum, int pageSize) {
        return dataStartNum + pageSize - 1;
    }

    /**
     * 分页后的信息
     *
     * @param data      实体对象列表
     * @param pageNum   当前页码
     * @param pageSize  每页显示条数
     * @param totalRows 总条数
     */
    public PageData(List<T> data, int pageNum, int pageSize, long totalRows) {
        this.data = data;
        this.currentPage = pageNum;
        this.pageSize = pageSize;
        this.totalRows = totalRows;

        // 以下自动计算
        setTotalPages();
        setStartNum();
        setEndNum();
        setNextPage();
        setPreviousPage();
    }

    /**
     * 当没有分页的时候
     *
     * @param data 数据
     */
    public PageData(List<T> data) {
        this.data = data;
    }

    /**
     * 初始化分页对象
     *
     * @param <T> 返回值类型
     * @return 初始化分页对象
     */
    public static <T> PageData<T> newPageData() {
        return new PageData<>(new ArrayList<>());
    }

    // 该修饰符禁止修改
    private void setTotalPages() {
        final int pageSize = getPageSize();
        if (pageSize != 0) {
            this.totalPages = (int) Math.ceil((float) getTotalRows() / pageSize);
        } else {
            this.totalPages = 0;
        }
    }

    // 该修饰符禁止修改
    private void setStartNum() {
        this.startNum = getDataStartNum(getCurrentPage(), getPageSize());
    }

    // 该修饰符禁止修改
    private void setEndNum() {
        this.endNum = getDataEndNum(getStartNum(), getPageSize());
    }

    // 该修饰符禁止修改
    private void setNextPage() {
        int totalPage = getCurrentPage() + 1;
        if (totalPage > getTotalPages()) {
            totalPage = getTotalPages();
            setHasNextPage(false);
        } else {
            setHasNextPage(true);
        }
        this.nextPage = totalPage;
    }

    // 该修饰符禁止修改
    private void setPreviousPage() {
        int pageNo = getCurrentPage() - 1;
        if (pageNo < 1) {
            pageNo = 1;
            setHasPreviousPage(false);
        } else {
            setHasPreviousPage(true);
        }
        this.previousPage = pageNo;
    }

    // 该修饰符禁止修改
    protected void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    // 该修饰符禁止修改
    private void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<T> getData() {
        return data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public int getStartNum() {
        return startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PageData.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("currentPage=" + currentPage)
                .add("totalPages=" + totalPages)
                .add("pageSize=" + pageSize)
                .add("totalRows=" + totalRows)
                .add("startNum=" + startNum)
                .add("endNum=" + endNum)
                .add("nextPage=" + nextPage)
                .add("previousPage=" + previousPage)
                .add("hasNextPage=" + hasNextPage)
                .add("hasPreviousPage=" + hasPreviousPage)
                .toString();
    }
}
