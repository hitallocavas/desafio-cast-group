package br.ufpe.cin.hcs3.documentmanager.v1.model.builders;

import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;

import java.util.Optional;

public class DocumentBuilder {
    public static Document mountLeftDocument(Long id, String data, Optional<Document> optionalDocument){
        Document document = Document.builder()
                .id(id)
                .leftDoc(data)
                .build();
        optionalDocument.ifPresent(doc -> document.setRightDoc(doc.getRightDoc()));
        return document;
    }

    public static Document mountRightDocument(Long id, String data, Optional<Document> optionalDocument){
        Document document = Document.builder()
                .id(id)
                .rightDoc(data)
                .build();
        optionalDocument.ifPresent(doc -> document.setLeftDoc(doc.getLeftDoc()));
        return document;
    }
}
