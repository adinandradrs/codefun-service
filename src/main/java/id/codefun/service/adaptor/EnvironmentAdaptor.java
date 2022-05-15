package id.codefun.service.adaptor;

import id.codefun.common.model.request.BaseRequest;
import id.codefun.common.model.response.BaseResponse;

public interface EnvironmentAdaptor <I extends BaseRequest, O extends BaseResponse> {

    public O execute(I request);

}