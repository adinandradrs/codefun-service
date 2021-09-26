package id.codefun.service.adaptor;

import id.codefun.common.model.request.BaseRequest;
import id.codefun.common.model.response.BaseResponse;

public interface EnvironmentAdaptor <input extends BaseRequest, output extends BaseResponse> {

    output execute(input request);

}