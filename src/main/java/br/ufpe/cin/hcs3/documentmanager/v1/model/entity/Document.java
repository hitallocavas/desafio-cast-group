package br.ufpe.cin.hcs3.documentmanager.v1.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_document")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class Document {

    @Id
    private Long id;
    private String leftDoc;
    private String rightDoc;

}
