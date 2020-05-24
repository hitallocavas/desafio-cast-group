package br.ufpe.cin.hcs3.documentmanager.unit_test;

import br.ufpe.cin.hcs3.documentmanager.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import br.ufpe.cin.hcs3.documentmanager.v1.repository.DocumentRepository;
import br.ufpe.cin.hcs3.documentmanager.v1.service.DocumentService;

import static org.junit.jupiter.api.Assertions.*;

import br.ufpe.cin.hcs3.documentmanager.v1.util.DocumentUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    public void shouldSaveLeftDocumentIntoExistentDocumentWithSuccess() {
        //Given
        var leftDocument = DocumentBuilder.buildDocumentLeft();

        //When
        Mockito.when(documentRepository.findById(1L)).thenReturn(Optional.of(DocumentBuilder.buildDocumentRight()));
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentSameSizeAndContent());
        Document doc = this.documentService.saveDocument(leftDocument);

        //Then
        assertNotNull(doc.getLeftDoc());
        assertEquals(doc.getId(), leftDocument.getId());
    }

    @Test
    public void shouldSaveLeftDocumentIntoNonExistentDocumentWithSuccess() {
        //Given
        var leftDocument = DocumentBuilder.buildDocumentLeft();

        //When
        Mockito.when(documentRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentLeft());
        Document doc = this.documentService.saveDocument(leftDocument);

        //Then
        assertNotNull(doc.getLeftDoc());
        assertEquals(doc.getId(), leftDocument.getId());
    }

    @Test
    public void shouldSaveRightDocumentIntoExistentDocumentWithSuccess() {
        //Given
        var documentRight = DocumentBuilder.buildDocumentRight();

        //When
        Mockito.when(documentRepository.findById(1L)).thenReturn(Optional.of(DocumentBuilder.buildDocumentLeft()));
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentSameSizeAndContent());
        Document doc = this.documentService.saveDocument(documentRight);

        //Then
        assertNotNull(doc.getRightDoc());
        assertEquals(doc.getId(), documentRight.getId());
    }

    @Test
    public void shouldSaveRightDocumentIntoNonExistentDocumentWithSuccess() {
        //Given
        var documentRight = DocumentBuilder.buildDocumentRight();

        //When
        Mockito.when(documentRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(documentRepository.save(any(Document.class))).
                thenReturn(DocumentBuilder.buildDocumentRight());
        Document doc = this.documentService.saveDocument(documentRight);

        //Then
        assertNotNull(doc.getRightDoc());
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
        var differentSizeAndContentDocument = DocumentBuilder.buildDocumentSameSizeDifferentContent();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(differentSizeAndContentDocument));
        String message = documentService.evaluate(documentId);

        //Then
        assertEquals( "10", message);
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

    @Test()
    public void shouldHaveEvaluateErrorWhenLeftContentIsNull() {
        //Given
        var documentId = 1L;
        var nullContentDocument = DocumentBuilder.buildDocumentWithNullLeftContent();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(nullContentDocument));
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> {
            String message = documentService.evaluate(documentId);
        });

        //Then
        String expected = DocumentUtil.getNullPositionsMessage(nullContentDocument);
        String generated = responseStatusException.getReason();

        assertEquals(expected, generated);
    }

    @Test()
    public void shouldHaveEvaluateErrorWhenRightContentIsNull() {
        //Given
        var documentId = 1L;
        var nullContentDocument = DocumentBuilder.buildDocumentWithNullRightContent();

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.of(nullContentDocument));
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> {
            String message = documentService.evaluate(documentId);
        });

        //Then
        String expected = DocumentUtil.getNullPositionsMessage(nullContentDocument);
        String generated = responseStatusException.getReason();

        assertEquals(expected, generated);
    }

    @Test()
    public void shouldHaveEvaluateErrorWhenIdNotFound() {
        //Given
        var documentId = 1L;

        //When
        Mockito.when(documentRepository.findById(documentId)).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> {
            String message = documentService.evaluate(documentId);
        });

        //Then
        String expected = ResponseMessage.DOCUMENT_NOT_FOUND;
        String generated = responseStatusException.getReason();

        assertEquals(expected, generated);
    }

}
