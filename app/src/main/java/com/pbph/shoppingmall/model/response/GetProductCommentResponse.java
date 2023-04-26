package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/9.
 */

public class GetProductCommentResponse  extends BaseResponesModel {


    /**
     * data : {"commentPageBean":{"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,
     * "firstPageNo":1,"lastPageNo":1,"list":[{"commentContent":"aaaaaaaaaaaaaa","commentId":51,
     * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发(黑色二尺六)",
     * "productDescription":80,"publishTime":1527167791000,"shareImgList":["123.jgp","45fdf.jgp",
     * "/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg",
     * "/storage/emulated/0/Pictures/1525338961130.jpg",
     * "/storage/emulated/0/DCIM/Camera/1525338940020.png"],"storeName":"鹏博科技"},
     * {"commentContent":"aaaaaaaaaaaaaa","commentId":52,"customerImg":"https://pbkjb2b2cbucket
     * .oss-cn-beijing.aliyuncs.com/appImg/15293990680.jpg","customerName":"13333333333",
     * "goodsInfoName":"阿斯顿发(黑色二尺六)","productDescription":80,"publishTime":1527167834000,
     * "storeName":"鹏博科技"},{"commentContent":"aaaaaaaaaaaaaa","commentId":53,
     * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发(黑色二尺六)",
     * "productDescription":80,"publishTime":1527167834000,"storeName":"鹏博科技"}],"nextPageNo":1,
     * "pageNo":1,"pageSize":20,"prePageNo":1,"rows":3,"start":1,"startNo":1,"startRowNum":0,
     * "startRowNumApp":0,"totalPages":1},"goodsCommentCount":{"colligate":"300","count":"3",
     * "highopinion":"3","inferior":"0","middlingopinion":"0"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * commentPageBean : {"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,
         * "lastPageNo":1,"list":[{"commentContent":"aaaaaaaaaaaaaa","commentId":51,
         * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发(黑色二尺六)
         * ","productDescription":80,"publishTime":1527167791000,"shareImgList":["123.jgp","45fdf
         * .jgp","/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg",
         * "/storage/emulated/0/Pictures/1525338961130.jpg",
         * "/storage/emulated/0/DCIM/Camera/1525338940020.png"],"storeName":"鹏博科技"},
         * {"commentContent":"aaaaaaaaaaaaaa","commentId":52,
         * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发(黑色二尺六)
         * ","productDescription":80,"publishTime":1527167834000,"storeName":"鹏博科技"},
         * {"commentContent":"aaaaaaaaaaaaaa","commentId":53,
         * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发(黑色二尺六)
         * ","productDescription":80,"publishTime":1527167834000,"storeName":"鹏博科技"}],
         * "nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":3,"start":1,"startNo":1,
         * "startRowNum":0,"startRowNumApp":0,"totalPages":1}
         * goodsCommentCount : {"colligate":"300","count":"3","highopinion":"3","inferior":"0",
         * "middlingopinion":"0"}
         */

        private CommentPageBeanBean commentPageBean;
        private GoodsCommentCountBean goodsCommentCount;

        public CommentPageBeanBean getCommentPageBean() {
            return commentPageBean;
        }

        public void setCommentPageBean(CommentPageBeanBean commentPageBean) {
            this.commentPageBean = commentPageBean;
        }

        public GoodsCommentCountBean getGoodsCommentCount() {
            return goodsCommentCount;
        }

        public void setGoodsCommentCount(GoodsCommentCountBean goodsCommentCount) {
            this.goodsCommentCount = goodsCommentCount;
        }

        public static class CommentPageBeanBean implements Serializable{
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 20
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"commentContent":"aaaaaaaaaaaaaa","commentId":51,
             * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发
             * (黑色二尺六)","productDescription":80,"publishTime":1527167791000,
             * "shareImgList":["123.jgp","45fdf.jgp",
             * "/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg",
             * "/storage/emulated/0/Pictures/1525338961130.jpg",
             * "/storage/emulated/0/DCIM/Camera/1525338940020.png"],"storeName":"鹏博科技"},
             * {"commentContent":"aaaaaaaaaaaaaa","commentId":52,
             * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发
             * (黑色二尺六)","productDescription":80,"publishTime":1527167834000,"storeName":"鹏博科技"},
             * {"commentContent":"aaaaaaaaaaaaaa","commentId":53,
             * "customerImg":"https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/appImg/15293990680.jpg","customerName":"13333333333","goodsInfoName":"阿斯顿发
             * (黑色二尺六)","productDescription":80,"publishTime":1527167834000,"storeName":"鹏博科技"}]
             * nextPageNo : 1
             * pageNo : 1
             * pageSize : 20
             * prePageNo : 1
             * rows : 3
             * start : 1
             * startNo : 1
             * startRowNum : 0
             * startRowNumApp : 0
             * totalPages : 1
             */

            private int end;
            private int endNo;
            private int endRowNum;
            private int endRowNumApp;
            private int firstPageNo;
            private int lastPageNo;
            private int nextPageNo;
            private int pageNo;
            private int pageSize;
            private int prePageNo;
            private int rows;
            private int start;
            private int startNo;
            private int startRowNum;
            private int startRowNumApp;
            private int totalPages;
            private List<ListBean> list;

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getEndNo() {
                return endNo;
            }

            public void setEndNo(int endNo) {
                this.endNo = endNo;
            }

            public int getEndRowNum() {
                return endRowNum;
            }

            public void setEndRowNum(int endRowNum) {
                this.endRowNum = endRowNum;
            }

            public int getEndRowNumApp() {
                return endRowNumApp;
            }

            public void setEndRowNumApp(int endRowNumApp) {
                this.endRowNumApp = endRowNumApp;
            }

            public int getFirstPageNo() {
                return firstPageNo;
            }

            public void setFirstPageNo(int firstPageNo) {
                this.firstPageNo = firstPageNo;
            }

            public int getLastPageNo() {
                return lastPageNo;
            }

            public void setLastPageNo(int lastPageNo) {
                this.lastPageNo = lastPageNo;
            }

            public int getNextPageNo() {
                return nextPageNo;
            }

            public void setNextPageNo(int nextPageNo) {
                this.nextPageNo = nextPageNo;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPrePageNo() {
                return prePageNo;
            }

            public void setPrePageNo(int prePageNo) {
                this.prePageNo = prePageNo;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getStartNo() {
                return startNo;
            }

            public void setStartNo(int startNo) {
                this.startNo = startNo;
            }

            public int getStartRowNum() {
                return startRowNum;
            }

            public void setStartRowNum(int startRowNum) {
                this.startRowNum = startRowNum;
            }

            public int getStartRowNumApp() {
                return startRowNumApp;
            }

            public void setStartRowNumApp(int startRowNumApp) {
                this.startRowNumApp = startRowNumApp;
            }

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean  implements Serializable{
                /**
                 * commentContent : aaaaaaaaaaaaaa
                 * commentId : 51
                 * customerImg : https://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/appImg/15293990680.jpg
                 * customerName : 13333333333
                 * goodsInfoName : 阿斯顿发(黑色二尺六)
                 * productDescription : 80
                 * publishTime : 1527167791000
                 * shareImgList : ["123.jgp","45fdf.jgp",
                 * "/storage/emulated/0/DCIM/camera/IMG_20180529_134339.jpg",
                 * "/storage/emulated/0/Pictures/1525338961130.jpg",
                 * "/storage/emulated/0/DCIM/Camera/1525338940020.png"]
                 * storeName : 鹏博科技
                 */

                private String commentContent;
                private int commentId;
                private String customerImg;
                private String customerName;
                private String goodsInfoName;
                private int productDescription;
                private long publishTime;
                private String storeName;
                private List<String> shareImgList;
                private int commentScore;

                public int getCommentScore() {
                    return commentScore;
                }

                public void setCommentScore(int commentScore) {
                    this.commentScore = commentScore;
                }

                public String getCommentContent() {
                    return commentContent;
                }

                public void setCommentContent(String commentContent) {
                    this.commentContent = commentContent;
                }

                public int getCommentId() {
                    return commentId;
                }

                public void setCommentId(int commentId) {
                    this.commentId = commentId;
                }

                public String getCustomerImg() {
                    return customerImg;
                }

                public void setCustomerImg(String customerImg) {
                    this.customerImg = customerImg;
                }

                public String getCustomerName() {
                    return customerName;
                }

                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }

                public String getGoodsInfoName() {
                    return goodsInfoName;
                }

                public void setGoodsInfoName(String goodsInfoName) {
                    this.goodsInfoName = goodsInfoName;
                }

                public int getProductDescription() {
                    return productDescription;
                }

                public void setProductDescription(int productDescription) {
                    this.productDescription = productDescription;
                }

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }

                public List<String> getShareImgList() {
                    return shareImgList;
                }

                public void setShareImgList(List<String> shareImgList) {
                    this.shareImgList = shareImgList;
                }
            }
        }

        public static class GoodsCommentCountBean implements Serializable {
            /**
             * colligate : 300
             * count : 3
             * highopinion : 3
             * inferior : 0
             * middlingopinion : 0
             */

            private String colligate;
            private String count;
            private String highopinion;
            private String inferior;
            private String middlingopinion;

            public String getColligate() {
                return colligate;
            }

            public void setColligate(String colligate) {
                this.colligate = colligate;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getHighopinion() {
                return highopinion;
            }

            public void setHighopinion(String highopinion) {
                this.highopinion = highopinion;
            }

            public String getInferior() {
                return inferior;
            }

            public void setInferior(String inferior) {
                this.inferior = inferior;
            }

            public String getMiddlingopinion() {
                return middlingopinion;
            }

            public void setMiddlingopinion(String middlingopinion) {
                this.middlingopinion = middlingopinion;
            }
        }
    }
}
