package org.example.level2bookmanagementsystem.swagger.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.springdoc.core.customizers.OperationCustomizer
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.HandlerMethod
import java.time.ZonedDateTime


@Configuration
class SwaggerConfig {

    // OpenAPI 상단 설정
    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Level 2 Book Management System API")
                    .version("1.0.0")
                    .description("도서 관리 시스템 API 문서입니다.")
                    .termsOfService("http://localhost:8080")
                    .contact(Contact().name("kwang-sub").email("choikwangsub@gmail.com"))
            )
            .components(
                Components().addSecuritySchemes(
                    "bearerAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            ).addSecurityItem(SecurityRequirement().addList("bearerAuth"))
    }

    // Swagger UI에서 API 그룹화 먼저 등록할 수록 상단에 노출됨, addOperationCustomizer를 통해 SwaggerApiErrorCodeExample 어노테이션이 붙은 메서드에 대한 에러 코드 예시를 추가함
    @Bean
    fun authApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("auth")
        .pathsToMatch("/api/v1/auth/**")
        .packagesToScan("org.example.level2bookmanagementsystem.security.controller")
        .addOperationCustomizer(customize())
        .build()

    @Bean
    fun bookApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("book")
        .pathsToMatch("/api/v1/books/**")
        .packagesToScan("org.example.level2bookmanagementsystem.swagger.controller")
        .addOperationCustomizer(customize())
        .build()

    @Bean
    fun customize(): OperationCustomizer {
        return OperationCustomizer { operation: Operation, handlerMethod: HandlerMethod ->
            val swaggerApiErrorCodeExample = handlerMethod.getMethodAnnotation(SwaggerApiErrorCodeExample::class.java)

            if (swaggerApiErrorCodeExample != null) {
                generateErrorCodeResponseExample(operation, swaggerApiErrorCodeExample.value)
            }
            operation
        }
    }

    private fun generateErrorCodeResponseExample(
        operation: Operation, errorCodes: Array<ErrorCode>
    ) {
        errorCodes.groupBy { it.httpCode.value().toString() }
            .forEach { (status, codes) ->
                val mediaType = MediaType().apply {
                    codes.forEach { addExamples(it.message, getSwaggerExample(it)) }
                }

                operation.responses.addApiResponse(status, ApiResponse().apply {
                    content = Content().addMediaType("application/json", mediaType)
                })
            }
    }

    private fun getSwaggerExample(errorCode: ErrorCode): Example {
        val errorResponse = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
            status = errorCode.httpCode.value(),
            path = "",
            timestamp = ZonedDateTime.now(),
        )

        return Example().apply {
            description = errorCode.message
            value = errorResponse
        }
    }
}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SwaggerApiErrorCodeExample(
    val value: Array<ErrorCode>
)

