package YapeCoupons;

import YapeCoupons.controller.Coupon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@SpringBootApplication
@Configuration
public class Application implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File uploads = new File(Coupon.UPLOAD_DIRECTORY);
        uploads.mkdir();
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+Coupon.UPLOAD_DIRECTORY);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}