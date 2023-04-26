package com.pbph.shoppingmall.module.main.type.childtypelist;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/5.
 */

public interface ChildTypeContract {
    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void setHttpData(List<GetClassifyResponse.DataBean.CateBarVosBean> cateBarVosBeans,
                         List<GetClassifyResponse.DataBean.CatesBean> catesBeanList);
    }

    interface Presenter extends IBasePresenter {
         void getHttpData(int mobCatebarId);
    }
}
