package click.pranjalonline.bakibohibackend.main.controller;

import click.pranjalonline.bakibohibackend.main.payload.ShopPayload;
import click.pranjalonline.bakibohibackend.main.payload.ShopResponse;
import click.pranjalonline.bakibohibackend.main.utils.FileUploadService;
import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import click.pranjalonline.bakibohibackend.persistance.entity.User;
import click.pranjalonline.bakibohibackend.persistance.service.ShopService;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    @PostMapping(value= "/create",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createShop(ShopPayload shopPayload){

        LOGGER.info(shopPayload.getPhoto().getName());
        String fileName= new StringBuilder()
                .append("my_file_")
                .append(System.currentTimeMillis())
                .append(".jpg").toString();
        LOGGER.info(fileName);
        try {
            fileUploadService.save(shopPayload.getPhoto(),fileName);
            Shop shop= modelMapper.map(shopPayload,Shop.class);
            shop.setImage_path(fileName);
            shop.setShopkeeper(userService.findById(1L));
            shopService.createShop(shop);
            return ResponseEntity.ok(shopPayload.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
