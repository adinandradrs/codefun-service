package id.codefun.service.model.request;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsbRequest {

    private HttpEntity payload;
    private Boolean isPlain;
    private LinkedMultiValueMap<String, String> params;

}

