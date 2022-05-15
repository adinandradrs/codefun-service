package id.codefun.service.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String DOCS_BODY_PAYLOAD = "Body payload";
	public static final String DOCS_YOUR_REGISTERED_API_KEY = "Your registered API Key";
	public static final String DOCS_YOUR_SESSION_ID = "Your Session ID after calling the validate operation";

    public static final String REQ_HEADER_USERID = "userId";
    public static final String REQ_HEADER_SESSION_ID = "sessionId";
    public static final String REQ_HEADER_APIKEY = "apiKey";

    public static final String SUCCESS_MSG_OK = "OK";
    public static final String SUCCESS_MSG_SUBMIT = "Data submitted successfully";
    public static final String SUCCESS_MSG_DATA_FOUND = "Here is your data";

    public static final String ERR_MSG_DATA_NOT_FOUND = "Data is not found";
    public static final String ERR_MSG_SOMETHING_WRONG = "Something went wrong";
    public static final String ERR_MSG_UNAUTHORIZED = "Unauthorized access";
    public static final String ERR_MSG_INVALID_SESSION = "Invalid session";
    public static final String ERR_MSG_SUBMITTED = "Data failed to submit";
    public static final String ERR_MSG_TOKEN_EXPIRED = "Token is expired";

    public enum USER_STATUS {
    	BLOCK(2, "BLOCK"),
    	ACTIVE(1, "ACTIVE"),
        INACTIVE(0, "INACTIVE");

        private Integer value;
        private String msg;
        private static Map<Integer, USER_STATUS> map = new HashMap<>();

        USER_STATUS(Integer value, String msg) {
            this.value = value;
            this.msg = msg;
        }

        static {
            for (USER_STATUS pageType : USER_STATUS.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static USER_STATUS valueOf(int pageType) {
            return map.get(pageType);
        }

        public Integer getValue() {
            return value;
        }

        public String getMsg() {
            return msg;
        }
    }
    
    public enum COMMON_STATUS {
        ACTIVE(1, "ACTIVE"),
        INACTIVE(0, "INACTIVE");

        private Integer value;
        private String msg;
        private static Map<Integer, COMMON_STATUS> map = new HashMap<>();

        COMMON_STATUS(Integer value, String msg) {
            this.value = value;
            this.msg = msg;
        }

        static {
            for (COMMON_STATUS pageType : COMMON_STATUS.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static COMMON_STATUS valueOf(int pageType) {
            return map.get(pageType);
        }

        public Integer getValue() {
            return value;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum FILE_UPLOAD_STATUS {
        PENDING(0, "Pending"),
        UPLOADED(1, "Uploaded"),
        FAILED(2, "Failed"),
        IN_PROGRESS(3, "In Progress"),
        REJECTED(4, "Rejected"),
        COMPLETED(5, "Completed");

        private final Integer value;
        private final String msg;
        private static final Map<Integer, FILE_UPLOAD_STATUS>  map = new HashMap<>();
        private static final Map<Object, Object> options = new HashMap<>();

        FILE_UPLOAD_STATUS(Integer value, String msg) {
            this.value = value;
            this.msg = msg;
        }

        static {
            for (FILE_UPLOAD_STATUS pageType : FILE_UPLOAD_STATUS.values()) {
                map.put(pageType.value, pageType);
                options.put(pageType.value, pageType.msg);
            }
        }

        public static FILE_UPLOAD_STATUS valueOf(int pageType) {
            return map.get(pageType);
        }

        public Integer getValue() {
            return value;
        }

        public String getMsg() {
            return msg;
        }
    }
    
}
