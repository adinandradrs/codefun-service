package id.codefun.service.adaptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import id.codefun.common.model.request.BaseRequest;
import id.codefun.common.model.response.BaseResponse;
import id.codefun.service.model.request.EsbRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Setter
@Getter
@Log4j2
public abstract class RestAdaptor <input extends BaseRequest, output extends BaseResponse> {

    protected String url;
    protected HttpMethod httpMethod;
    protected Class<output> response;

    private RestTemplate restTemplateHttps;

    protected ResponseEntity getResponse(EsbRequest request) {
        String requestUrl;
        if (ObjectUtils.isNotEmpty(request.getParams()) && !request.getParams().isEmpty()) {
            LinkedMultiValueMap<String, String> params = request.getParams();
            log.info("params = {}", params);
            UriComponents builder = UriComponentsBuilder.fromUriString(url).queryParams(params).build();
            requestUrl = builder.toUriString();
        } else {
            requestUrl = url;
        }
        log.info("requestUrl = {}", requestUrl);
        log.info("requestPayload = {}", JSON.toJSONString(request.getPayload()));
        ResponseEntity responseEntity = (request.getIsPlain()) ?
                this.restTemplateHttps.exchange(requestUrl, httpMethod, request.getPayload(), String.class) :
                this.restTemplateHttps.exchange(requestUrl, httpMethod, request.getPayload(), response);
        log.info("response for {} = {}", url, responseEntity);
        return responseEntity;
    }

    public abstract output execute(input input);
    protected abstract EsbRequest generatePayload(input input);
    
}