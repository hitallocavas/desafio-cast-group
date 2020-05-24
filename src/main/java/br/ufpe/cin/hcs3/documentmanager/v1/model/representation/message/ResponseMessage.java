package br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMessage {
    public static final String DOCUMENTS_WITH_SAME_SIZE = "Documentos %d idênticos";
    public static final String DOCUMENTS_WITH_DIFFERENT_SIZE = "Documentos %d com tamanhos diferentes";
    public static final String DOCUMENT_NOT_FOUND = "Documento não encontrado";
    public static final String LEFT_DOCUMENT_IS_NULL = "Documento Left para o Id informado não foi enviado";
    public static final String RIGHT_DOCUMENT_IS_NULL = "Documento Right para o Id informado não foi enviado";
    public static final String DATA_IS_REQUIRED = "Documento no formato BASE 64 é obrigatório";
}
