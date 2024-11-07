package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoFormDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String gender;
    private String birthdate;
    private String address;
    private String pastIllnesses;
    private String surgeries;
    private String chronicDiseases;
    private String drugIntolerance;
    private String badHabits;
}
