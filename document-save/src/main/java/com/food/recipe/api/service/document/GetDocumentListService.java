package com.food.recipe.api.service.document;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.DocumentEntity;
import com.food.recipe.api.model.document.DocumentInfoDTO;
import com.food.recipe.api.model.document.response.DocumentListResponse;
import com.food.recipe.api.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class GetDocumentListService implements SimpleTask<String, DocumentListResponse> {

    private final DocumentRepository documentRepository;

    /**
     * Get user documents information, response to documents list.
     * @param username the first function argument
     * @return DocumentListResponse
     */
    @Override
    public DocumentListResponse apply(String username) {

        final List<DocumentEntity> userDocuments = documentRepository.findByUsernameDocuments(username);
        final List<DocumentInfoDTO> base64Files = new ArrayList<>();
        if (Objects.nonNull(userDocuments)) {

            userDocuments.forEach(t -> {
                final DocumentInfoDTO infoDTO = DocumentInfoDTO.builder()
                        .userId(t.getUserId())
                        .username(t.getUsername())
                        .documentId(t.getId())
                        .data(t.getData())
                        .fileName(t.getFileName())
                        .fileType(t.getFileType())
                        .time(t.getTime())
                        .build();
                base64Files.add(infoDTO);
            });
        }
        return DocumentListResponse.builder().documents(base64Files).build();
    }
}
