package br.ufpe.cin.hcs3.documentmanager.v1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_document")
public @Data @NoArgsConstructor @AllArgsConstructor @Builder
class Document {

    @Id
    private Long id;
    private String leftDoc;
    private String rightDoc;

}
