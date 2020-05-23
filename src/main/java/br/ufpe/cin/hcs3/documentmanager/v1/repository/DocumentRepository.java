package br.ufpe.cin.hcs3.documentmanager.v1.repository;

import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
