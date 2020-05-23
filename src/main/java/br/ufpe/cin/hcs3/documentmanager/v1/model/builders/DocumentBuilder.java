package br.ufpe.cin.hcs3.documentmanager.v1.model.builders;

import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;

public class DocumentBuilder {
    public static Document mountLeftDocument(Long id, String data){
        return Document.builder()
                .id(id)
                .leftDoc(data)
                .build();
    }

    public static Document mountRightDocument(Long id, String data){
        return Document.builder()
                .id(id)
                .rightDoc(data)
                .build();
    }
}
