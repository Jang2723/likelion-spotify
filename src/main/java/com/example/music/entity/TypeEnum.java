package com.example.music.entity;

import lombok.Getter;

@Getter
public enum TypeEnum {
    ARTIST("/artists"),
    ALBUM("/albums"),
    TRACK("/tracks");

    private final String resources;

    TypeEnum(String resources) {
        this.resources = resources;
    }

    public String toString() {
        return this.name().toLowerCase();
    }
}