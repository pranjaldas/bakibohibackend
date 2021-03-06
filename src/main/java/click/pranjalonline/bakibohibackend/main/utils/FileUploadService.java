package click.pranjalonline.bakibohibackend.main.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileUploadService {
    public void init();
    public void save(MultipartFile file,String fileName);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}
