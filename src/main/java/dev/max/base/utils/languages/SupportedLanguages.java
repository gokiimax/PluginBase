package dev.max.base.utils.languages;

import lombok.Getter;

@Getter
public enum SupportedLanguages {

    ENGLISH("en"),
    GERMAN("de");

    public String lang;

    SupportedLanguages(final String lang) { this.lang = lang; }


}
