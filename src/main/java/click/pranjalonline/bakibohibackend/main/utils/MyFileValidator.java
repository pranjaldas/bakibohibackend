package click.pranjalonline.bakibohibackend.main.utils;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
@Service
public class MyFileValidator implements Validator {
    public static final String PNG_MIME_TYPE="image/png";
    public static final long TEN_MB_IN_BYTES = 104857;
    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile file =(MultipartFile) target;
        if(file.isEmpty()){
            errors.rejectValue("file", "upload.file.required");
        }
        else if(!PNG_MIME_TYPE.equalsIgnoreCase(file.getContentType())){
            errors.rejectValue("file", "upload.invalid.file.type");
        }

        else if(file.getSize() > TEN_MB_IN_BYTES){
            errors.rejectValue("file", "upload.exceeded.file.size");
        }

    }
}
