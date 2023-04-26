package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetOssTokenResponse extends BaseResponesModel {

    /**
     * data : {"accessKeyId":"STS.NHKFqgxKxxCFo2YABbJ6eHExC","securityToken":"CAIS8QF1q6Ft5B2yfSjIr4v+DcvTlZRZz4GtbRTopUI3RrlJp4DToTz2IH9IenhhAO0Wv/ozmWBQ6P4blqB6T55OSAmcNZIoMU2ycd3lMeT7oMWQweEuqv/MQBq+aXPS2MvVfJ+KLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/i6/clFKN1ODO1dj1bHtxbCxJ/ocsBTxvrOO2qLwThjxi7biMqmHIl1Tsks/Xin5zEtkCF1AOg8IJP+dSteKrDRtJ3IZJyX+2y2OFLbafb2EZSkUMVqPkn3fUZqWaZ4ozNWwcJug/nNPHP+9luPRJ/dlbmyX+PTS+WGoABMc2zf6Dbd0yG6ySI+2OYefAY6WUhl7YU+4vil2nCnE74D/lgpbs+z1VhNlEp5at2HmWLZ9jqF2fIVIlNiutiy3z2ddB4cl8hlVSnf7AJ82m+AeUovr5Fy7Y7+PNPlS3qETCHnMp/jF/VIegwJgxpkJcf30h1kz6NmqnCTYt3iOI=","accessKeySecret":"9jEQFaC91ab4ZmUnKMt9iVSS3rBZWsAWX67M5m1T4EVh","expiration":"2018-05-17T07:32:05Z"}
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
         * accessKeyId : STS.NHKFqgxKxxCFo2YABbJ6eHExC
         * securityToken : CAIS8QF1q6Ft5B2yfSjIr4v+DcvTlZRZz4GtbRTopUI3RrlJp4DToTz2IH9IenhhAO0Wv/ozmWBQ6P4blqB6T55OSAmcNZIoMU2ycd3lMeT7oMWQweEuqv/MQBq+aXPS2MvVfJ+KLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/i6/clFKN1ODO1dj1bHtxbCxJ/ocsBTxvrOO2qLwThjxi7biMqmHIl1Tsks/Xin5zEtkCF1AOg8IJP+dSteKrDRtJ3IZJyX+2y2OFLbafb2EZSkUMVqPkn3fUZqWaZ4ozNWwcJug/nNPHP+9luPRJ/dlbmyX+PTS+WGoABMc2zf6Dbd0yG6ySI+2OYefAY6WUhl7YU+4vil2nCnE74D/lgpbs+z1VhNlEp5at2HmWLZ9jqF2fIVIlNiutiy3z2ddB4cl8hlVSnf7AJ82m+AeUovr5Fy7Y7+PNPlS3qETCHnMp/jF/VIegwJgxpkJcf30h1kz6NmqnCTYt3iOI=
         * accessKeySecret : 9jEQFaC91ab4ZmUnKMt9iVSS3rBZWsAWX67M5m1T4EVh
         * expiration : 2018-05-17T07:32:05Z
         */

        private String accessKeyId;
        private String securityToken;
        private String accessKeySecret;
        private String expiration;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }
    }
}
