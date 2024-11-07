package ru.itis.eyejust.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.itis.eyejust.dto.MedicalReportDto;
import ru.itis.eyejust.models.MedicalReport;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalReportMapper {
    @Mapping(target = "user", source = "user.id")
    MedicalReportDto toMedicalReportDto(MedicalReport medicalReport);

    List<MedicalReportDto> toMedicalReportDtoList(List<MedicalReport> medicalReportList);

}
