package dmm.ydjm.service;

import dmm.ydjm.model.GeneralResponse;
import dmm.ydjm.model.PostFileRequest;
import dmm.ydjm.repository.PostBody;
import dmm.ydjm.repository.PostBodyRepository;
import dmm.ydjm.repository.PostFile;
import dmm.ydjm.repository.PostFileRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

@Service
public class FileService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private Environment env;

    @Autowired
    private PostBodyRepository postBodyRepository;

    @Autowired
    private PostFileRepository postFileRepository;

    @Value("${app.file.path}")
    private String absolutePath = "";

    public PostFileRequest reloadFile(String fileName, String path) {
        PostFileRequest postFileRequest = new PostFileRequest();
        String base64 = getBase64String(path + "\\" + fileName);
        if (base64.isEmpty()) {
            return  null;
        }
        postFileRequest.setFileBody(base64);
        postFileRequest.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
        return postFileRequest;
    }

    public boolean saveFile(PostFileRequest postFileRequest, PostBody postBody) {
        PostFile postFile = new PostFile();
        postFile.setFileType(postFileRequest.getFileType().toLowerCase());
        postFile.setFilePath(getPathName().toLowerCase());
        postFile.setPostItem(postBody);
        postFile.setFileName("wait");
        postFileRepository.save(postFile);

        if (!(postFile.getFileId() > 0)) {
            return false;
        }

        postFile.setFileName(getFileName(postFile.getFileId(), postFile.getFileType()).toLowerCase());

        saveBase64File(postFileRequest.getFileBody(), postFile.getFilePath() + "\\"+ postFile.getFileName());

        postFileRepository.save(postFile);

        return true;
    }

    public GeneralResponse savePostMultipartFile(MultipartFile file, Integer postId) throws IOException {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setSuccess(false);

        PostBody postBody = postBodyRepository.getOne(postId);

        PostFile postFile = new PostFile();
        postFile.setFileType(file.getOriginalFilename().toLowerCase(Locale.ROOT).substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        postFile.setFilePath(getPathName().toLowerCase());
        postFile.setPostItem(postBody);
        postFile.setFileName("wait");
        postFileRepository.save(postFile);

        if (!(postFile.getFileId() > 0)) {
            return generalResponse;
        }
        postFile.setFileName(getFileName(postFile.getFileId(), postFile.getFileType()).toLowerCase());
        File newFile = new File(absolutePath + postFile.getFilePath() + "\\"+ postFile.getFileName());
        file.transferTo(newFile);

        postFileRepository.save(postFile);

        generalResponse.setSuccess(true);
        return generalResponse;
    }

    private String getPathName() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("yyyyMM");
        String month = dateFormat.format(date);

        ensurePathExist(month);

        return month;
    }

    public String getFileName(int fileId, String fileType) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);

        return day + "_" + fileId + "." + fileType;
    }

    private void ensurePathExist(String pathName) {
        File directory = new File(absolutePath + pathName);
        if (!directory.exists()) {
            boolean suc = directory.mkdir();
            logger.info("Path created -- " + absolutePath + pathName);
            if (!suc) {
                logger.info("Path created failed -- " + absolutePath + pathName);
            }
        }
    }

    private boolean saveBinaryFile(byte[] data, String fileName) {
        try {
            logger.info("start save :" + absolutePath + fileName);
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(absolutePath + fileName));
            logger.info("save :" + absolutePath + fileName);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("save file failed", e);
            return false;
        }

        return true;
    }

    public String getBase64String(String fileName) {
        String res = "";
        try {
            File file = new File(absolutePath + fileName);
            byte[] fileData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(fileData);
            dis.close();

            res = Base64.getEncoder().encodeToString(fileData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("load file failed - " + fileName);
        }
        return res;
    }

    public String saveBase64File(String base64String, String filePathName) {
        byte[] decodedDetail = Base64.getDecoder().decode(base64String);
        logger.info("saveBase64File -- " + filePathName);
        saveBinaryFile(decodedDetail, filePathName);
        return filePathName;
    }

    public void deleteOneFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
