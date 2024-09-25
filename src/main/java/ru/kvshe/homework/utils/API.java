package ru.kvshe.homework.utils;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class API {
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Пользователи не найдены", responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    public @interface NotFoundResponse {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            description = "Внутренняя ошибка",
            responseCode = "500",
            content = @Content(schema = @Schema())
    )
    public @interface InternalServerError {

    }
}
