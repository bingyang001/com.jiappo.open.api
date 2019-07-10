package com.jiappo.open.api.domain.service;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 18:05
 **/
public class ErrorConstant {
    private ErrorConstant() {

    }

    public static class SignError {
        private SignError() {

        }

        public static final int DATA_ERROR = 40003;
        public static final String DATA_ERROR_DOC = "data error";

        public static final int SIGN_ERROR = 40001;
        public static final String SIGN_ERROR_DOC = "sign can not null";

        public static final int SECRET_KEY_ERROR = 40002;
        public static final String SECRET_KEY_ERROR_DOC = "secret key can not null";

        public static final int NO_ACTIVATE_ERROR = 40004;
        public static final String NO_ACTIVATE_ERROR_DOC = "api no activate";

        public static final int ALREADY_EXPIRED_ERROR = 40005;
        public static final String AUTHORIZE_ALREADY_EXPIRED_DOC = "api authorize already expired";

        public static final int SECRET_KEY_NO_MATCH_ERROR = 40006;
        public static final String SECRET_KEY_NO_MATCH_ERROR_DOC = "SecretKey error";

        public static final int SIGN_VERIFIED_FAILED = 40007;
        public static final String SIGN_VERIFIED_FAILED_DOC = "sign verified failed";

        public static final int SIGN_STRING_NULL=40008;
        public static final String SIGN_STRING_NULL_DOC="sign value can not null";
    }
}
