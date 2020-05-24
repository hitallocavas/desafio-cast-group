package br.ufpe.cin.hcs3.documentmanager.unit.builders;

import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;

public class DocumentBuilder {

    public static Document buildDocumentLeft(){
        return Document.builder()
                .id(1L)
                .leftDoc("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static Document buildDocumentRight(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static Document buildDocumentSameSizeAndContent(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .leftDoc("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static Document buildDocumentSameSizeDiferentContent(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .leftDoc("NzQgNjUgNzMgNzU=")
                .build();
    }

    public static Document buildDocumentDifferentSize(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .leftDoc("NzQgNjUgNzMgNzUgNzcgNTQgNjU=")
                .build();
    }

}
