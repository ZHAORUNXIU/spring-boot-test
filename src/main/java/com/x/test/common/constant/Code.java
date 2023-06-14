package com.x.test.common.constant;

public interface Code {

    /**
     * Success
     */
    int SUCCESS = 200;

    /**
     * Forbidden
     */
    int FORBIDDEN = 403;

    /**
     * Resource Not Found
     */
    int NOT_FOUND = 404;

    /**
     * System Error
     */
    int SYSTEM_ERROR = 500;

    /**
     * Invalid Request
     */
    int ILLEGAL_REQUEST = 501;

    /**
     * Invalid Parameter
     */
    int ILLEGAL_PARAM = 502;

    /**
     * Request Exceeds Size Limit
     */
    int EXCEED_SIZE = 503;

    /**
     * Missing Parameter
     */
    int PARAM_MISSING = 504;

    /**
     * Frequent Invocation
     */
    int FREQUENT_INVOKE = 510;

    /**
     * Blacklisted User
     */
    int BLACKLIST = 511;

    /**
     * Anonymous Access Rejected
     */
    int REJECT_ANONYMOUS = 512;

}
