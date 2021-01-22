package com.englishapp.demoen.entity.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
public class TextAudioCustomLoadThrough {
    @NotNull(message = "id is mandatory")
    private Long id;

    @DecimalMin(value = "2")
    private int time;

    public TextAudioCustomLoadThrough(Long id, int time) {
        this.id = id;
        this.time = time;
    }

    public TextAudioCustomLoadThrough() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
