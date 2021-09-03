package org.lql;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Title: ServerApplication <br>
 * ProjectName: coldServer <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/3 11:43 <br>
 */
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(ServerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
