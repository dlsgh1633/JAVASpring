package com.example.demo.model;

public class JsonDto {

	private String result;
    private String message; 
    private Object data;

    public static JsonDto success(Object data) {
        return new JsonDto("success", null, data);
    }

    public static JsonDto success(Object data, String value) {
        return new JsonDto("success", value, data);
    }

    public static JsonDto fail(String message) {
        return new JsonDto("fail", message, null);
    }

    private JsonDto(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data; 
    }

    public JsonDto() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
    }

}
