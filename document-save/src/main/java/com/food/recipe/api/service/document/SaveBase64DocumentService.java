package com.food.recipe.api.service.document;

import com.food.recipe.api.DocumentConstant;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.DocumentEntity;
import com.food.recipe.api.exception.BusinessException;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.request.SaveDocumentBase64Request;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SaveBase64DocumentService implements SimpleTask<SaveDocumentBase64Request, List<SaveDocumentResponse>> {

    private final DocumentRepository documentRepository;

    private final Base64FileControlService base64FileControlService;

    private final MessageSource messageSource;


    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(5);

    /**
     * This executable service works with multiple data, create executor service for parallel document save operations.
     * @param SaveDocumentBase64Request the function argument
     * @return Boolean
     */
//    @Override
//    public Boolean apply(SaveDocumentBase64Request request) {
//
//        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        log.info("ExecutorService create new thread");
//        AtomicInteger sizeOfFiles = new AtomicInteger();
//
//        for (Base64Files base64File : request.getFilesList()) {
//            executorService.execute(() -> {
//                try {
//                    log.info("Base64File file name: {}", base64File.getFileName());
//                    byte[] fileBytes = base64File.decodeBase64Data();
//                    final FileOutputStream fileOutputStream = new FileOutputStream(base64File.getFileName());
//                    fileOutputStream.write(fileBytes);
//                    fileOutputStream.close();
//
//                    sizeOfFiles.addAndGet(base64FileControlService.apply(base64File));
//
//                    // Total file bytes size firstly, convert megabyte then total size of files lower than 5MB. Program will save file on DB.
//                    if (sizeOfFiles.intValue() <= 5 * 1000000L) {
//                        final DocumentEntity document = DocumentEntity.builder()
//                                .userId(request.getUserId())
//                                .username(request.getUsername())
//                                .fileType(base64File.getFileType())
//                                .fileName(base64File.getFileName())
//                                .data(fileBytes)
//                                .time(LocalDateTime.now())
//                                .build();
//                        documentRepository.save(document);
//                        log.info("SaveBase64DocumentService is successfully");
//                    }
//                    // TODO TEST
//                    // If total files size greater than 5MB, this else block will throw exception for user.
//                    else {
//                        throw new FileSizeLimitExceededException(messageSource.getMessage(DocumentConstant.Exception.FILE_SIZE_LIMIT, null, Locale.ENGLISH),
//                                sizeOfFiles.intValue(), MAX_FILE_SIZE.toBytes());
//                    }
//
//                } catch (IOException e) {
//                    log.error("SaveBase64DocumentService: {}" , e.getMessage());
//                }
//            });
//        }
//        executorService.shutdown();
//
//        return Boolean.TRUE;
//    }


    @Override
    public List<SaveDocumentResponse> apply(SaveDocumentBase64Request saveDocumentBase64Request) {
        AtomicInteger sizeOfFiles = new AtomicInteger();
        var virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        List<SaveDocumentResponse> documentResponseList = new ArrayList<>();

        for (Base64Files base64File : saveDocumentBase64Request.getFilesList()) {
            virtualThreadExecutor.execute(() -> {
                try {
                    log.info("Base64File file name: {}", base64File.getFileName());
                    byte[] fileBytes = base64File.decodeBase64Data();
                    final FileOutputStream fileOutputStream = new FileOutputStream(base64File.getFileName());
                    fileOutputStream.write(fileBytes);
                    fileOutputStream.close();

                    sizeOfFiles.addAndGet(base64FileControlService.apply(base64File));

                    // Total file bytes size firstly, convert megabyte then total size of files lower than 5MB. Program will save file on DB.
                    if (sizeOfFiles.intValue() <= 5 * 1000000L) {
                        // final UserDetails userInfo = jwtUserDetailsService.loadUserByUsername(saveDocumentBase64Request.getUsername());
                        final DocumentEntity document = DocumentEntity.builder()
                                .userId(saveDocumentBase64Request.getUserId())
                                .username(saveDocumentBase64Request.getUsername())
                                .fileType(base64File.getFileType())
                                .fileName(base64File.getFileName())
                                .data(fileBytes)
                                .time(LocalDateTime.now())
                                .build();
                        documentRepository.save(document);
                        log.info("SaveBase64DocumentService is successfully");
                        SaveDocumentResponse saveDocumentResponse = new SaveDocumentResponse();
                        saveDocumentResponse.setDocumentId(document.getId());
                        saveDocumentResponse.setUserId(document.getUserId());
                        saveDocumentResponse.setFileName(document.getFileName());
                        documentResponseList.add(saveDocumentResponse);
                    }
                    // TODO TEST
                    // If total files size greater than 5MB, this else block will throw exception for user.
                    else {
                        throw new FileSizeLimitExceededException(messageSource.getMessage(DocumentConstant.Exception.FILE_SIZE_LIMIT, null, Locale.ENGLISH),
                                sizeOfFiles.intValue(), MAX_FILE_SIZE.toBytes());
                    }

                } catch (IOException e) {
                    log.error("SaveBase64DocumentService: {}", e.getMessage());
                }
            });
        }

        // Java Virtual Threads Executor'ünü kapatın (Java 17+ kullanılıyorsa bu gerekli değil)
        if (virtualThreadExecutor instanceof AutoCloseable) {
            try {
                ((AutoCloseable) virtualThreadExecutor).close();
            } catch (Exception e) {
                log.error("Error closing Virtual Threads Executor: {}", e.getMessage());
                throw new BusinessException( "İşlemlerinizi şu vakit gerçekleştiremiyoruz.");
            }
        }

        return documentResponseList;
    }

}
