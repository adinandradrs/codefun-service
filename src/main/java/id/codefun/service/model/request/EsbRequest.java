package id.codefun.service.model.request;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import id.codefun.common.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsbRequest extends BaseRequest {

    private HttpEntity<?> payload;
    private boolean isPlain;
    private LinkedMultiValueMap<String, String> params;

}

