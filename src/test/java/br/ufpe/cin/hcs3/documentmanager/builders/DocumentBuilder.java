package br.ufpe.cin.hcs3.documentmanager.builders;

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

    public static Document buildDocumentSameSizeAndContentDiffId(){
        return Document.builder()
                .id(4L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .leftDoc("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static Document buildDocumentSameSizeDifferentContent(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .leftDoc("NzQgNjUgNzMgNzU=")
                .build();
    }

    public static Document buildDocumentWithNullLeftContent(){
        return Document.builder()
                .id(1L)
                .rightDoc("NzQgNjUgNzMgNzQ=")
                .build();
    }

    public static Document buildDocumentWithNullRightContent(){
        return Document.builder()
                .id(1L)
                .leftDoc("NzQgNjUgNzMgNzQ=")
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
