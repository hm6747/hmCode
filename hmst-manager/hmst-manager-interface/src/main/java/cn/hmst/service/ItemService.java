package cn.hmst.service;

import cn.hmst.common.pojo.HmResult;
import cn.hmst.pojo.TbItem;
import cn.hmst.query.PageQuery;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public interface ItemService {
    TbItem getItemById(long itemId);
    PageInfo<TbItem> getItemList(PageQuery pageQuery, String keyWord);
    HmResult addItem(TbItem tbItem , String desc);
}
