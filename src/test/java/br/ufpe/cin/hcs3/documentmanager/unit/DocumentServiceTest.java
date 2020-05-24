package br.ufpe.cin.hcs3.documentmanager.unit;

import br.ufpe.cin.hcs3.documentmanager.unit.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import br.ufpe.cin.hcs3.documentmanager.v1.repository.DocumentRepository;
import br.ufpe.cin.hcs3.documentmanager.v1.service.DocumentService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    public void shouldSaveLeftDocumentWithSuccess() {
        //Given
        var leftDocument = DocumentBuilder.buildDocumentLeft();

        //When
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentLeft());
        Document doc = this.documentService.saveDocument(leftDocument);

        //Then
        assertNotNull(doc.getLeftDoc());
        assertEquals(doc.getId(), leftDocument.getId());
    }

    @Test
    public void shouldSaveRightDocumentWithSuccess() {
        //Given
        var documentRight = DocumentBuilder.buildDocumentRight();

        //When
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentLeft());
        Document doc = this.documentService.saveDocument(documentRight);

        //Then
        assertNotNull(doc.getLeftDoc());
        assertEquals(doc.getId(), documentRight.getId());
    }

    @Test
    public void shouldHaveDifferentSize() {
        //Given
        var documentId = 1L;
        var differentSizeDocument = DocumentBuilder.buildDocumentDifferentSize();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(differentSizeDocument));
        String message = documentService.evaluate(documentId);

        //Then
        assertEquals(message, String.format(ResponseMessage.DOCUMENTS_WITH_DIFFERENT_SIZE, documentId));
    }

    @Test
    public void shouldHaveSameSizeDifferentContent() {
        //Given
        var documentId = 1L;
        var differentSizeAndContentDocument = DocumentBuilder.buildDocumentSameSizeDiferentContent();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(differentSizeAndContentDocument));
        String message = documentService.evaluate(documentId);

        //Then
        assertEquals(message, "10");
    }

    @Test
    public void shouldHaveSameSizeAndContent() {
        //Given
        var documentId = 1L;
        var sameSizeAndContentDocument = DocumentBuilder.buildDocumentSameSizeAndContent();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(sameSizeAndContentDocument));
        String message = documentService.evaluate(documentId);

        //Then
        assertEquals(message, String.format(ResponseMessage.DOCUMENTS_WITH_SAME_SIZE, documentId));
    }

}
