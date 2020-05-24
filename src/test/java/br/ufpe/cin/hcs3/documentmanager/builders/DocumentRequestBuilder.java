package br.ufpe.cin.hcs3.documentmanager.builders;

import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DocumentRequestBuilder {

    public static DocumentRequest validDocumentRequest(){
        return DocumentRequest.builder()
                .data("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static DocumentRequest invalidDocumentRequest(){
        return DocumentRequest.builder()
                .data("")
                .build();
    }

}
