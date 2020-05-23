package br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message;

public class ResponseMessage {
    public static final String DOCUMENTS_WITH_SAME_SIZE = "Documentos <ID> idênticos";
    public static final String DOCUMENTS_WITH_DIFFERENT_SIZE = "Documentos <ID> com tamanhos diferentes";
    public static final String DOCUMENT_NOT_FOUND = "Documento não encontrado";
    public static final String LEFT_DOCUMENT_IS_NULL = "Documento Left para o Id informado não foi enviado";
    public static final String RIGHT_DOCUMENT_IS_NULL = "Documento Right para o Id informado não foi enviado";

    public static String getMessageReplaceId(String message, Long id){
        return message.replace("<ID>", id.toString());
    }
}
