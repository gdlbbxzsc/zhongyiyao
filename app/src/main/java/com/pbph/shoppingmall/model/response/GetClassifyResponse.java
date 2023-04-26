package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/7.
 */

public class GetClassifyResponse extends BaseResponesModel {


    /**
     * data : {"cateBarVos":[{"childs":[{"childs":[{"childs":[],"createTime":1525330179000,
     * "delFlag":0,"goodsCateId":36,"grade":3,"ppid":5,"isOpen":1,"name":"旗袍","parentId":4,
     * "sort":1}],"createTime":1525330002000,"delFlag":0,"goodsCateId":35,"grade":2,"ppid":4,
     * "isOpen":1,"name":"女装","parentId":1,"sort":1}],"createTime":1525327250000,"delFlag":0,
     * "grade":1,"ppid":1,"isOpen":1,"name":"1F服装总汇","parentId":0,"sort":1},{"childs":[],
     * "createTime":1525329445000,"delFlag":0,"grade":1,"ppid":2,"isOpen":1,"name":"xxxxx",
     * "parentId":0,"sort":2},{"childs":[],"createTime":1525329494000,"delFlag":0,"grade":1,
     * "ppid":3,"isOpen":1,"name":"3F工艺品/化妆品","parentId":0,"sort":3}],"cateId":0,
     * "cates":[{"createTime":1525327250000,"delFlag":0,"grade":1,"ppid":1,"isOpen":1,
     * "name":"1F服装总汇","parentId":0,"sort":1},{"createTime":1525329445000,"delFlag":0,"grade":1,
     * "ppid":2,"isOpen":1,"name":"xxxxx","parentId":0,"sort":2},{"createTime":1525329494000,
     * "delFlag":0,"grade":1,"ppid":3,"isOpen":1,"name":"3F工艺品/化妆品","parentId":0,"sort":3}]}
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
         * cateBarVos : [{"childs":[{"childs":[{"childs":[],"createTime":1525330179000,
         * "delFlag":0,"goodsCateId":36,"grade":3,"ppid":5,"isOpen":1,"name":"旗袍","parentId":4,
         * "sort":1}],"createTime":1525330002000,"delFlag":0,"goodsCateId":35,"grade":2,"ppid":4,
         * "isOpen":1,"name":"女装","parentId":1,"sort":1}],"createTime":1525327250000,"delFlag":0,
         * "grade":1,"ppid":1,"isOpen":1,"name":"1F服装总汇","parentId":0,"sort":1},{"childs":[],
         * "createTime":1525329445000,"delFlag":0,"grade":1,"ppid":2,"isOpen":1,"name":"xxxxx",
         * "parentId":0,"sort":2},{"childs":[],"createTime":1525329494000,"delFlag":0,"grade":1,
         * "ppid":3,"isOpen":1,"name":"3F工艺品/化妆品","parentId":0,"sort":3}]
         * cateId : 0
         * cates : [{"createTime":1525327250000,"delFlag":0,"grade":1,"ppid":1,"isOpen":1,
         * "name":"1F服装总汇","parentId":0,"sort":1},{"createTime":1525329445000,"delFlag":0,
         * "grade":1,"ppid":2,"isOpen":1,"name":"xxxxx","parentId":0,"sort":2},
         * {"createTime":1525329494000,"delFlag":0,"grade":1,"ppid":3,"isOpen":1,
         * "name":"3F工艺品/化妆品","parentId":0,"sort":3}]
         */

        private int cateId;
        private List<CateBarVosBean> cateBarVos;
        private List<CatesBean> cates;

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public List<CateBarVosBean> getCateBarVos() {
            return cateBarVos;
        }

        public void setCateBarVos(List<CateBarVosBean> cateBarVos) {
            this.cateBarVos = cateBarVos;
        }

        public List<CatesBean> getCates() {
            return cates;
        }

        public void setCates(List<CatesBean> cates) {
            this.cates = cates;
        }

        public static class CateBarVosBean {
            /**
             * childs : [{"childs":[{"childs":[],"createTime":1525330179000,"delFlag":0,
             * "goodsCateId":36,"grade":3,"ppid":5,"isOpen":1,"name":"旗袍","parentId":4,
             * "sort":1}],"createTime":1525330002000,"delFlag":0,"goodsCateId":35,"grade":2,
             * "ppid":4,"isOpen":1,"name":"女装","parentId":1,"sort":1}]
             * createTime : 1525327250000
             * delFlag : 0
             * grade : 1
             * ppid : 1
             * isOpen : 1
             * name : 1F服装总汇
             * parentId : 0
             * sort : 1
             */

            private String catAdvertisementImage;
            private long createTime;
            private int delFlag;
            private int grade;
            private int ppid;
            private int isOpen;
            private String name;
            private int parentId;
            private int sort;
            private List<ChildsBeanX> childs;

            public String getCatAdvertisementImage() {
                return catAdvertisementImage;
            }

            public void setCatAdvertisementImage(String catAdvertisementImage) {
                this.catAdvertisementImage = catAdvertisementImage;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(int isOpen) {
                this.isOpen = isOpen;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ChildsBeanX> getChilds() {
                return childs;
            }

            public void setChilds(List<ChildsBeanX> childs) {
                this.childs = childs;
            }

            public static class ChildsBeanX {
                /**
                 * childs : [{"childs":[],"createTime":1525330179000,"delFlag":0,
                 * "goodsCateId":36,"grade":3,"ppid":5,"isOpen":1,"name":"旗袍","parentId":4,
                 * "sort":1}]
                 * createTime : 1525330002000
                 * delFlag : 0
                 * goodsCateId : 35
                 * grade : 2
                 * ppid : 4
                 * isOpen : 1
                 * name : 女装
                 * parentId : 1
                 * sort : 1
                 */

                private long createTime;
                private int delFlag;
                private int goodsCateId;
                private int grade;
                private int ppid;
                private int isOpen;
                private String name;
                private int parentId;
                private int sort;
                private List<ChildsBean> childs;
                private String cateImage;

                public String getCateImage() {
                    return cateImage;
                }

                public void setCateImage(String cateImage) {
                    this.cateImage = cateImage;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(int delFlag) {
                    this.delFlag = delFlag;
                }

                public int getGoodsCateId() {
                    return goodsCateId;
                }

                public void setGoodsCateId(int goodsCateId) {
                    this.goodsCateId = goodsCateId;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public int getIsOpen() {
                    return isOpen;
                }

                public void setIsOpen(int isOpen) {
                    this.isOpen = isOpen;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public List<ChildsBean> getChilds() {
                    return childs;
                }

                public void setChilds(List<ChildsBean> childs) {
                    this.childs = childs;
                }

                public static class ChildsBean {
                    /**
                     * childs : []
                     * createTime : 1525330179000
                     * delFlag : 0
                     * goodsCateId : 36
                     * grade : 3
                     * ppid : 5
                     * isOpen : 1
                     * name : 旗袍
                     * parentId : 4
                     * sort : 1
                     */

                    private long createTime;
                    private int delFlag;
                    private int goodsCateId;
                    private int grade;
                    private int ppid;
                    private int isOpen;
                    private String name;
                    private int parentId;
                    private int sort;
                    private List<?> childs;

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }

                    public int getDelFlag() {
                        return delFlag;
                    }

                    public void setDelFlag(int delFlag) {
                        this.delFlag = delFlag;
                    }

                    public int getGoodsCateId() {
                        return goodsCateId;
                    }

                    public void setGoodsCateId(int goodsCateId) {
                        this.goodsCateId = goodsCateId;
                    }

                    public int getGrade() {
                        return grade;
                    }

                    public void setGrade(int grade) {
                        this.grade = grade;
                    }

                    public int getPpid() {
                        return ppid;
                    }

                    public void setPpid(int ppid) {
                        this.ppid = ppid;
                    }

                    public int getIsOpen() {
                        return isOpen;
                    }

                    public void setIsOpen(int isOpen) {
                        this.isOpen = isOpen;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public List<?> getChilds() {
                        return childs;
                    }

                    public void setChilds(List<?> childs) {
                        this.childs = childs;
                    }
                }
            }
        }

        public static class CatesBean {
            /**
             * createTime : 1525327250000
             * delFlag : 0
             * grade : 1
             * ppid : 1
             * isOpen : 1
             * name : 1F服装总汇
             * parentId : 0
             * sort : 1
             */

            private long createTime;
            private int delFlag;
            private int grade;
            private int ppid;
            private int isOpen;
            private String name;
            private int parentId;
            private int sort;
            private String catAdvertisementImage;

            public String getCatAdvertisementImage() {
                return catAdvertisementImage;
            }

            public void setCatAdvertisementImage(String catAdvertisementImage) {
                this.catAdvertisementImage = catAdvertisementImage;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(int isOpen) {
                this.isOpen = isOpen;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
