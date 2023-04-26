package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetMyMessageListResponse extends BaseResponesModel {

    /**
     * data : {"messageList":{"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"createTime":1531195067000,"customerId":168,"delFlag":0,"expressNo":"123456000","ppid":1,"messageClassifyId":1,"messageContent":"测试内容","messageImgUrl":"http://baidu.com","messageJumpClassify":1,"messageName":"测试","messageType":1,"modifyTime":1531195070000,"readFlag":0,"remark":"哈哈哈"}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":1,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}
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
         * messageList : {"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"createTime":1531195067000,"customerId":168,"delFlag":0,"expressNo":"123456000","ppid":1,"messageClassifyId":1,"messageContent":"测试内容","messageImgUrl":"http://baidu.com","messageJumpClassify":1,"messageName":"测试","messageType":1,"modifyTime":1531195070000,"readFlag":0,"remark":"哈哈哈"}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":1,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
         */

        private MessageListBean messageList;

        public MessageListBean getMessageList() {
            return messageList;
        }

        public void setMessageList(MessageListBean messageList) {
            this.messageList = messageList;
        }

        public static class MessageListBean {
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 20
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"createTime":1531195067000,"customerId":168,"delFlag":0,"expressNo":"123456000","ppid":1,"messageClassifyId":1,"messageContent":"测试内容","messageImgUrl":"http://baidu.com","messageJumpClassify":1,"messageName":"测试","messageType":1,"modifyTime":1531195070000,"readFlag":0,"remark":"哈哈哈"}]
             * nextPageNo : 1
             * pageNo : 1
             * pageSize : 20
             * prePageNo : 1
             * rows : 1
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

            public static class ListBean {
                /**
                 * createTime : 1531195067000
                 * customerId : 168
                 * delFlag : 0
                 * expressNo : 123456000
                 * ppid : 1
                 * messageClassifyId : 1
                 * messageContent : 测试内容
                 * messageImgUrl : http://baidu.com
                 * messageJumpClassify : 1
                 * messageName : 测试
                 * messageType : 1
                 * modifyTime : 1531195070000
                 * readFlag : 0
                 * remark : 哈哈哈
                 */

                private long createTime;
                private int customerId;
                private int delFlag;
                private String expressNo;
                private int ppid;
                private int messageClassifyId;
                private String messageContent;
                private String messageImgUrl;
                private int messageJumpClassify;
                private String messageName;
                private int messageType;
                private long modifyTime;
                private int readFlag;
                private String remark;

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(int customerId) {
                    this.customerId = customerId;
                }

                public int getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(int delFlag) {
                    this.delFlag = delFlag;
                }

                public String getExpressNo() {
                    return expressNo;
                }

                public void setExpressNo(String expressNo) {
                    this.expressNo = expressNo;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public int getMessageClassifyId() {
                    return messageClassifyId;
                }

                public void setMessageClassifyId(int messageClassifyId) {
                    this.messageClassifyId = messageClassifyId;
                }

                public String getMessageContent() {
                    return messageContent;
                }

                public void setMessageContent(String messageContent) {
                    this.messageContent = messageContent;
                }

                public String getMessageImgUrl() {
                    return messageImgUrl;
                }

                public void setMessageImgUrl(String messageImgUrl) {
                    this.messageImgUrl = messageImgUrl;
                }

                public int getMessageJumpClassify() {
                    return messageJumpClassify;
                }

                public void setMessageJumpClassify(int messageJumpClassify) {
                    this.messageJumpClassify = messageJumpClassify;
                }

                public String getMessageName() {
                    return messageName;
                }

                public void setMessageName(String messageName) {
                    this.messageName = messageName;
                }

                public int getMessageType() {
                    return messageType;
                }

                public void setMessageType(int messageType) {
                    this.messageType = messageType;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getReadFlag() {
                    return readFlag;
                }

                public void setReadFlag(int readFlag) {
                    this.readFlag = readFlag;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }
        }
    }
}
