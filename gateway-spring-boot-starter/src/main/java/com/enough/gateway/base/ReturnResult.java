package com.enough.gateway.base;

import java.io.Serializable;

/**
 * @program: learn-redis
 * @description: rest层统一返回结果
 * @author: lidong
 * @create: 2019/11/15
 */
public class ReturnResult<T> implements Serializable {

    private static final long serialVersionUID = -1393104860022774284L;

    private String msg;
    private Status status;
    private boolean successed;
    private T data;
    private String newResource;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public String getNewResource() {
        return newResource;
    }

    public void setNewResource(String newResource) {
        this.newResource = newResource;
    }

    @Override
    public String toString() {
        return "ReturnResult{" + "msg='" + msg + '\'' + ", status=" + status + ", successed=" + successed + ", data=" + data + ", newResource='" + newResource
                + '\'' + '}';
    }

    public enum Status {
        SUCCESS, FAULT;
    }

    public static <T> ResultBuilder <T> success() {
        ResultBuilder <T> builder = new ResultBuilder();
        builder.successed(true);
        return builder;
    }

    public static <T> ResultBuilder <T> failed() {
        ResultBuilder <T> builder = new ResultBuilder();
        builder.successed(false);
        return builder;
    }

    public static class ResultBuilder<T> {
        private String msg;
        private boolean successed;
        private T data;
        private String newResource;

        public ResultBuilder() {

        }

        public ResultBuilder <T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResultBuilder <T> successed(boolean successed) {
            this.successed = successed;
            return this;
        }

        public ResultBuilder <T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultBuilder <T> newResource(String newResource) {
            this.newResource = newResource;
            return this;
        }

        public ReturnResult <T> build() {
            ReturnResult <T> returnResult = new ReturnResult <>();
            returnResult.setMsg(this.msg);
            returnResult.setStatus(this.successed ? Status.SUCCESS : Status.FAULT);
            returnResult.setData(this.data);
            returnResult.setSuccessed(this.successed);
            returnResult.setNewResource(this.newResource);
            return returnResult;
        }
    }

    public void test(String a,String b,String c){
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println("c=" + c);
    }


}
