package com.goku.dubbo.commons.utils;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.goku.dubbo.commons.consts.PageConsts.*;

/**
 * @author fuyongde
 * @desc 内存分页的工具类
 * @date 2017/11/15 13:52
 */
public class PaginationUtils {

    /**
     * 对原始数据进行拆分
     *
     * @param list     原始数据
     * @param pageSize 页面大小
     * @param <T>
     *
     * @return
     */
    private static <T> List<List<T>> split(@NonNull List<T> list, int pageSize) {

        pageSize = pageSize < PAGE_SIZE_MIN ? PAGE_SIZE_DEFAULT : pageSize;

        //获取总记录数
        int totalSize = list.size();

        //获取总页数
        int totalPage = (totalSize + pageSize - 1) / pageSize;

        //创建一个为总页数大小的list
        List<List<T>> result = new ArrayList<>(totalPage);

        //将数据分页之后放入结果集
        for (int i = 0, next = i * pageSize; i < totalPage; i++) {
            result.add(new ArrayList<>(list.subList(next, (i < totalPage - 1) ? (next = next + pageSize) : totalSize)));
        }

        return result;
    }

    /**
     * 内存分页取数据
     *
     * @param list      原始数据
     * @param pageIndex 页码
     * @param pageSize  页面大小
     * @param <T>
     *
     * @return
     */
    public static <T> List<T> getByPage(List<T> list, int pageIndex, int pageSize) {
        pageIndex = pageIndex < PAGE_INDEX_MIN ? PAGE_INDEX_DEFAULT : pageIndex;
        return split(list, pageSize).get(pageIndex);
    }
}
