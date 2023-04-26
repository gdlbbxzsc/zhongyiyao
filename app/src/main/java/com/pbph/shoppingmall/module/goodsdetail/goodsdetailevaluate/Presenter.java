package com.pbph.shoppingmall.module.goodsdetail.goodsdetailevaluate;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetProductCommentRequest;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract
        .Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情

    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void getHttpData(int startRowNum, int endRowNum, int goodsId, int type, String title) {

        GetProductCommentRequest<GetProductCommentResponse> getProductCommentRequest = new
                GetProductCommentRequest<>();

        getProductCommentRequest.endRowNum = endRowNum;
        getProductCommentRequest.startRowNum = startRowNum;
        getProductCommentRequest.goodsId = goodsId;
        getProductCommentRequest.type = type;
//        getProductCommentRequest.title = title;

        WaitUI.Show(getBaseView().getContext());
        getProductCommentRequest.request().subscribe(new LogoutConsumer<GetProductCommentResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(GetProductCommentResponse vo) throws Exception {

                WaitUI.Cancel();
                getBaseView().setGoodsCommentCount(vo.getData().getGoodsCommentCount());
                getBaseView().setEvaluateData(vo.getData().getCommentPageBean().getList());

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                WaitUI.Cancel();
            }
        });
//        List<Bean> lists = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            Bean bean = new Bean();
//            bean.setName("a "+i);
//            bean.setStringList(new ArrayList<>());
//            for (int j = 0; j <= i+4; j++) {
//                bean.getStringList().add("b "+j);
//            }
//            lists.add(bean);
//
//        }
//
    }
}
