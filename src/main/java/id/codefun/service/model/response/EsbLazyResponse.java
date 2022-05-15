package id.codefun.service.model.response;

import id.codefun.common.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsbLazyResponse extends BaseResponse {

    private String body;
    private Integer httpStatus;

}