package xyl.me.util;

/**
 * @author pippo 统一异常
 */
public class HAException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object data;
	private String code;
	private String message;

	public HAException(String code, String message, Throwable cause) {
		super(cause);
		this.code = code;
		this.message = message;
	}

	public HAException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public HAException(Object data, String code, String message) {
		super();
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	@Override
    public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
