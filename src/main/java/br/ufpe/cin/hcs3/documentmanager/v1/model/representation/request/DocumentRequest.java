package br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request;

import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ValidationMessages;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

public @Getter @Setter
class DocumentRequest {
    @NotBlank(message = ValidationMessages.DATA_IS_REQUIRED)
    private String data;
}
