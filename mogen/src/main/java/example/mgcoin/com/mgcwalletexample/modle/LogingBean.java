package example.mgcoin.com.mgcwalletexample.modle;

/**
 * 测试用来解析请求后返回的数字
 */

public class LogingBean {


    /**
     * ErrorId : 0
     * ErrorDes :
     * Data : {"token":"30d7767f7d5956f56049a3292e66d4e1","uid":"833"}
     */

    private int ErrorId;
    private String ErrorDes;
    private DataBean Data;

    @Override
    public String toString() {
        return "LogingBean 实体类{" +
                "ErrorId=" + ErrorId +
                ", ErrorDes='" + ErrorDes + '\'' +
                ", Data=" + Data +
                '}';
    }

    public int getErrorId() {
        return ErrorId;
    }

    public void setErrorId(int ErrorId) {
        this.ErrorId = ErrorId;
    }

    public String getErrorDes() {
        return ErrorDes;
    }

    public void setErrorDes(String ErrorDes) {
        this.ErrorDes = ErrorDes;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * token : 30d7767f7d5956f56049a3292e66d4e1
         * uid : 833
         */

        private String token;
        private String uid;

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", uid='" + uid + '\'' +
                    '}';
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
