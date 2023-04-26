package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetSysSwitchResponse extends BaseResponesModel {

    /**
     * data : {"npSysSwitch":{"ppid":1,"swichKuaidi100":0,"switchAuditThirdGoods":1,"switchChat":0,"switchSign":1}}
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
         * npSysSwitch : {"ppid":1,"swichKuaidi100":0,"switchAuditThirdGoods":1,"switchChat":0,"switchSign":1}
         */

        private NpSysSwitchBean npSysSwitch;

        public NpSysSwitchBean getNpSysSwitch() {
            return npSysSwitch;
        }

        public void setNpSysSwitch(NpSysSwitchBean npSysSwitch) {
            this.npSysSwitch = npSysSwitch;
        }

        public static class NpSysSwitchBean {
            /**
             * ppid : 1
             * swichKuaidi100 : 0
             * switchAuditThirdGoods : 1
             * switchChat : 0
             * switchSign : 1
             */

            private int ppid;
            private int swichKuaidi100;
            private int switchAuditThirdGoods;
            private int switchChat;
            private int switchSign;

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getSwichKuaidi100() {
                return swichKuaidi100;
            }

            public void setSwichKuaidi100(int swichKuaidi100) {
                this.swichKuaidi100 = swichKuaidi100;
            }

            public int getSwitchAuditThirdGoods() {
                return switchAuditThirdGoods;
            }

            public void setSwitchAuditThirdGoods(int switchAuditThirdGoods) {
                this.switchAuditThirdGoods = switchAuditThirdGoods;
            }

            public int getSwitchChat() {
                return switchChat;
            }

            public void setSwitchChat(int switchChat) {
                this.switchChat = switchChat;
            }

            public int getSwitchSign() {
                return switchSign;
            }

            public void setSwitchSign(int switchSign) {
                this.switchSign = switchSign;
            }
        }
    }
}
