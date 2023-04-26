package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by 连嘉凡 on 2018/5/31.
 */

public class GetStorePageFloorResponse extends BaseResponesModel {


    /**
     * data : {"defaultImage":"","floorMap":{"fourFloor":{"floorTiTle":"四楼标题",
     * "goodsInfoAndImgMap":{"left":{"goodsInfoId":112,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519895671751.jpg"},"right":{"goodsInfoId":108,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1520151831859.jpg"}}},"oneFloor":{"floorTiTle":"一楼标题",
     * "goodsInfoAndImgMap":{"main":{"goodsInfoId":112,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1515728580245.png"}}},"threeFloor":{"floorTiTle":"三楼标题",
     * "goodsInfoAndImgMap":{"left":{"goodsInfoId":109,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519541502235.png"},"main":{"goodsInfoId":122,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519895352210.jpg"},"right":{"goodsInfoId":105,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519895386305.jpg"}}},"twoFloor":{"floorTiTle":"二楼标题",
     * "goodsInfoAndImgMap":{"left":{"goodsInfoId":108,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519458673068.png"},"right":{"goodsInfoId":121,
     * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1519542231573.jpg"}}}},"ppid":1,"storeId":37}
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
         * defaultImage :
         * floorMap : {"fourFloor":{"floorTiTle":"四楼标题",
         * "goodsInfoAndImgMap":{"left":{"goodsInfoId":112,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519895671751.jpg"},"right":{"goodsInfoId":108,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1520151831859.jpg"}}},"oneFloor":{"floorTiTle":"一楼标题",
         * "goodsInfoAndImgMap":{"main":{"goodsInfoId":112,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1515728580245.png"}}},"threeFloor":{"floorTiTle":"三楼标题",
         * "goodsInfoAndImgMap":{"left":{"goodsInfoId":109,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519541502235.png"},"main":{"goodsInfoId":122,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519895352210.jpg"},"right":{"goodsInfoId":105,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519895386305.jpg"}}},"twoFloor":{"floorTiTle":"二楼标题",
         * "goodsInfoAndImgMap":{"left":{"goodsInfoId":108,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519458673068.png"},"right":{"goodsInfoId":121,
         * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1519542231573.jpg"}}}}
         * ppid : 1
         * storeId : 37
         */

        private String defaultImage;
        private FloorMapBean floorMap;
        private int ppid;
        private int storeId;


        public String getDefaultImage() {
            return defaultImage;
        }

        public void setDefaultImage(String defaultImage) {
            this.defaultImage = defaultImage;
        }

        public FloorMapBean getFloorMap() {
            return floorMap;
        }

        public void setFloorMap(FloorMapBean floorMap) {
            this.floorMap = floorMap;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public static class FloorMapBean {
            /**
             * fourFloor : {"floorTiTle":"四楼标题","goodsInfoAndImgMap":{"left":{"goodsInfoId":112,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519895671751.jpg"},"right":{"goodsInfoId":108,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1520151831859.jpg"}}}
             * oneFloor : {"floorTiTle":"一楼标题","goodsInfoAndImgMap":{"main":{"goodsInfoId":112,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1515728580245.png"}}}
             * threeFloor : {"floorTiTle":"三楼标题","goodsInfoAndImgMap":{"left":{"goodsInfoId":109,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519541502235.png"},"main":{"goodsInfoId":122,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519895352210.jpg"},"right":{"goodsInfoId":105,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519895386305.jpg"}}}
             * twoFloor : {"floorTiTle":"二楼标题","goodsInfoAndImgMap":{"left":{"goodsInfoId":108,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519458673068.png"},"right":{"goodsInfoId":121,
             * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
             * .com/productimage2/3/1519542231573.jpg"}}}
             */

            private FourFloorBean fourFloor;
            private OneFloorBean oneFloor;
            private ThreeFloorBean threeFloor;
            private TwoFloorBean twoFloor;

            public FourFloorBean getFourFloor() {
                return fourFloor;
            }

            public void setFourFloor(FourFloorBean fourFloor) {
                this.fourFloor = fourFloor;
            }

            public OneFloorBean getOneFloor() {
                return oneFloor;
            }

            public void setOneFloor(OneFloorBean oneFloor) {
                this.oneFloor = oneFloor;
            }

            public ThreeFloorBean getThreeFloor() {
                return threeFloor;
            }

            public void setThreeFloor(ThreeFloorBean threeFloor) {
                this.threeFloor = threeFloor;
            }

            public TwoFloorBean getTwoFloor() {
                return twoFloor;
            }

            public void setTwoFloor(TwoFloorBean twoFloor) {
                this.twoFloor = twoFloor;
            }

            public static class FourFloorBean {
                /**
                 * floorTiTle : 四楼标题
                 * goodsInfoAndImgMap : {"left":{"goodsInfoId":112,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1519895671751.jpg"},"right":{"goodsInfoId":108,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1520151831859.jpg"}}
                 */

                private String floorTiTle;
                private GoodsInfoAndImgMapBean goodsInfoAndImgMap;

                public String getFloorTiTle() {
                    return floorTiTle;
                }

                public void setFloorTiTle(String floorTiTle) {
                    this.floorTiTle = floorTiTle;
                }

                public GoodsInfoAndImgMapBean getGoodsInfoAndImgMap() {
                    return goodsInfoAndImgMap;
                }

                public void setGoodsInfoAndImgMap(GoodsInfoAndImgMapBean goodsInfoAndImgMap) {
                    this.goodsInfoAndImgMap = goodsInfoAndImgMap;
                }

                public static class GoodsInfoAndImgMapBean {
                    /**
                     * left : {"goodsInfoId":112,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1519895671751.jpg"}
                     * right : {"goodsInfoId":108,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1520151831859.jpg"}
                     */

                    private LeftBean left;
                    private RightBean right;

                    public LeftBean getLeft() {
                        return left;
                    }

                    public void setLeft(LeftBean left) {
                        this.left = left;
                    }

                    public RightBean getRight() {
                        return right;
                    }

                    public void setRight(RightBean right) {
                        this.right = right;
                    }

                    public static class LeftBean {
                        /**
                         * goodsInfoId : 112
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1519895671751.jpg
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }

                    public static class RightBean {
                        /**
                         * goodsInfoId : 108
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1520151831859.jpg
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }
                }
            }

            public static class OneFloorBean {
                /**
                 * floorTiTle : 一楼标题
                 * goodsInfoAndImgMap : {"main":{"goodsInfoId":112,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1515728580245.png"}}
                 */

                private String floorTiTle;
                private GoodsInfoAndImgMapBeanX goodsInfoAndImgMap;

                public String getFloorTiTle() {
                    return floorTiTle;
                }

                public void setFloorTiTle(String floorTiTle) {
                    this.floorTiTle = floorTiTle;
                }

                public GoodsInfoAndImgMapBeanX getGoodsInfoAndImgMap() {
                    return goodsInfoAndImgMap;
                }

                public void setGoodsInfoAndImgMap(GoodsInfoAndImgMapBeanX goodsInfoAndImgMap) {
                    this.goodsInfoAndImgMap = goodsInfoAndImgMap;
                }

                public static class GoodsInfoAndImgMapBeanX {
                    /**
                     * main : {"goodsInfoId":112,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1515728580245.png"}
                     */

                    private MainBean main;

                    public MainBean getMain() {
                        return main;
                    }

                    public void setMain(MainBean main) {
                        this.main = main;
                    }

                    public static class MainBean {
                        /**
                         * goodsInfoId : 112
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1515728580245.png
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }
                }
            }

            public static class ThreeFloorBean {
                /**
                 * floorTiTle : 三楼标题
                 * goodsInfoAndImgMap : {"left":{"goodsInfoId":109,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1519541502235.png"},"main":{"goodsInfoId":122,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1519895352210.jpg"},"right":{"goodsInfoId":105,
                 * "goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                 * .com/productimage2/3/1519895386305.jpg"}}
                 */

                private String floorTiTle;
                private GoodsInfoAndImgMapBeanXX goodsInfoAndImgMap;

                public String getFloorTiTle() {
                    return floorTiTle;
                }

                public void setFloorTiTle(String floorTiTle) {
                    this.floorTiTle = floorTiTle;
                }

                public GoodsInfoAndImgMapBeanXX getGoodsInfoAndImgMap() {
                    return goodsInfoAndImgMap;
                }

                public void setGoodsInfoAndImgMap(GoodsInfoAndImgMapBeanXX goodsInfoAndImgMap) {
                    this.goodsInfoAndImgMap = goodsInfoAndImgMap;
                }

                public static class GoodsInfoAndImgMapBeanXX {
                    /**
                     * left : {"goodsInfoId":109,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1519541502235.png"}
                     * main : {"goodsInfoId":122,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1519895352210.jpg"}
                     * right : {"goodsInfoId":105,"goodsInfoImageUrl":"http://pbkjb2b2cbucket
                     * .oss-cn-beijing.aliyuncs.com/productimage2/3/1519895386305.jpg"}
                     */

                    private LeftBeanX left;
                    private MainBeanX main;
                    private RightBeanX right;

                    public LeftBeanX getLeft() {
                        return left;
                    }

                    public void setLeft(LeftBeanX left) {
                        this.left = left;
                    }

                    public MainBeanX getMain() {
                        return main;
                    }

                    public void setMain(MainBeanX main) {
                        this.main = main;
                    }

                    public RightBeanX getRight() {
                        return right;
                    }

                    public void setRight(RightBeanX right) {
                        this.right = right;
                    }

                    public static class LeftBeanX {
                        /**
                         * goodsInfoId : 109
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519541502235.png
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }

                    public static class MainBeanX {
                        /**
                         * goodsInfoId : 122
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519895352210.jpg
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }

                    public static class RightBeanX {
                        /**
                         * goodsInfoId : 105
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519895386305.jpg
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }
                }
            }

            public static class TwoFloorBean {
                /**
                 * floorTiTle : 二楼标题
                 * goodsInfoAndImgMap : {"left":{"goodsInfoId":108,"goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519458673068.png"},"right":{"goodsInfoId":121,"goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519542231573.jpg"}}
                 */

                private String floorTiTle;
                private GoodsInfoAndImgMapBeanXXX goodsInfoAndImgMap;

                public String getFloorTiTle() {
                    return floorTiTle;
                }

                public void setFloorTiTle(String floorTiTle) {
                    this.floorTiTle = floorTiTle;
                }

                public GoodsInfoAndImgMapBeanXXX getGoodsInfoAndImgMap() {
                    return goodsInfoAndImgMap;
                }

                public void setGoodsInfoAndImgMap(GoodsInfoAndImgMapBeanXXX goodsInfoAndImgMap) {
                    this.goodsInfoAndImgMap = goodsInfoAndImgMap;
                }

                public static class GoodsInfoAndImgMapBeanXXX {
                    /**
                     * left : {"goodsInfoId":108,"goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519458673068.png"}
                     * right : {"goodsInfoId":121,"goodsInfoImageUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519542231573.jpg"}
                     */

                    private LeftBeanXX left;
                    private RightBeanXX right;

                    public LeftBeanXX getLeft() {
                        return left;
                    }

                    public void setLeft(LeftBeanXX left) {
                        this.left = left;
                    }

                    public RightBeanXX getRight() {
                        return right;
                    }

                    public void setRight(RightBeanXX right) {
                        this.right = right;
                    }

                    public static class LeftBeanXX {
                        /**
                         * goodsInfoId : 108
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519458673068.png
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }

                    public static class RightBeanXX {
                        /**
                         * goodsInfoId : 121
                         * goodsInfoImageUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519542231573.jpg
                         */

                        private int goodsInfoId;
                        private String goodsInfoImageUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImageUrl() {
                            return goodsInfoImageUrl;
                        }

                        public void setGoodsInfoImageUrl(String goodsInfoImageUrl) {
                            this.goodsInfoImageUrl = goodsInfoImageUrl;
                        }
                    }
                }
            }
        }
    }
}
