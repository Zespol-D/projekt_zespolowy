package com.example.projekt_zespolowy

// klasa do zmiany ikonek w zależności od miejsca pobytu użytkownika
enum class Location {
    HOME, // dla ekranu głównego
    ADD_COURSE, // dla ekranu dodawania kursu
    COURSE_DETAILS, // dla ekranu konkretnego kursu
    PROFILE, // dla ekranu profilu użytkownika
    EDITABLE_PROFILE, // dla ekranu gdzie możemy edytować profil użytkownika
    SETTINGS, // dla ekranu ustawień
    LOG_OUT // dla wylogowania ale na razie bez pomysłu
}