/**
 * 嚴肅聲明：
 * 開源版本請務必保留此註釋頭資訊，若刪除我方將保留所有法律責任追究！
 * 本軟體已申請軟體著作權，受國家版權局智慧財產權以及國家計算機軟體著作權保護！
 * 可正常分享和學習原始碼，不得用於違法犯罪活動，違者必究！
 * Copyright (c) 2019-2021 十三 all rights reserved.
 * 版權所有，侵權必究！
 */
package ltd.newbee.mall.config;

import ltd.newbee.mall.entity.AdminUserToken;
import ltd.newbee.mall.entity.MallUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {

        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> swaggerParams = new ArrayList<Parameter>();
        tokenParam.name("token").description("使用者認證資訊")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket參數非必填，傳空也可以
        swaggerParams.add(tokenParam.build());    //根據每個方法名也知道目前方法在設定什麼參數

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(MallUser.class, AdminUserToken.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ltd.newbee.mall.api"))// 修改爲自己的 controller 包路徑
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(swaggerParams);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("新蜂商城介面文件")
                .description("swagger介面文件")
                .version("2.0")
                .build();
    }
}