package id.codefun.service.model.response;

import java.io.Serializable;
import id.codefun.common.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsbLazyResponse extends BaseResponse implements Serializable {

    private String body;
    private Integer httpStatus;

}