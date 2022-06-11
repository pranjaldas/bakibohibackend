package click.pranjalonline.bakibohibackend.main.controller;

import click.pranjalonline.bakibohibackend.main.payload.ErrorDetails;
import click.pranjalonline.bakibohibackend.main.payload.ShopPayload;
import click.pranjalonline.bakibohibackend.main.payload.ShopResponse;
import click.pranjalonline.bakibohibackend.main.utils.Base64ToMultipartFile;
import click.pranjalonline.bakibohibackend.main.utils.FileUploadService;
import click.pranjalonline.bakibohibackend.main.utils.MyFileValidator;
import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import click.pranjalonline.bakibohibackend.persistance.service.ShopService;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shops/")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    MyFileValidator myFileValidator;
    private final Logger LOGGER= LogManager.getLogger(ShopService.class);
    @GetMapping
    public ResponseEntity<List<ShopResponse>> getShops(){
        List<Shop> shops= shopService.findAllShops();
        List<ShopResponse> resposeShops= shops.stream().map(shop->new ShopResponse(shop))
                .collect(Collectors.toList());
        resposeShops.stream().forEach(i->System.out.println(i.getAddress()));

        return ResponseEntity.ok(resposeShops);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Shop>> getShopsNew(){
        List<Shop> shops= shopService.findAllShops();
        LOGGER.error("hii");
        return ResponseEntity.ok(shops);
    }
    @PostMapping(value= "/create")
    public ResponseEntity<String> createShop(@Valid @RequestBody ShopPayload shopPayload,BindingResult bindingResult){
        String fileName= new StringBuilder()
                .append("my_file_")
                .append(System.currentTimeMillis())
                .append(".jpg").toString();
        try {
            validateAndStoreFile(shopPayload,bindingResult);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Shop shop= modelMapper.map(shopPayload,Shop.class);
//        shop.setImage_path(fileName);
//        shop.setShopkeeper(userService.findById(1L));
//        shopService.createShop(shop);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);


    }
    public String validateAndStoreFile(ShopPayload shopPayload,BindingResult bindingResult){
        String fileName= new StringBuilder()
                .append("my_file_")
                .append(System.currentTimeMillis())
                .append(".jpg").toString();
        String dataUir, data;
        dataUir = shopPayload.getEncodedPhoto().split(",")[0];
        data = shopPayload.getEncodedPhoto().split(",")[1];

        MultipartFile multipartFile = null;
        try {
            multipartFile = new Base64ToMultipartFile(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        shopPayload.setFile(multipartFile);



        // String mimeType =URLConnection.guessContentTypeFromStream(new BufferedInputStream(new ByteArrayInputStream(multipartFile.getBytes())));
        LOGGER.info("The file type is: "+multipartFile.getContentType()+ multipartFile.getOriginalFilename());
        // fileUploadService.save(multipartFile,fileName);

        myFileValidator.validate(multipartFile,bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorDetails<String> errorDetails= new ErrorDetails(new Date(),
                    HttpStatus.BAD_REQUEST.toString(),
                    bindingResult.getAllErrors().toString(),
                    bindingResult.getAllErrors().toString());

        }else{
            LOGGER.info("NO ERROR OCCURRED");
        }




        return "path";
    }


}
