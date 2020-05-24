package br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request;

import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import lombok.*;

import javax.validation.constraints.NotBlank;

public
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor @Generated
class DocumentRequest {
    @NotBlank(message = ResponseMessage.DATA_IS_REQUIRED)
    private String data;
}
