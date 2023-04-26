package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetCommentDetailResponse extends BaseResponesModel {

    /**
     * data : {"commentDTOList":[{"commentContent":"","goodsId":111,"goodsInfoCostPrice":123,"goodsInfoId":122,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoMarketPrice":123,"goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoPreferPrice":123,"goodsInfoSubtitle":"金立手机","productDescription":50,"shareImgList":[{"createTime":1527663689000,"ppid":37,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg"},{"createTime":1527663689000,"ppid":38,"shareId":18,"shareImage":"/storage/emulated/0/Pictures/1525338961130.jpg"},{"createTime":1527663689000,"ppid":39,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/Camera/1525338940020.png"}],"thirdVal":0},{"commentContent":"","goodsId":108,"goodsInfoCostPrice":123,"goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoMarketPrice":123,"goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoPreferPrice":123,"goodsInfoSubtitle":"旗袍1","productDescription":100,"shareImgList":[{"createTime":1527663689000,"ppid":37,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg"},{"createTime":1527663689000,"ppid":38,"shareId":18,"shareImage":"/storage/emulated/0/Pictures/1525338961130.jpg"},{"createTime":1527663689000,"ppid":39,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/Camera/1525338940020.png"}],"thirdVal":0}],"commentDesk":100,"commentLogistics":100,"isAnonymous":1,"orderId":43}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentDTOList : [{"commentContent":"","goodsId":111,"goodsInfoCostPrice":123,"goodsInfoId":122,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoMarketPrice":123,"goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoPreferPrice":123,"goodsInfoSubtitle":"金立手机","productDescription":50,"shareImgList":[{"createTime":1527663689000,"ppid":37,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg"},{"createTime":1527663689000,"ppid":38,"shareId":18,"shareImage":"/storage/emulated/0/Pictures/1525338961130.jpg"},{"createTime":1527663689000,"ppid":39,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/Camera/1525338940020.png"}],"thirdVal":0},{"commentContent":"","goodsId":108,"goodsInfoCostPrice":123,"goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoMarketPrice":123,"goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoPreferPrice":123,"goodsInfoSubtitle":"旗袍1","productDescription":100,"shareImgList":[{"createTime":1527663689000,"ppid":37,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg"},{"createTime":1527663689000,"ppid":38,"shareId":18,"shareImage":"/storage/emulated/0/Pictures/1525338961130.jpg"},{"createTime":1527663689000,"ppid":39,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/Camera/1525338940020.png"}],"thirdVal":0}]
         * commentDesk : 100
         * commentLogistics : 100
         * isAnonymous : 1
         * orderId : 43
         */

        private int commentDesk;
        private int commentLogistics;
        private int isAnonymous;
        private int orderId;
        private List<CommentDTOListBean> commentDTOList;

        public int getCommentDesk() {
            return commentDesk;
        }

        public void setCommentDesk(int commentDesk) {
            this.commentDesk = commentDesk;
        }

        public int getCommentLogistics() {
            return commentLogistics;
        }

        public void setCommentLogistics(int commentLogistics) {
            this.commentLogistics = commentLogistics;
        }

        public int getIsAnonymous() {
            return isAnonymous;
        }

        public void setIsAnonymous(int isAnonymous) {
            this.isAnonymous = isAnonymous;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public List<CommentDTOListBean> getCommentDTOList() {
            return commentDTOList;
        }

        public void setCommentDTOList(List<CommentDTOListBean> commentDTOList) {
            this.commentDTOList = commentDTOList;
        }

        public static class CommentDTOListBean {
            /**
             * commentContent :
             * goodsId : 111
             * goodsInfoCostPrice : 123
             * goodsInfoId : 122
             * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160
             * goodsInfoMarketPrice : 123
             * goodsInfoName : jin金立手机(黑色二尺六50*40)
             * goodsInfoPreferPrice : 123
             * goodsInfoSubtitle : 金立手机
             * productDescription : 50
             * shareImgList : [{"createTime":1527663689000,"ppid":37,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg"},{"createTime":1527663689000,"ppid":38,"shareId":18,"shareImage":"/storage/emulated/0/Pictures/1525338961130.jpg"},{"createTime":1527663689000,"ppid":39,"shareId":18,"shareImage":"/storage/emulated/0/DCIM/Camera/1525338940020.png"}]
             * thirdVal : 0
             */

            private String commentContent;
            private int goodsId;
            private int goodsInfoCostPrice;
            private int goodsInfoId;
            private String goodsInfoImgUrl;
            private int goodsInfoMarketPrice;
            private String goodsInfoName;
            private int goodsInfoPreferPrice;
            private String goodsInfoSubtitle;
            private int productDescription;
            private int thirdVal;
            private List<ShareImgListBean> shareImgList;

            public String getCommentContent() {
                return commentContent;
            }

            public void setCommentContent(String commentContent) {
                this.commentContent = commentContent;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsInfoCostPrice() {
                return goodsInfoCostPrice;
            }

            public void setGoodsInfoCostPrice(int goodsInfoCostPrice) {
                this.goodsInfoCostPrice = goodsInfoCostPrice;
            }

            public int getGoodsInfoId() {
                return goodsInfoId;
            }

            public void setGoodsInfoId(int goodsInfoId) {
                this.goodsInfoId = goodsInfoId;
            }

            public String getGoodsInfoImgUrl() {
                return goodsInfoImgUrl;
            }

            public void setGoodsInfoImgUrl(String goodsInfoImgUrl) {
                this.goodsInfoImgUrl = goodsInfoImgUrl;
            }

            public int getGoodsInfoMarketPrice() {
                return goodsInfoMarketPrice;
            }

            public void setGoodsInfoMarketPrice(int goodsInfoMarketPrice) {
                this.goodsInfoMarketPrice = goodsInfoMarketPrice;
            }

            public String getGoodsInfoName() {
                return goodsInfoName;
            }

            public void setGoodsInfoName(String goodsInfoName) {
                this.goodsInfoName = goodsInfoName;
            }

            public int getGoodsInfoPreferPrice() {
                return goodsInfoPreferPrice;
            }

            public void setGoodsInfoPreferPrice(int goodsInfoPreferPrice) {
                this.goodsInfoPreferPrice = goodsInfoPreferPrice;
            }

            public String getGoodsInfoSubtitle() {
                return goodsInfoSubtitle;
            }

            public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
                this.goodsInfoSubtitle = goodsInfoSubtitle;
            }

            public int getProductDescription() {
                return productDescription;
            }

            public void setProductDescription(int productDescription) {
                this.productDescription = productDescription;
            }

            public int getThirdVal() {
                return thirdVal;
            }

            public void setThirdVal(int thirdVal) {
                this.thirdVal = thirdVal;
            }

            public List<ShareImgListBean> getShareImgList() {
                return shareImgList;
            }

            public void setShareImgList(List<ShareImgListBean> shareImgList) {
                this.shareImgList = shareImgList;
            }

            public static class ShareImgListBean {
                /**
                 * createTime : 1527663689000
                 * ppid : 37
                 * shareId : 18
                 * shareImage : /storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg
                 */

                private long createTime;
                private int ppid;
                private int shareId;
                private String shareImage;

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public int getShareId() {
                    return shareId;
                }

                public void setShareId(int shareId) {
                    this.shareId = shareId;
                }

                public String getShareImage() {
                    return shareImage;
                }

                public void setShareImage(String shareImage) {
                    this.shareImage = shareImage;
                }
            }
        }
    }
}
