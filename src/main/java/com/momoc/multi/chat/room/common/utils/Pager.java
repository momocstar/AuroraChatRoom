package com.momoc.multi.chat.room.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author momoc
 */
@NoArgsConstructor
@ToString
public class Pager<T> extends Page<T> {
    public Pager(HttpServletRequest request, Integer pageSize) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (AssertUtil.isEmpty(parameterMap)){
            parameterMap = null;
        }
        if (request.getParameterMap().containsKey("page")){
            this.current = Integer.parseInt(request.getParameter("page"));
        }
        if (pageSize!=0){
            this.size = pageSize;
        }
    }
//    private static final long serialVersionUID = 8545996863226528798L;
////    @JsonProperty("pageData")
//    protected List<T> records;
//    protected long total;
////    @JsonProperty("size")
//    protected long size=20;
////    @JsonProperty("page")
//    protected long page=1;
//
//
//    public Pager(HttpServletRequest request, int pageSize){
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (AssertUtil.isEmpty(parameterMap)){
//            parameterMap = null;
//        }
//        if (request.getParameterMap().containsKey("page")){
//            this.page = Integer.parseInt(request.getParameter("page"));
//        }
//        if (pageSize!=0){
//            this.size = pageSize;
//        }
//
//
//    }
//    @Override
//    public List<OrderItem> orders() {
//        return null;
//    }
////    @JsonIgnore
//    @Override
//    public List<T> getRecords() {
//        return this.records;
//    }
//
//    @Override
//    public IPage<T> setRecords(List<T> records) {
//        this.records = records;
//        return this;
//    }
//
//    @Override
//    public long getTotal() {
//        return total;
//    }
//
//    @Override
//    public IPage<T> setTotal(long total) {
//        this.total = total;
//        return this;
//    }
//
//    @Override
//    public long getSize() {
//        return this.size;
//    }
//
//    @Override
//    public IPage<T> setSize(long size) {
//        this.size = size;
//        return this;
//    }
////    @JsonIgnore
//    @Override
//    public long getCurrent() {
//
//        return this.page;
//    }
//
//    @Override
//    public IPage<T> setCurrent(long page) {
//        this.page = page;
//        return this;
//    }
}
